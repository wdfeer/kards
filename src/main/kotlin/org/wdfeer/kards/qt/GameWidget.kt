package org.wdfeer.kards.qt

import io.qt.core.Qt
import io.qt.gui.QKeyEvent
import io.qt.widgets.*
import org.wdfeer.kards.common.client.ClientState

abstract class GameWidget(private val state: ClientState) : QWidget() {
    private val mainLayout = createLayout()
    private fun createLayout(): QVBoxLayout = QVBoxLayout(this)

    init {
        createWidgets().forEach { mainLayout.addWidget(it) }
        setLayout(mainLayout)
    }

    private fun createWidgets(): List<QWidget> = mutableListOf(
        PlayerWidget("Opponent", state.opponentCardCount),
        RowsWidget(state),
        PlayerWidget("You", state.myCards.count())
    )

    private fun updateCards() {
        children().forEach { mainLayout.removeWidget(if (it is QWidget) it else return@forEach) }
        createWidgets().forEach { mainLayout.addWidget(it) }
    }

    override fun keyPressEvent(event: QKeyEvent?) {
        state.playCard(getDigitPressed(event?.key() ?: return)?.minus(1) ?: return)
        updateCards()
    }

    private fun getDigitPressed(key: Int): Int? {
        return when (key) {
            Qt.Key.Key_1.value() -> return 1
            Qt.Key.Key_2.value() -> return 2
            Qt.Key.Key_3.value() -> return 3
            else -> null
        }
    }
}
