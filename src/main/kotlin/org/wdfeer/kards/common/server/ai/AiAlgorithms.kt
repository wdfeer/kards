package org.wdfeer.kards.common.server.ai

import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.common.server.ServerState
import org.wdfeer.kards.common.server.ai.AiUtils.evaluateBoardState
import org.wdfeer.kards.common.server.ai.AiUtils.simulateBoardState

typealias evaluateCard = (state: ServerState, card: Card, player: Int) -> Int

object AiAlgorithms {
    val random: evaluateCard = {_, _, _ -> 0}

    val scoreDiffAfterTurnEnd: evaluateCard = { state, card, player ->
        val newBoard = simulateBoardState(state.fields, player, card)
        evaluateBoardState(newBoard, player)
    }
}