package org.wdfeer.kards.common.client

import org.wdfeer.kards.common.server.ServerState

class LocalServerAccessor(private val serverState: ServerState, private val player: Int) : ServerAccessor {
    override fun updateState(clientState: ClientState): Pair<ClientState, Boolean> =
        serverState.updateClient(clientState, player)

    override fun playCard(cardId: Int) =
        serverState.playCard(player, cardId)
}