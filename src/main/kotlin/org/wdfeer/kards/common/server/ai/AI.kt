package org.wdfeer.kards.common.server.ai

import org.wdfeer.kards.common.Logger.info
import org.wdfeer.kards.common.Logger.warn
import org.wdfeer.kards.common.server.ServerState

object AI {
    var difficulty: AiDifficulty
        get() = AiDifficulty.entries.first { it.algorithm == algorithm }
        set(value) { algorithm = value.algorithm }

    private lateinit var algorithm: AiAlgorithm


    init {
        difficulty = AiDifficulty.entries.random()
    }


    fun chooseCardToPlay(state: ServerState, player: Int): Int {
        var cardIndex: Int = -1
        logTime {
            val values = state.hands[player].map { algorithm.evaluate(state, it, player) }
            cardIndex = values.indexOf(values.max())
        }

        return cardIndex
    }

    private fun logTime(function: () -> Unit) {
        val startTime = System.currentTimeMillis()

        function()

        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime

        val message = "Evaluation ET = $elapsedTime ms"

        if (elapsedTime > 1000)
            algorithm.warn(message)
        else
            algorithm.info(message)
    }
}