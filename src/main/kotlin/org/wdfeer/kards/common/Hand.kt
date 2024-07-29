package org.wdfeer.kards.common

import org.wdfeer.kards.common.util.randoms

object Hand {
    fun getRandom(count: Int): List<CardType> = CardType.entries.randoms(count)
}