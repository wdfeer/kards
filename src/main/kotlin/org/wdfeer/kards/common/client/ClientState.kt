package org.wdfeer.kards.common.client

import org.wdfeer.kards.common.card.FieldCard

data class ClientState(
    val fields: List<List<FieldCard>>,
    val me: Me,
    val opponent: Opponent,
    val accessor: ServerAccessor,
)