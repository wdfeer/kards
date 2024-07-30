package org.wdfeer.kards.common.server

import kotlinx.coroutines.delay
import org.wdfeer.kards.common.card.CardType
import org.wdfeer.kards.common.card.Hand
import org.wdfeer.kards.common.card.MutableCard
import org.wdfeer.kards.common.client.ClientState
import org.wdfeer.kards.common.client.LocalServerAccessor
import org.wdfeer.kards.common.client.Me
import org.wdfeer.kards.common.client.Opponent
import kotlin.random.Random

data class ServerState(
    val fields: List<MutableList<MutableCard>> = listOf(mutableListOf(), mutableListOf()),
    val hands: List<MutableList<CardType>> = listOf(Hand.getRandom(7).toMutableList(), Hand.getRandom(7).toMutableList()),
    var turnCount: Int = Random.nextInt(2)
) {
    private val playing: Int get() = (turnCount + 1) % 2

    init {
        if (canPlayAi())
            playAi()
    }

    fun createClientState(id: Int): ClientState = ClientState(
        if (id == 1) fields else fields.reversed(),
        Me(hands[id], playing == id),
        Opponent(getOtherHand(id).size, playing != id),
        LocalServerAccessor(this, id)
    )

    fun updateClient(client: ClientState, player: Int): Pair<ClientState, Boolean> {
        val newState = createClientState(player)
        return if (client.hashCode() != newState.hashCode()) Pair(newState, true)
        else Pair(client, false)
    }

    fun playCard(player: Int, card: Int) {
        if (player != playing) throw IllegalArgumentException("Player $player cannot play on turn $turnCount!")

        if (!(0 until hands[player].size).contains(card))
            throw IndexOutOfBoundsException("Player $player does not have a card with index $card! Hand size: ${hands[player].size}")

        fields[player].add(MutableCard(hands[player][card]))
        hands[player].removeAt(card)

        onTurnEnd(player)
    }

    private fun onTurnEnd(player: Int) {
        fields[player].forEach { card ->
            card.turnEnd(getOtherField(player).filter { !it.dead })
        }
        fields.forEach { field -> field.removeIf { it.dead } }

        turnCount++

        if (canPlayAi())
            ServerCoroutine.launch {
                delay(1000)
                playAi()
            }
    }

    private fun canPlayAi() = playing == 0 && hands[0].isNotEmpty()

    /** @throws IllegalStateException */
    private fun playAi() {
        if (canPlayAi())
            playCard(0, AI.chooseCardToPlay(this, 0))
        else throw IllegalStateException("AI cannot make a turn right now!")
    }

    private fun getOtherField(my: Int): MutableList<MutableCard> = fields[(my + 1) % 2]
    private fun getOtherHand(my: Int): MutableList<CardType> = hands[(my + 1) % 2]
}