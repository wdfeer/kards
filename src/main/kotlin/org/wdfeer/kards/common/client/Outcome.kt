package org.wdfeer.kards.common.client

enum class Outcome {
    Victory,
    Draw,
    Defeat;

    companion object {
        fun getOutcome(scoreDiff: Int): Outcome {
            return if (scoreDiff > 0)
                Victory
            else if (scoreDiff < 0)
                Defeat
            else
                Draw
        }
    }
}