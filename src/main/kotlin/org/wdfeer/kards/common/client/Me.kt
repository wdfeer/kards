package org.wdfeer.kards.common.client

import org.wdfeer.kards.common.card.CardType

data class Me(
    val hand: List<CardType>,
    val name: String = "You"
)
