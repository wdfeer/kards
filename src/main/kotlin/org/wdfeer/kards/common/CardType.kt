package org.wdfeer.kards.common

enum class CardType(override val hp: Int, override val dmg: Int, override val power: Int) : Card {
    Warrior(3, 1, 1),
    Archer(2, 2, 1),
    Mage(1, 3, 1);
}