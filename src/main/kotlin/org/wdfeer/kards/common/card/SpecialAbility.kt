package org.wdfeer.kards.common.card

data class SpecialAbility(
    val turnEnd: ((MutableCard) -> Unit)?
)
