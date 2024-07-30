package org.wdfeer.kards.common.card

object Hand {
    fun getRandom(count: Int): List<CardType> = buildList { repeat(count) { add(CardType.entries.random()) } }
}