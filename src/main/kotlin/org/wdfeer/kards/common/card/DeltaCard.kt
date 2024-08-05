package org.wdfeer.kards.common.card

data class DeltaCard(
    val cardId: Long,
    val type: CardStatType,
    val change: Int
)
