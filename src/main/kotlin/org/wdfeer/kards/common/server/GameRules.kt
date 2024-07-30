package org.wdfeer.kards.common.server

import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.common.card.MutableCard

object GameRules {
    fun playCard(fields: List<MutableList<MutableCard>>, player: Int, card: Card) {
        fields[player].add(MutableCard(card))
    }

    fun onTurnEnd(fields: List<MutableList<MutableCard>>, player: Int) {
        fields[player].forEach { card ->
            card.turnEnd(fields[1 - player].filter { !it.dead })
        }
        fields.forEach { field -> field.removeIf { it.dead } }
    }
}