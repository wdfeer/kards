package org.wdfeer.kards.common

data class MutableCard(val type: CardType, override var hp: Int, override var dmg: Int, override var score: Int): Card {
    constructor(type: CardType) : this(type, type.hp, type.dmg, type.score)

    override val name: String
        get() = type.name

    val dead: Boolean get() = hp <= 0
    fun damage(amount: Int) { hp -= amount }
}
