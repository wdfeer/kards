package org.wdfeer.kards.common.card

interface Card {
    val name: String
    val hp: Int
    val dmg: Int
    val score: Int

    fun displayString(): String = "$name\n$hp \udb80\uded1\n$dmg \uDB81\uDCE5\n" + "\udb81\udccf".repeat(score)
}