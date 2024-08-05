package org.wdfeer.kards.qt.widget.game.card

import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.common.card.DeltaCard

class FieldCardWidget(private val card: Card, private val delta: DeltaCard?) : CardWidget() {
    init {
        build()
    }

    override fun getStyledStrings(): List<Pair<String, String>> {
        return card.getStringsByStat().map { entry ->
            Pair(entry.value, getStyle(this.delta?.stats?.find { it.type == entry.key }?.change))
        }
    }

    private fun getStyle(delta: Int?) =
        if (delta == null) ""
        else if (delta > 0) "color: green;"
        else if (delta < 0) "color: red"
        else ""
}