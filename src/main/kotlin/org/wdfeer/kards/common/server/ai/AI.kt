package org.wdfeer.kards.common.server.ai

import org.wdfeer.kards.common.server.ServerState

object AI {
    private val algorithm: AiAlgorithm = listOf(Random(), Greedy()).random()

    fun chooseCardToPlay(state: ServerState, player: Int): Int {
        val values = state.hands[player].map { algorithm.evaluate(state, it, player) }
        return values.indexOf(values.max())
    }
}