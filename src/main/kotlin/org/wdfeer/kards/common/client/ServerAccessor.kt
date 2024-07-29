package org.wdfeer.kards.common.client

class ServerAccessor(
    val updateState: (ClientState) -> Pair<ClientState, Boolean>,
    val playCard: (Int) -> Unit
)