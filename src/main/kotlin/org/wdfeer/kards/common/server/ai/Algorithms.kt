package org.wdfeer.kards.common.server.ai

import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.common.card.Field
import org.wdfeer.kards.common.card.MutableCard
import org.wdfeer.kards.common.server.GameRules
import org.wdfeer.kards.common.server.ServerState
import kotlin.math.roundToInt

typealias CardEvaluator = (state: ServerState, card: Card, player: Int) -> Int

object Algorithms {
    val random: CardEvaluator = { _, _, _ -> 0}

    val future1: CardEvaluator = { state, card, player ->
        val newBoard = simulateBoardState(state.fields, player, card)
        evaluateBoardState(newBoard, player)
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