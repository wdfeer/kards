package org.wdfeer.kards.common.server.ai

import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.common.card.Field
import org.wdfeer.kards.common.card.MutableCard
import org.wdfeer.kards.common.server.GameRules

object AiUtils {
    fun simulateBoardState(board: List<List<Card>>, player: Int, cardToPlay: Card): List<MutableList<MutableCard>> {
        val newBoard: List<MutableList<MutableCard>> = board.map { field -> field.map { MutableCard(it) }.toMutableList() }

        GameRules.playCard(newBoard, player, cardToPlay)
        newBoard[player].add(MutableCard(cardToPlay))
        GameRules.onTurnEnd(newBoard, player)

        return newBoard
    }

    fun evaluateBoardState(board: List<List<Card>>, player: Int): Int = Field.getScoreDiff(board, player)
}