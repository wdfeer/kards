package org.wdfeer.kards.common.server.ai

import org.wdfeer.kards.common.server.ServerState

object AI {
    private var difficulty: AiDifficulty
        get() = AiDifficulty.entries.first { it.algorithm == algorithm }
        set(value) { algorithm = value.algorithm }

    private lateinit var algorithm: AiAlgorithm


    init {
        difficulty = AiDifficulty.entries.random()
    }


    fun chooseCardToPlay(state: ServerState, player: Int): Int {
        val values = state.hands[player].map { algorithm.evaluate(state, it, player) }
        return values.indexOf(values.max())
    }
}