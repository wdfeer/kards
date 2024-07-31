package org.wdfeer.kards.qt.widget.game.card

import io.qt.core.QSize
import io.qt.widgets.QFrame
import io.qt.widgets.QLabel
import io.qt.widgets.QVBoxLayout
import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.qt.util.SizePolicies

abstract class CardWidget(card: Card, ) : QFrame() {
    init {
        sizePolicy = SizePolicies.FixMin
        minimumSize = QSize(85, 120)
        styleSheet = "border: 1px solid black;"

        setLayout(QVBoxLayout().apply {
            val strings = card.displayString().split("\n").toMutableList()

            addWidget(getNameLabel(strings[0]))

            strings -= strings[0]

            strings.forEach {
                addWidget(QLabel(it).apply {
                    styleSheet = "border: none;"
                })
            }
        })
    }

    open fun getNameLabel(text: String): QLabel = QLabel(text).apply {
        styleSheet = "border: none;"
        styleSheet += "font-size: 17px;"
    }
}