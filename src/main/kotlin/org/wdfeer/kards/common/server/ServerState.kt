package org.wdfeer.kards.common.server

import org.wdfeer.kards.common.MutableCard
import org.wdfeer.kards.common.CardType
import org.wdfeer.kards.common.Hand
import org.wdfeer.kards.common.client.ClientState
import org.wdfeer.kards.common.client.LocalServerAccessor

data class ServerState(
    val fields: List<MutableList<MutableCard>> = listOf(mutableListOf(), mutableListOf()),
    val hands: List<MutableList<CardType>> = listOf(Hand.getDefault().toMutableList(), Hand.getDefault().toMutableList()),
    var turnCount: Int = 0
) {
    private val playing: Int get() = (turnCount + 1) % 2

    fun createClientState(id: Int): ClientState = ClientState(
        if (id == 1) fields else fields.reversed(),
        hands[id],
        getOtherHand(id).size,
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
        fields[player].forEach {
            val target = getOtherField(player).randomOrNull() ?: return@forEach
            target.damage(it.dmg)
        }
        getOtherField(player).removeIf { it.dead }

        turnCount++

        if (playing == 0 && hands[0].isNotEmpty())
            playCard(0, AI.chooseCardToPlay(this, 0))
    }

    private fun getOtherField(my: Int): MutableList<MutableCard> = fields[(my + 1) % 2]
    private fun getOtherHand(my: Int): MutableList<CardType> = hands[(my + 1) % 2]
}