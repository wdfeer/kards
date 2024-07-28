package org.wdfeer.kards.qt

import io.qt.widgets.*
import org.wdfeer.kards.common.Card
import org.wdfeer.kards.common.GameState

class GameWidget(state: GameState) : QWidget() {
    init {
        val mainLayout = QVBoxLayout(this)

        val rows: List<QHBoxLayout> = buildList { repeat(4) { add(QHBoxLayout()) } }
        val hands = listOf(rows[0], rows[3])
        val fields = listOf(rows[1], rows[2])

        initHands(hands, state)
        initFields(fields, state)

        rows.forEach { mainLayout.addLayout(it) }

        setLayout(mainLayout)

        windowTitle = "Card Game"
        setMinimumSize(800, 600)
        show()
    }

    private fun initFields(fields: List<QHBoxLayout>, state: GameState) {
        state.players.forEachIndexed { i, player ->
            player.field.forEach {
                fields[i].addWidget(cardToWidget(it))
            }
        }
    }

    private fun initHands(hands: List<QHBoxLayout>, state: GameState) {
        hands.forEach(::addSpacer)
        state.players.forEachIndexed { i, player ->
            player.hand.forEach {
                hands[i].addWidget(cardToWidget(it))
            }
        }
        hands.forEach(::addSpacer)
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
