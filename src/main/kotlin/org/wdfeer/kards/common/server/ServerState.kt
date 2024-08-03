package org.wdfeer.kards.common.server

import kotlinx.coroutines.delay
import org.wdfeer.kards.common.card.CardType
import org.wdfeer.kards.common.card.Hand
import org.wdfeer.kards.common.card.MutableCard
import org.wdfeer.kards.common.client.ClientState
import org.wdfeer.kards.common.client.LocalServerAccessor
import org.wdfeer.kards.common.client.Me
import org.wdfeer.kards.common.client.Opponent
import org.wdfeer.kards.common.server.ai.AI
import kotlin.random.Random

class ServerState(
    cardCount: Int = 7
) {
    val fields: List<MutableList<MutableCard>> = listOf(mutableListOf(), mutableListOf())
    val hands: List<MutableList<CardType>> = listOf(Hand.getRandom(cardCount).toMutableList(), Hand.getRandom(cardCount).toMutableList())

    private var playing = Random.nextInt(2)
    private fun flipWhoPlaying() { playing = 1 - playing }

    init {
        if (canPlayAi())
            playAi()
    }

    fun createClientState(id: Int): ClientState = ClientState(
        if (id == 1) fields else fields.reversed(),
        Me(hands[id], playing == id),
        Opponent(hands[1 - id].size, playing != id),
        LocalServerAccessor(this, id)
    )

    fun updateClient(client: ClientState, player: Int): Pair<ClientState, Boolean> {
        val newState = createClientState(player)
        return if (client.hashCode() != newState.hashCode()) Pair(newState, true)
        else Pair(client, false)
    }

    fun playCard(player: Int, card: Int) {
        if (player != playing) throw IllegalArgumentException("Player $player cannot play at this turn!")

        if (!(0 until hands[player].size).contains(card))
            throw IndexOutOfBoundsException("Player $player does not have a card with index $card! Hand size: ${hands[player].size}")

        GameRules.playCard(fields, player, hands[player][card])
        hands[player].removeAt(card)

        onTurnEnd(player)
    }

    private fun onTurnEnd(player: Int) {
        GameRules.onTurnEnd(fields, player)

        flipWhoPlaying()

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
}