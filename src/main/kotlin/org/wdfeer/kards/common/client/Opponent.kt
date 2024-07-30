package org.wdfeer.kards.common.client

data class Opponent(
    val handSize: Int,
    val playing: Boolean,
    val name: String = "Opponent"
)
