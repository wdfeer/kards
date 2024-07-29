package org.wdfeer.kards.qt.widget

import io.qt.widgets.QHBoxLayout
import io.qt.widgets.QLabel
import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.qt.util.WrappedBoxLayout

class FieldWrapper(cards: List<Card>) : WrappedBoxLayout<QHBoxLayout>(QHBoxLayout()) {
    init {
        layout.addStretch()

        cards.forEach {
            layout.addWidget(CardWidget(it))
        }

        layout.addStretch()

        layout.addWidget(QLabel("${cards.sumOf { it.score }} \uDB81\uDCCF").apply {
            styleSheet = "border: none;"
        })

        widget.styleSheet = "border: 1px solid black;"
    }
}