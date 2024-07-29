package org.wdfeer.kards.qt.widget

import io.qt.widgets.QHBoxLayout
import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.qt.util.WrappedBoxLayout

class FieldWrapper(cards: List<Card>) : WrappedBoxLayout<QHBoxLayout>(QHBoxLayout()) {
    init {
        cards.forEach {
            layout.addWidget(CardWidget(it))
        }

        widget.styleSheet = "border: 1px solid black;"
    }
}