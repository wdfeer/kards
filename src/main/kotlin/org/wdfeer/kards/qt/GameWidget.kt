package org.wdfeer.kards.qt

import io.qt.widgets.*
import org.wdfeer.kards.common.client.ClientState

class GameWidget(state: ClientState) : QWidget() {
    init {
        val mainLayout = QVBoxLayout(this)

        mainLayout.addWidget(PlayerWidget("Opponent", state.opponentCardCount))

        val rows: List<WrappedBoxLayout<QHBoxLayout>> = buildList { repeat(3) {
            add(WrappedBoxLayout(QHBoxLayout()).apply {
                widget.styleSheet = "border: 1px solid black;"
            })
        } }
        initFields(rows.take(2).map { it.layout }, state)
        initHand(rows.last().layout, state)

        rows.forEach { mainLayout.addWidget(it.widget) }

        mainLayout.addWidget(PlayerWidget("You", state.myCards.count()))


        setLayout(mainLayout)

        windowTitle = "Card Game"
        setMinimumSize(800, 600)
        show()
    }

    private fun initFields(fields: List<QHBoxLayout>, state: ClientState) {
        state.fields.forEachIndexed { i, f ->
            f.forEach {
                fields[i].addWidget(CardWidget(it))
            }
        }
    }

    private fun initHand(hand: QHBoxLayout, state: ClientState) {
        addSpacer(hand)
        state.myCards.forEach {
            hand.addWidget(CardWidget(it))
        }
        addSpacer(hand)
    }

    private fun addSpacer(row: QHBoxLayout) {
        row.addSpacerItem(HExpandingSpacer())
    }
}
