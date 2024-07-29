package org.wdfeer.kards.common

interface Card {
    val name: String
    val hp: Int
    val dmg: Int
    val power: Int

    fun displayString(): String = "$name\n$hp/$dmg/$power"
}