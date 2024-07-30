package org.wdfeer.kards.common.card

enum class CardType(override val hp: Int, override val dmg: Int, override val score: Int, private val special: SpecialAbility? = null) : Card {
    // Base
    Warrior(4, 1, 1),
    Archer(3, 2, 1),
    Mage(2, 3, 1),

    Castle(7, 0, 0),
    Dynamite(0, 4, 0),

    // Special Cards
    Golem(3, 1, 0, SpecialAbility { it.hp++ }),
    Spy(3, 1, 1, SpecialAbility { it.dmg++ }),
    King(3, 0, 1, SpecialAbility { it.score++ }),
    Queen(1,1,1, SpecialAbility { it.hp++; it.dmg++; it.score++ });

    override fun getSpecial(): SpecialAbility? = special
}