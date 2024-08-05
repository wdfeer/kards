package org.wdfeer.kards.common.card

import kotlin.random.Random

data class MutableCard(
    val type: Card,
    override var hp: Int,
    override var dmg: Int,
    override var score: Int,
    override val id: Long
): FieldCard {
    constructor(type: Card) : this(type, type.hp, type.dmg, type.score, Random.nextLong())

    override val name: String
        get() = type.name

    val dead: Boolean get() = hp <= 0
    private fun damage(amount: Int) { hp -= amount }

    fun turnEnd(enemies: List<MutableCard>) {
        enemies.randomOrNull()?.damage(dmg)
        type.getSpecial()?.turnEnd?.let { it(this) }
    }
}
