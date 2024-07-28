package org.wdfeer.kards.common

enum class Card(val hp: Byte, val dmg: Byte, val power: Byte) {
    Warrior(3, 1, 1),
    Archer(2, 2, 1),
    Mage(1, 3, 1);

    override fun toString(): String = "$hp/$dmg/$power"
}