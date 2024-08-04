package org.wdfeer.kards.qt.widget.menu.options

import io.qt.widgets.QLabel
import io.qt.widgets.QVBoxLayout
import org.wdfeer.kards.qt.widget.abstract.Menu

class OptionsMenu : Menu() {
    init {
        setLayout(QVBoxLayout().apply {
            QLabel("Not Implemented!")
        })
    }
}