package org.wdfeer.kards.qt

import io.qt.widgets.*

class GameWidget : QWidget() {
    init {
        val mainLayout = QVBoxLayout(this)

        val cardRows = buildList { repeat(4) { add(QHBoxLayout()) } }
        val hands = listOf(cardRows[0], cardRows[3])
        val board = listOf(cardRows[1], cardRows[2])

        hands.forEach { it.addSpacerItem(QSpacerItem(20, 20, QSizePolicy.Policy.Expanding, QSizePolicy.Policy.Minimum)) }

        // Test Cards
        for (i in 1..10) {
            val cardLabel = QLabel("Card $i", this)
            cardLabel.setFixedSize(60, 100)
            cardLabel.styleSheet = "border: 1px solid black;"
            hands[i % 2].addWidget(cardLabel)
        }

        hands.forEach { it.addSpacerItem(QSpacerItem(20, 20, QSizePolicy.Policy.Expanding, QSizePolicy.Policy.Minimum)) }

        board.forEach {
            for (i in 1..4) {
                val cardLabel = QLabel("Cell", this)
                cardLabel.setFixedSize(60, 100)
                cardLabel.styleSheet = "border: 1px solid black;"
                it.addWidget(cardLabel)
            }
        }

        cardRows.forEach { mainLayout.addLayout(it) }

        setLayout(mainLayout)

        windowTitle = "Card Game"
        setMinimumSize(800, 600)
        show()
    }
}
