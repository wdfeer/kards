package org.wdfeer.kards.qt.widget.game

import io.qt.widgets.QHBoxLayout
import io.qt.widgets.QLabel
import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.common.card.DeltaCard
import org.wdfeer.kards.qt.util.WrappedBoxLayout
import org.wdfeer.kards.qt.widget.game.card.FieldCardWidget

class FieldWrapper(cards: Map<Card, DeltaCard>) : WrappedBoxLayout<QHBoxLayout>(QHBoxLayout()) {
    init {
        layout.addStretch()

        cards.forEach {
            layout.addWidget(FieldCardWidget(it.key, it.value))
        }

        layout.addStretch()

        layout.addWidget(QLabel("${cards.keys.sumOf { it.score }} \uDB81\uDCCF").apply {
            styleSheet = "border: none;"
        })

        widget.styleSheet = "border: 1px solid black;"
    }
}