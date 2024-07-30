package org.wdfeer.kards.qt.widget

import io.qt.widgets.*
import org.wdfeer.kards.qt.util.QSpacerItem
import org.wdfeer.kards.qt.util.SizePolicies

class PlayerWidget(displayName: String, playing: Boolean, cardCount: Int) : QWidget() {
    init {
        val spacer = QSpacerItem.ExpMin

        setLayout(QHBoxLayout().apply {
            addWidget(QLabel(displayName))
            addSpacerItem(spacer)

            if (playing) {
                addWidget(QLabel("PLAYING"))
                addSpacerItem(spacer)
            }

            addWidget(QLabel("$cardCount Cards"))

            sizePolicy = SizePolicies.ExpFix
        })
    }
}