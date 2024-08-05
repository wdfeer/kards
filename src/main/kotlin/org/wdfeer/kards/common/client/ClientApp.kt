package org.wdfeer.kards.common.client

import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.common.card.DeltaCard

interface ClientApp {
    var oldState: ClientState?
    var state: ClientState
    fun redrawState()
    fun redrawDeltas(deltas: Map<Long, List<DeltaCard>>)


    fun updateState() {
        oldState = state
        state = state.accessor.updateState(state).first
        redrawState()
        redrawDeltas(getDeltas())
    }
    private fun getDeltas(): Map<Long, List<DeltaCard>> {
        if (oldState == null || oldState!! == state) return emptyMap()

        val oldCards: Map<Long, Card> = oldState!!.fields.flatten().associateBy { it.id }
        val newCards: Map<Long, Card> = state.fields.flatten().associateBy { it.id }

        return newCards.filter { oldCards.containsKey(it.key) }.mapValues {
            val old = oldCards[it.key]!!

            DeltaCard.create(it.value, old)
        }
    }

    fun hasGameEnded(): Boolean = state.me.hand.isEmpty() && state.opponent.handSize == 0

    fun canPlayCard(card: Int): Boolean = state.me.playing && state.me.hand.size > card

    /** @throws IndexOutOfBoundsException
     * @throws IllegalStateException */
    fun playCard(card: Int) {
        if (!state.me.playing) throw IllegalStateException("The player may not play a card right now!")
        if (state.me.hand.size <= card) throw IndexOutOfBoundsException("Card $card not found! Hand size: ${state.me.hand.size}")

        state.accessor.playCard(card)

        updateState()
    }
}