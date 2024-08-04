package org.wdfeer.kards.qt.widget.menu.options

import io.qt.widgets.QLabel
import io.qt.widgets.QVBoxLayout
import org.wdfeer.kards.qt.widget.abstract.Menu

class OptionsMenu : Menu() {
    override fun QVBoxLayout.initLayout() {
        addWidget(QLabel("Not Implemented!"))
    }
}