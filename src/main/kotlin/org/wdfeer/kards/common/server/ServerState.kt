package org.wdfeer.kards.common.server

import org.wdfeer.kards.common.Card
import org.wdfeer.kards.common.Hand
import org.wdfeer.kards.common.client.ClientState

data class ServerState(
    val fields: List<MutableList<Card>> = listOf(mutableListOf(), mutableListOf()),
    val hands: List<MutableList<Card>> = listOf(Hand.getDefault().toMutableList(), Hand.getDefault().toMutableList())
) {
    fun createClientState(id: Int): ClientState =
        ClientState(
            if (id == 1) fields else fields.reversed(),
            hands[id],
            hands[if (id == 1) 0 else 1].count()
        ) {
            fields[id].add(hands[id][it])
            hands[id].removeAt(it)
        }
}