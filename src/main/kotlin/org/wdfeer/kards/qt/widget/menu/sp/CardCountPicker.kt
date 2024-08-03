package org.wdfeer.kards.qt.widget.menu.sp

import io.qt.widgets.QSpinBox

class CardCountPicker : QSpinBox() {
    init {
        setRange(4, 8)
        value = 7
    }
}