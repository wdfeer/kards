package org.wdfeer.kards.common

object Field {
    fun getScore(field: List<Card>): Int = field.sumOf { it.score }
}