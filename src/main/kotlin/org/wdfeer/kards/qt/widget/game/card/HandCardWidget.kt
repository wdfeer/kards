package org.wdfeer.kards.qt.widget.game.card

import io.qt.core.QEvent
import io.qt.gui.QEnterEvent
import io.qt.gui.QMouseEvent
import org.wdfeer.kards.common.card.Card

class HandCardWidget(card: Card, index: Int, private val onClicked: () -> Unit) : CardWidget(card) {
    init {
        labels[0].text += " [${index + 1}]"
    }

    override fun mousePressEvent(event: QMouseEvent?) { onClicked.invoke() }

    override fun enterEvent(event: QEnterEvent?) {
        styleSheet = "border: 3px solid black;"
    }

    override fun leaveEvent(event: QEvent?) {
        styleSheet = "border: 1px solid black;"
    }
}