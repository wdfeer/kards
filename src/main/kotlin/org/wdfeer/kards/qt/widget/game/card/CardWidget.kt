package org.wdfeer.kards.qt.widget.game.card

import io.qt.core.QSize
import io.qt.widgets.QFrame
import io.qt.widgets.QLabel
import io.qt.widgets.QVBoxLayout
import org.wdfeer.kards.qt.util.SizePolicies

abstract class CardWidget : QFrame() {
    abstract fun getStyledStrings(): List<Pair<String, String>>
    open fun getFirstStyle(): String = "font-size: 17px;"

    protected fun build() {
        sizePolicy = SizePolicies.FixMin
        minimumSize = QSize(85, 120)
        styleSheet = "border: 1px solid black;"

        setLayout(QVBoxLayout().apply {
            createLabels().forEach { addWidget(it) }
        })
    }

    private fun createLabels() = getStyledStrings().let { pairs ->
        getStyledLabels(pairs.map { it.first }, pairs.map { it.second })
    }

    private fun getStyledLabels(strings: List<String>, styles: List<String>): List<QLabel> =
        strings.mapIndexed { i, str -> QLabel(str).apply {
                    styleSheet = "border: none;" + styles[i] + if (i == 0) getFirstStyle() else ""
                }
        }
}