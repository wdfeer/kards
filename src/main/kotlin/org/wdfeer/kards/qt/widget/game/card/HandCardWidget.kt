package org.wdfeer.kards.qt.widget.game.card

import io.qt.core.QEvent
import io.qt.gui.QEnterEvent
import io.qt.gui.QMouseEvent
import org.wdfeer.kards.common.card.Card

class HandCardWidget(private val card: Card, private val index: Int, private val onClicked: () -> Unit) : CardWidget() {
    init {
        build()
    }

    override fun getStyledStrings(): List<Pair<String, String>> {
        val strings = card.getStringsByStat().map { it.value }.toMutableList()
        strings[0] += " [${index + 1}]" // TODO: make this optional

        return strings.map { Pair(it, "") }
    }

    override fun mousePressEvent(event: QMouseEvent?) { onClicked.invoke() }

    override fun enterEvent(event: QEnterEvent?) {
        styleSheet = "border: 3px solid black;"
    }

    override fun leaveEvent(event: QEvent?) {
        styleSheet = "border: 1px solid black;"
    }
}