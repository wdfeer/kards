package org.wdfeer.kards.common.client

import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.common.card.DeltaCard

data class ClientState(
    val fields: List<List<Card>>,
    val statChanges: Map<Long, DeltaCard>,
    val me: Me,
    val opponent: Opponent,
    val accessor: ServerAccessor,
)