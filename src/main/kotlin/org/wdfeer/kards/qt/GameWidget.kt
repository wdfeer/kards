package org.wdfeer.kards.qt

import io.qt.widgets.*
import org.wdfeer.kards.common.Card
import org.wdfeer.kards.common.client.ClientState

class GameWidget(state: ClientState) : QWidget() {
    init {
        val mainLayout = QVBoxLayout(this)

        val rows: List<WrappedBoxLayout<QHBoxLayout>> = buildList { repeat(3) {
            add(WrappedBoxLayout(QHBoxLayout()).apply {
                widget.styleSheet = "border: 1px solid black;"
            })
        } }
        val fields: List<QHBoxLayout> = rows.take(2).map { it.layout }

        initFields(fields, state)
        initHand(rows.last().layout, state)

        rows.forEach { mainLayout.addWidget(it.widget) }

        setLayout(mainLayout)

        windowTitle = "Card Game"
        setMinimumSize(800, 600)
        show()
    }

    private fun initFields(fields: List<QHBoxLayout>, state: ClientState) {
        state.fields.forEachIndexed { i, f ->
            f.forEach {
                fields[i].addWidget(cardToWidget(it))
            }
        }
    }

    private fun initHand(hand: QHBoxLayout, state: ClientState) {
        addSpacer(hand)
        state.myCards.forEach {
            hand.addWidget(cardToWidget(it))
        }
        addSpacer(hand)
    }

    private fun cardToWidget(card: Card): QWidget {
        val cardLabel = QLabel(card.toString(), this)
        cardLabel.setFixedSize(60, 100)
        cardLabel.styleSheet = "border: 1px solid black;"
        return cardLabel
    }

    private fun addSpacer(row: QHBoxLayout) {
        row.addSpacerItem(
            QSpacerItem(
                20,
                20,
                QSizePolicy.Policy.Expanding,
                QSizePolicy.Policy.Minimum
            )
        )
    }
}
