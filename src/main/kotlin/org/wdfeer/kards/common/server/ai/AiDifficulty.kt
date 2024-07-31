package org.wdfeer.kards.common.server.ai

enum class AiDifficulty(internal val algorithm: AiAlgorithm) {
    Easy(RandomAi()),
    Normal(GreedyAi()),
    Hard(RecursiveAi(2)),
    Lunatic(RecursiveAi(3)) // TODO: optimize and increase depth
}