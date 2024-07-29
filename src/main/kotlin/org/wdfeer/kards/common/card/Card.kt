package org.wdfeer.kards.common.card

interface Card {
    val name: String
    val hp: Int
    val dmg: Int
    val score: Int

    fun displayString(): String = "$name\nHP $hp\nATK $dmg\nVAL $score"
}