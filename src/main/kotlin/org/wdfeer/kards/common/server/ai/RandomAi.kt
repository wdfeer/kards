package org.wdfeer.kards.common.server.ai

import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.common.server.ServerState

internal class RandomAi : AiAlgorithm {
    override fun evaluate(presentState: ServerState, cardToPlay: Card, player: Int): Int = 0
}