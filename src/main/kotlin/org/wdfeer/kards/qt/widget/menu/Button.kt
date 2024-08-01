package org.wdfeer.kards.qt.widget.menu

import io.qt.widgets.QPushButton

class Button(text: String, onClicked: () -> Unit) : QPushButton(text) {
    init {
        clicked.connect(onClicked)
    }
}