package org.wdfeer.kards.qt.widget

import io.qt.widgets.QHBoxLayout
import io.qt.widgets.QVBoxLayout
import io.qt.widgets.QWidget
import org.wdfeer.kards.common.client.ClientState
import org.wdfeer.kards.qt.util.QSpacerItem
import org.wdfeer.kards.qt.util.WrappedBoxLayout

class RowsWidget(private val state: ClientState) : QWidget() {
    init {
        val layout = QVBoxLayout()
        createCardRows().forEach { layout.addWidget(it.widget) }
        setLayout(layout)
    }

    private fun createCardRows(): List<WrappedBoxLayout<QHBoxLayout>> {
        val rows: List<WrappedBoxLayout<QHBoxLayout>> = buildList {
            repeat(2) { add(FieldWrapper(state.fields[it])) }
            add(WrappedBoxLayout(QHBoxLayout()).apply {
                widget.styleSheet = "border: none;"
            })
        }
        initHand(rows.last().layout)
        return rows
    }

    private fun initHand(hand: QHBoxLayout) {
        addSpacer(hand)
        state.me.hand.forEachIndexed { i, card ->
            hand.addWidget(CardWidget(card, i) { (parentWidget() as GameWidget).playCard(i) })
        }
        addSpacer(hand)
    }

    private fun addSpacer(row: QHBoxLayout) {
        row.addSpacerItem(QSpacerItem.ExpMin)
    }
}