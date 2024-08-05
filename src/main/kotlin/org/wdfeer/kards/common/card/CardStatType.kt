package org.wdfeer.kards.common.card

enum class CardStatType(val get: (Card) -> Int) {
    Hp({it.hp}),
    Dmg({it.dmg}),
    Score({it.score})
}