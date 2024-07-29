package org.wdfeer.kards.common

enum class CardType(override val hp: Int, override val dmg: Int, override val score: Int) : Card {
    Warrior(4, 1, 1),
    Archer(3, 2, 1),
    Mage(2, 3, 1),
    Spy(1, 1, 2),
    King(2, 0, 2),
    Castle(7, 0, 0),
    Cannon(1, 4, 0)
}