package org.wdfeer.kards.common

data class GameState(val players: List<PlayerState> = listOf(PlayerState.getDefault(), PlayerState.getDefault()))