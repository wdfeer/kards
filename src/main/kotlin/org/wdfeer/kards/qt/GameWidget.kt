package org.wdfeer.kards.qt

import io.qt.widgets.*

class GameWidget : QWidget() {
    init {
        // Set up the main layout
        val mainLayout = QVBoxLayout(this)

        // Create the top and bottom layouts for players' hands
        val topHandLayout = QHBoxLayout()
        val bottomHandLayout = QHBoxLayout()

        // Create the middle grid layout for the board
        val boardLayout = QGridLayout()

        // Example of adding some dummy cards for demonstration
        // You would replace these with your actual card widgets
//        for (i in 1..5) {
//            val cardLabel = QLabel("Card $i", this)
//            cardLabel.setFixedSize(60, 100)
//            cardLabel.styleSheet = "border: 1px solid black;"
//
//            topHandLayout.addWidget(cardLabel)
//            bottomHandLayout.addWidget(cardLabel)
//        }

        // Add spacers to push the hands to the edges
        topHandLayout.addSpacerItem(QSpacerItem(20, 20, QSizePolicy.Policy.Expanding, QSizePolicy.Policy.Minimum))
        bottomHandLayout.addSpacerItem(QSpacerItem(20, 20, QSizePolicy.Policy.Expanding, QSizePolicy.Policy.Minimum))

        // Add rows for the board
        for (row in 0..1) {
            for (col in 0..4) {
                val cardLabel = QLabel("Board $row, $col", this)
                cardLabel.setFixedSize(60, 100)
                cardLabel.styleSheet = "border: 1px solid black;"
                boardLayout.addWidget(cardLabel, row, col)
            }
        }

        // Add layouts to the main layout
        mainLayout.addLayout(topHandLayout)
        mainLayout.addLayout(boardLayout)
        mainLayout.addLayout(bottomHandLayout)

        // Set the layout for this widget
        setLayout(mainLayout)

        // Set the window title and size (optional)
        windowTitle = "Card Game"
        setMinimumSize(800, 600)
        show()
    }
}
