package org.wdfeer.kards.common.server.ai

import org.wdfeer.kards.common.server.ServerState

object AI {
    private val evaluateCard = Algorithms.recursive

    fun chooseCardToPlay(state: ServerState, player: Int): Int {
        val values = state.hands[player].map { evaluateCard(state, it, player) }
        return values.indexOf(values.max())
    }
}