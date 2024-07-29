package org.wdfeer.kards.common.card

data class MutableCard(val type: CardType, override var hp: Int, override var dmg: Int, override var score: Int): Card {
    constructor(type: CardType) : this(type, type.hp, type.dmg, type.score)

    override val name: String
        get() = type.name

    val dead: Boolean get() = hp <= 0
    private fun damage(amount: Int) { hp -= amount }

    fun turnEnd(enemies: List<MutableCard>) {
        enemies.randomOrNull()?.damage(dmg)
        type.special?.turnEnd?.let { it(this) }
    }
}
