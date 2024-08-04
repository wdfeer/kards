package org.wdfeer.kards.qt.widget.abstract

import io.qt.widgets.QVBoxLayout

abstract class Menu : MarginedWidget(left = 40, right = 40) {
    abstract fun QVBoxLayout.initLayout()

    init {
        setLayout(QVBoxLayout().apply { initLayout() })
    }
}