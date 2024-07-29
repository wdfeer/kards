package org.wdfeer.kards.qt.util

import io.qt.widgets.QBoxLayout
import io.qt.widgets.QWidget

open class WrappedBoxLayout <T : QBoxLayout> (val layout: T, val widget: QWidget = QWidget()) {
    init {
        widget.setLayout(layout)
    }
}