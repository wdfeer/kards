package org.wdfeer.kards.common.client

import org.wdfeer.kards.common.card.Card

data class ClientState(
    val fields: List<List<Card>>,
    val me: Me,
    val opponent: Opponent,
    val accessor: ServerAccessor,
)