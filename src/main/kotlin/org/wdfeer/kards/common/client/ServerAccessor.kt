package org.wdfeer.kards.common.client

interface ServerAccessor {
    fun updateState(clientState: ClientState): Pair<ClientState, Boolean>

    fun playCard(cardId: Int)
}
