package org.wdfeer.kards.common.server

import kotlin.random.Random

object AI {
    fun chooseCardToPlay(state: ServerState, player: Int): Int = Random.nextInt(state.hands[player].size)
}