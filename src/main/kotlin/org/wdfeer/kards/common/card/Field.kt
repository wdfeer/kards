package org.wdfeer.kards.common.card

object Field {
    private fun getScore(field: List<Card>): Int = field.sumOf { it.score }

    fun getScoreDiff(fields: List<List<Card>>, player: Int = 1): Int = getScore(fields[player]) - getScore(fields[(player + 1) % 2])
}