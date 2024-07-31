package org.wdfeer.kards.common.server.ai

enum class AiDifficulty(internal val algorithm: AiAlgorithm) {
    Easy(RandomAi()),
    Normal(GreedyAi())
}