package org.wdfeer.kards.common

enum class Outcome {
    Victory,
    Draw,
    Defeat;

    companion object {
        fun getOutcome(fields: List<List<Card>>, player: Int): Outcome {
            val scores = fields.map(Field::getScore)
            val my = scores[player]
            val other = scores[(player + 1) % 2]

            return if (my > other)
                Victory
            else if (my < other)
                Defeat
            else
                Draw
        }
    }
}