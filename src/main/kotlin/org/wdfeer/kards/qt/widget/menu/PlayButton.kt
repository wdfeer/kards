package org.wdfeer.kards.qt.widget.menu

import io.qt.widgets.QPushButton

class PlayButton(onClicked: () -> Unit) : QPushButton("Play") {
    init {
        clicked.connect(onClicked)
    }
}