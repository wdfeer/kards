package org.wdfeer.kards.common.server.ai

import org.wdfeer.kards.common.Logger.logTime
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
        logTime(algorithm, {"Total evaluation ET = $it ms"}) {
            val values = state.hands[player].mapIndexed {i, card ->
                logTime(algorithm, {"Card #$i evaluation ET = $it ms"}) {
                    algorithm.evaluate(state, card, player)
                }
            }
            cardIndex = values.indexOf(values.max())
        }

        return cardIndex
    }
}