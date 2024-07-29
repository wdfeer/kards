package org.wdfeer.kards.qt.widget

import io.qt.core.QSize
import io.qt.widgets.QFrame
import io.qt.widgets.QLabel
import io.qt.widgets.QVBoxLayout
import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.qt.util.SizePolicies

class CardWidget(card: Card, index: Int) : QFrame() {
    init {
        sizePolicy = SizePolicies.FixMin
        minimumSize = QSize(85, 120)

        setLayout(QVBoxLayout().apply {
            val strings = card.displayString().split("\n").toMutableList()

            addWidget(QLabel(strings[0] + " [${index + 1}]").apply {
                styleSheet = "border: none;"
                styleSheet += "text-decoration: underline;"
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