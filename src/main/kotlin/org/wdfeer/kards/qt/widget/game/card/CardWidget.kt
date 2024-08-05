package org.wdfeer.kards.qt.widget.game.card

import io.qt.core.QSize
import io.qt.widgets.QFrame
import io.qt.widgets.QLabel
import io.qt.widgets.QVBoxLayout
import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.common.card.DeltaCard
import org.wdfeer.kards.qt.util.SizePolicies

// TODO: apply the "delta" parameter visually
abstract class CardWidget(card: Card, delta: DeltaCard? = null) : QFrame() {
    protected val labels: List<QLabel> = getLabels(card.displayString().split("\n"))

    init {
        sizePolicy = SizePolicies.FixMin
        minimumSize = QSize(85, 120)
        styleSheet = "border: 1px solid black;"

        setLayout(QVBoxLayout().apply {
            labels.forEach { addWidget(it) }
        })
    }

    private fun getLabels(strings: List<String>): List<QLabel> =
        mutableListOf(getNameLabel(strings[0])) +
                strings.toMutableList().apply { removeFirst() }.map { str -> QLabel(str).apply { styleSheet = "border: none;" } }

    private fun getNameLabel(text: String): QLabel = QLabel(text).apply {
        styleSheet = "border: none;"
        styleSheet += "font-size: 17px;"
    }
}