package org.wdfeer.kards.common.client

import org.wdfeer.kards.common.card.MutableCard
import org.wdfeer.kards.common.card.CardType

data class ClientState(
    val fields: List<List<MutableCard>>,
    val myCards: List<CardType>,
    val opponentCardCount: Int,
    val accessor: ServerAccessor,
)