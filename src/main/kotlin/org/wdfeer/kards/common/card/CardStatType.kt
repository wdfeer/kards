package org.wdfeer.kards.common.card

enum class CardStatType(val get: (Card) -> Int, val getString: (Int) -> String) {
    Hp({it.hp}, {"$it \uDB80\uDED1"}),
    Dmg({it.dmg}, {"$it \uDB81\uDCE5"}),
    Score({it.score}, {"\udb81\udccf".repeat(it)})
}