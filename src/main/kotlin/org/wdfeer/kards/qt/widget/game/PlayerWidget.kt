package org.wdfeer.kards.qt.widget.game

import io.qt.widgets.*
import org.wdfeer.kards.qt.util.QSpacerItem
import org.wdfeer.kards.qt.util.SizePolicies

class PlayerWidget(displayName: String, playing: Boolean, cardCount: Int) : QWidget() {
    init {
        setLayout(QHBoxLayout().apply {
            addWidget(QLabel(displayName))
            addSpacerItem(QSpacerItem.ExpMin)

            if (playing) {
                addWidget(QLabel("PLAYING"))
                addSpacerItem(QSpacerItem.ExpMin)
            }

            addWidget(QLabel("$cardCount Cards"))

            sizePolicy = SizePolicies.ExpFix
        })
    }
}