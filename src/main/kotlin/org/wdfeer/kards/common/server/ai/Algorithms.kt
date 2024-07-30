package org.wdfeer.kards.common.server.ai

import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.common.card.Field
import org.wdfeer.kards.common.card.MutableCard
import org.wdfeer.kards.common.server.GameRules
import org.wdfeer.kards.common.server.ServerState
import kotlin.math.roundToInt

typealias CardEvaluator = (state: ServerState, card: Card, player: Int) -> Int

object Algorithms {
    val zero: CardEvaluator = { _, _, _ -> 0}

    val greedy: CardEvaluator = { state, card, player ->
        val newBoard = simulateBoardState(state.fields, player, card)
        evaluateBoardState(newBoard, player)
    }

    val recursive: CardEvaluator = fun(state: ServerState, card: Card, player: Int): Int {
        // Base case: if the opponent has no cards left, use the greedy evaluation
        if (state.hands[1 - player].isEmpty()) {
            return greedy(state, card, player)
        }

        // Simulate playing the current card
        val postAiMove = simulateBoardState(state.fields, player, card)
        val aiCards = state.hands[player].toMutableList().apply { remove(card) }

        // Recursively simulate all possible moves for the opponent
        val opponentMoves: List<Int> = state.hands[1 - player].map { opponentCard ->
            val postOpponentMove = simulateBoardState(postAiMove, 1 - player, opponentCard)
            val opponentScore = evaluateBoardState(postOpponentMove, 1 - player)
            val aiScore = evaluateBoardState(postAiMove, player)
            val scoreDiff = aiScore - opponentScore
            scoreDiff
        }

        // Return the maximum score difference for the player
        return opponentMoves.maxOrNull() ?: 0
    }



    fun averaged(evaluator: CardEvaluator, iterations: Int): CardEvaluator = { state, card, player ->
        buildList { repeat(iterations) { add(evaluator(state, card, player)) } }.average().roundToInt()
    }


    private fun simulateBoardState(board: List<List<Card>>, player: Int, cardToPlay: Card): List<MutableList<MutableCard>> {
        val newBoard: List<MutableList<MutableCard>> = board.map { field -> field.map { MutableCard(it) }.toMutableList() }

        GameRules.playCard(newBoard, player, cardToPlay)
        newBoard[player].add(MutableCard(cardToPlay))
        GameRules.onTurnEnd(newBoard, player)

        return newBoard
    }

    private fun evaluateBoardState(board: List<List<Card>>, player: Int): Int = Field.getScoreDiff(board, player)
}