package org.wdfeer.kards.common.card

data class DeltaCard(
    val type: CardStatType,
    val change: Int) {
    companion object {
        fun create(newCard: Card, oldCard: Card): List<DeltaCard> {
            val oldStats: List<Int> = CardStatType.entries.map { it.get(oldCard) }
            val newStats: List<Int> = CardStatType.entries.map { it.get(newCard) }

            val result = mutableListOf<DeltaCard>()
            repeat(CardStatType.entries.size) {
                val change = newStats[it] - oldStats[it]
                if (change != 0)
                    result.add(DeltaCard(CardStatType.entries[it], change))
            }
            return result
        }
    }
}