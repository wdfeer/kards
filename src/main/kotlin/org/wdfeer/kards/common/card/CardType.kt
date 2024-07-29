package org.wdfeer.kards.common.card

enum class CardType(override val hp: Int, override val dmg: Int, override val score: Int, val special: SpecialAbility? = null) : Card {
    // Base
    Warrior(4, 1, 1),
    Archer(3, 2, 1),
    Mage(2, 3, 1),

    // Special Cards
    Spy(2, 1, 1, SpecialAbility { it.dmg++ }),
    King(2, 0, 1, SpecialAbility { it.score++ }),
    Golem(2, 1, 0, SpecialAbility { it.hp++ }),

    // Constructs
    Castle(7, 0, 0),
    Cannon(1, 4, 0)
}