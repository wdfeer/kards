package org.wdfeer.kards.qt.widget

import io.qt.core.QSize
import io.qt.widgets.QFrame
import io.qt.widgets.QLabel
import io.qt.widgets.QVBoxLayout
import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.qt.util.SizePolicies

class CardWidget(card: Card, index: Int? = null) : QFrame() {
    init {
        sizePolicy = SizePolicies.FixMin
        minimumSize = QSize(85, 120)
        styleSheet = "border: 1px solid black;"

        setLayout(QVBoxLayout().apply {
            val strings = card.displayString().split("\n").toMutableList()

            addWidget(QLabel(strings[0] + (index?.let { " [${index + 1}]" } ?: "") ).apply {
                styleSheet = "border: none;"
                styleSheet += "font-size: 17px;"
            })

            strings -= strings[0]

            strings.forEach {
                addWidget(QLabel(it).apply {
                    styleSheet = "border: none;"
                })
            }
        })
    }
}