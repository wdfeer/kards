package org.wdfeer.kards.common.card

private fun createStats(newCard: Card, oldCard: Card): List<DeltaStat> {
    val oldStats: List<Int> = CardStatType.entries.map { it.get(oldCard) }
    val newStats: List<Int> = CardStatType.entries.map { it.get(newCard) }

    val result = mutableListOf<DeltaStat>()
    repeat(CardStatType.entries.size) {
        val change = newStats[it] - oldStats[it]
        if (change != 0)
            result.add(DeltaStat(CardStatType.entries[it], change))
    }
    return result
}

data class DeltaCard(val stats: List<DeltaStat>) {
    constructor() : this(emptyList())
    constructor(newCard: Card, oldCard: Card) : this(createStats(newCard, oldCard))
}