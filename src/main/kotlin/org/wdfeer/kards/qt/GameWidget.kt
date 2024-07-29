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

    private lateinit var rows: RowsWidget

    private fun createWidgets(): List<QWidget> {
        rows = RowsWidget(state)
        return mutableListOf(
            PlayerWidget("Opponent", state.opponentCardCount),
            rows,
            PlayerWidget("You", state.myCards.count())
        )
    }

    override fun keyPressEvent(event: QKeyEvent?) {
        state.accessor.playCard(getDigitPressed(event?.key() ?: return)?.minus(1) ?: return)

        val newRows = RowsWidget(state)
        mainLayout.replaceWidget(rows, newRows)
        rows.setParent(null)
        rows = newRows
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
