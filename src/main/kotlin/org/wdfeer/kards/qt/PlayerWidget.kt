package org.wdfeer.kards.qt

import io.qt.widgets.*

class PlayerWidget(displayName: String, cardCount: Int) : QWidget() {
    init {
        setLayout(QHBoxLayout().apply {
            addWidget(QLabel(displayName).apply { styleSheet = "text-align: left;" })
            addSpacerItem(HExpandingSpacer())
            addWidget(QLabel("$cardCount Cards").apply { styleSheet = "text-align: right;" })
        })
    }
}