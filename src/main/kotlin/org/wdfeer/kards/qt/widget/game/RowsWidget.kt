package org.wdfeer.kards.qt.widget.game

import io.qt.widgets.QHBoxLayout
import io.qt.widgets.QVBoxLayout
import io.qt.widgets.QWidget
import org.wdfeer.kards.common.card.Card
import org.wdfeer.kards.common.card.DeltaCard
import org.wdfeer.kards.common.client.ClientState
import org.wdfeer.kards.qt.util.QSpacerItem
import org.wdfeer.kards.qt.util.WrappedBoxLayout
import org.wdfeer.kards.qt.widget.game.card.HandCardWidget

class RowsWidget(private val state: ClientState, private val deltas: Map<Long, DeltaCard>) : QWidget() {
    init {
        val layout = QVBoxLayout()
        createRows().forEach { layout.addWidget(it.widget) }
        setLayout(layout)
    }

    private fun createRows(): List<WrappedBoxLayout<QHBoxLayout>> =
        buildList {
            repeat(2) { i ->
                add(
                    FieldWrapper(state.fields[i]
                        .associateWith { card -> deltas[card.id] ?: DeltaCard() }
                        .mapKeys { it as Card })
                )
            }

            add(WrappedBoxLayout(QHBoxLayout().apply { initHand(this) }).apply {
                widget.styleSheet = "border: none;"
            })
        }

    private fun initHand(hand: QHBoxLayout) {
        addSpacer(hand)
        state.me.hand.forEachIndexed { i, card ->
            hand.addWidget(HandCardWidget(card, i) {
                val gameWidget = parentWidget() as GameWidget
                if (gameWidget.canPlayCard(i))
                    gameWidget.playCard(i)
            })
        }
        addSpacer(hand)
    }

    private fun addSpacer(row: QHBoxLayout) {
        row.addSpacerItem(QSpacerItem.ExpMin)
    }
}