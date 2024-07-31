package org.wdfeer.kards.common.server.ai

import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.common.server.ServerState
import org.wdfeer.kards.common.server.ai.Algorithms.evaluateBoardState
import org.wdfeer.kards.common.server.ai.Algorithms.simulateBoardState

object RecursiveAlgorithm {
    private data class SimpleState(val fields: List<List<Card>>, val hands: List<List<Card>>)

    private fun getStatePostPlay(present: SimpleState, player: Int, cardIndex: Int) =
        SimpleState(
            present.fields.apply { simulateBoardState(this, player, present.hands[player][cardIndex]) },
            present.hands.map { it.toMutableList() }.apply { this[player].removeAt(cardIndex) })

    private fun simulateCloseFutures(present: SimpleState, player: Int, cardIndex: Int): List<SimpleState> {
        if (present.hands[player].isEmpty()) return emptyList()

        val postAiPlay = getStatePostPlay(present, player, cardIndex)

        if (present.hands[1 - player].isEmpty()) return listOf(postAiPlay)

        return List(present.hands[1 - player].size) { i -> getStatePostPlay(postAiPlay, 1 - player, i) }
    }

    private fun simulateFarFutures(present: SimpleState, player: Int, cardIndex: Int, depth: Int): List<SimpleState> {
        val closeFutures = simulateCloseFutures(present, player, cardIndex)

        return if (depth > 1 && closeFutures.isNotEmpty()) {
            closeFutures.flatMap { futureState ->
                val handsSize = futureState.hands[player].size
                (0 until handsSize).flatMap { index ->
                    simulateFarFutures(futureState, player, index, depth - 1)
                }
            }
        } else {
            closeFutures
        }
    }


    fun evaluateCard(presentState: ServerState, card: Card, player: Int): Int {
        val simpleState = SimpleState(presentState.fields, presentState.hands)
        return simulateFarFutures(
            simpleState,
            player,
            simpleState.hands[player].indexOf(card),
            10
        ).maxOfOrNull { evaluateBoardState(it.fields, player) } ?: 0
    }
}