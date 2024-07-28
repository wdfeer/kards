package org.wdfeer.kards.common.server

import org.wdfeer.kards.common.Card
import org.wdfeer.kards.common.Hand
import org.wdfeer.kards.common.client.ClientState

data class ServerState(
    val fields: List<List<Card>> = listOf(emptyList(), emptyList()),
    val hands: List<List<Card>> = listOf(Hand.getDefault(), Hand.getDefault())
) {
    fun createClientState(id: Int): ClientState =
        ClientState(if (id == 1) fields else fields.reversed(), hands[id], hands[if (id == 1) 0 else 1].count())
}