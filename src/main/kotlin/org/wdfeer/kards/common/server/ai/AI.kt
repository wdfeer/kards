package org.wdfeer.kards.common.server.ai

import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import org.wdfeer.kards.common.Logger.logTime
import org.wdfeer.kards.common.server.ServerCoroutine
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

        val jobs = mutableListOf<Job>()

        val hand = state.hands[player]

        val results: Array<Int> = Array(hand.size) { 0 }

        logTime(algorithm, {"Total evaluation ET = $it ms"}) {
            hand.forEachIndexed { index, card ->
                jobs.add(ServerCoroutine.launch {
                    logTime(algorithm, {"Card #$index evaluation ET = $it ms"}) {
                        results[index] = algorithm.evaluate(state, card, player)
                    }
                })
            }

            runBlocking {
                jobs.joinAll()
            }

            cardIndex = results.indexOf(results.max())
        }


        return cardIndex
    }
}