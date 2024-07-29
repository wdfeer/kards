package org.wdfeer.kards.qt

import io.qt.widgets.QLabel
import org.wdfeer.kards.common.Card

class CardWidget(card: Card) : QLabel(card.displayString()) {
    init {
        setFixedSize(60, 100)
        styleSheet = "border: 1px solid black;"
    }
}