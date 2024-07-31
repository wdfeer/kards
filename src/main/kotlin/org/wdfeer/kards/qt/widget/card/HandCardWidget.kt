package org.wdfeer.kards.qt.widget.card

import io.qt.core.QEvent
import io.qt.gui.QEnterEvent
import io.qt.gui.QMouseEvent
import io.qt.widgets.QLabel
import org.wdfeer.kards.common.card.Card

class HandCardWidget(card: Card, private val index: Int, private val onClicked: () -> Unit) : CardWidget(card) {
    override fun getNameLabel(text: String): QLabel {
        return super.getNameLabel(text + " [${index + 1}]")
    }


    override fun mousePressEvent(event: QMouseEvent?) { onClicked.invoke() }

    override fun enterEvent(event: QEnterEvent?) {
        styleSheet = "border: 3px solid black;"
    }

    override fun leaveEvent(event: QEvent?) {
        styleSheet = "border: 1px solid black;"
    }
}