package org.wdfeer.kards.common.server.ai

import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.common.server.ServerState

internal data class SimpleState(val fields: List<List<Card>>, val hands: List<List<Card>>) {
    constructor(serverState: ServerState) : this(serverState.fields, serverState.hands)
}