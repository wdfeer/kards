package org.wdfeer.kards.common.server.ai

import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.common.card.Field
import org.wdfeer.kards.common.card.MutableCard
import org.wdfeer.kards.common.server.GameRules
import org.wdfeer.kards.common.server.ServerState
import kotlin.math.roundToInt

internal interface AiAlgorithm {
    fun evaluate(presentState: ServerState, cardToPlay: Card, player: Int): Int

    fun averaged(iterations: Int): (presentState: ServerState, cardToPlay: Card, player: Int) -> Int = { state, card, player ->
        buildList { repeat(iterations) { add(evaluate(state, card, player)) } }.average().roundToInt()
    }



    fun simulateBoardState(board: List<List<Card>>, player: Int, cardToPlay: Card): List<MutableList<MutableCard>> {
        val newBoard: List<MutableList<MutableCard>> = board.map { field -> field.map { MutableCard(it) }.toMutableList() }

        GameRules.playCard(newBoard, player, cardToPlay)
        newBoard[player].add(MutableCard(cardToPlay))
        GameRules.onTurnEnd(newBoard, player)

        return newBoard
    }

    fun evaluateBoardState(board: List<List<Card>>, player: Int): Int = Field.getScoreDiff(board, player)
}