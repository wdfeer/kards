package org.wdfeer.kards.common.card

interface Card {
    val name: String
    val hp: Int
    val dmg: Int
    val score: Int

    fun getSpecial(): SpecialAbility? = null

    private fun statStrings(): Map<CardStatType, String> = CardStatType.entries.associateWith { it.getString(it.getStat(this)) }
    fun getStringsByStat(): Map<CardStatType?, String> = mapOf(Pair(null, "$name\n")) + statStrings()
    fun getStrings(): List<String> = getStringsByStat().values.toList()
}