package org.wdfeer.kards.common

object Field {
    private fun getScore(field: List<Card>): Int = field.sumOf { it.score }

    fun getScoreDiff(fields: List<List<Card>>): Int = getScore(fields[1]) - getScore(fields[0])
}