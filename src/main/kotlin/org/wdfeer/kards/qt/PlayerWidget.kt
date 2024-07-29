package org.wdfeer.kards.qt

import io.qt.widgets.*
import org.wdfeer.kards.qt.util.QSpacerItem
import org.wdfeer.kards.qt.util.SizePolicies

class PlayerWidget(displayName: String, cardCount: Int) : QWidget() {
    init {
        val nameLabel = QLabel(displayName).apply {
            styleSheet = "text-align: left;"
        }

        val spacer = QSpacerItem.ExpMin

        val cardLabel = QLabel("$cardCount Cards").apply {
            styleSheet = "text-align: right;"
        }

        setLayout(QHBoxLayout().apply {
            addWidget(nameLabel)
            addSpacerItem(spacer)
            addWidget(cardLabel)

            sizePolicy = SizePolicies.ExpFix
        })
    }
}