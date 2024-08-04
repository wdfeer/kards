package org.wdfeer.kards.common.server.ai

import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import org.wdfeer.kards.common.Logger.logTimeIf
import org.wdfeer.kards.common.server.ServerCoroutine
import org.wdfeer.kards.common.server.ServerState

object AI {
    var difficulty: AiDifficulty
        get() = AiDifficulty.entries.first { it.algorithm == algorithm }
        set(value) { algorithm = value.algorithm }

    private lateinit var algorithm: AiAlgorithm


    var shouldLogTime: Boolean = false


    init {
        difficulty = AiDifficulty.entries.random()
    }


    fun chooseCardToPlay(state: ServerState, player: Int): Int {
        var cardIndex: Int = -1

        val jobs = mutableListOf<Job>()

        val hand = state.hands[player]

        val results: Array<Int> = Array(hand.size) { 0 }

        logTimeIf(shouldLogTime, algorithm, {"Total evaluation ET = $it ms"}) {
            hand.forEachIndexed { index, card ->
                jobs.add(ServerCoroutine.launch {
                    logTimeIf(shouldLogTime, algorithm, {"Card #$index evaluation ET = $it ms"}) {
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