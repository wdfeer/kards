package org.wdfeer.kards.qt.widget.abstract

import io.qt.widgets.QWidget

abstract class MarginedWidget(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) : QWidget() {
    init {
        this.setContentsMargins(left, top, right, bottom)
    }
}