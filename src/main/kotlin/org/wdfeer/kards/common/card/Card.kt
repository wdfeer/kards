package org.wdfeer.kards.common.card

interface Card {
    val name: String
    val hp: Int
    val dmg: Int
    val score: Int

    fun getSpecial(): SpecialAbility? = null

    fun statStrings(): List<String> = CardStatType.entries.map { it.getString(it.get(this)) }
    fun displayString(): String = "$name\n" + statStrings().joinToString("\n")
}