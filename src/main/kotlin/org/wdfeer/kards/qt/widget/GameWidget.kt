package org.wdfeer.kards.qt.widget

import io.qt.core.QTimer
import io.qt.core.Qt
import io.qt.gui.QKeyEvent
import io.qt.widgets.QApplication
import io.qt.widgets.QVBoxLayout
import io.qt.widgets.QWidget
import org.wdfeer.kards.common.Outcome
import org.wdfeer.kards.common.client.ClientState

abstract class GameWidget(private var state: ClientState) : QWidget() {
    private val mainLayout = createLayout()
    private fun createLayout(): QVBoxLayout = QVBoxLayout(this)

    private val widgets: Array<QWidget?> = arrayOfNulls(3)
    private var rows: RowsWidget?
        get() = widgets[1] as RowsWidget?
        set(value) { widgets[1] = value }

    init {
        createWidgets()
        setLayout(mainLayout)
    }

    private fun updateState() {
        this.state = state.accessor.updateState(state).first

        widgets.forEach { it?.setParent(null) }
        createWidgets()

        if (state.myCards.isEmpty() && state.opponentCardCount == 0) {
            OutcomeMessage(Outcome.getOutcome(state.fields, 1))
            QTimer().apply {
                timeout.connect(object {
                    fun quit() = QApplication.quit()
                }, "quit()")
                interval = 1500
                start()
            }
        }
    }

    private fun createWidgets() {
        rows = RowsWidget(state)
        listOf(
            PlayerWidget("Opponent", state.opponentCardCount),
            rows!!,
            PlayerWidget("You", state.myCards.count())
        ).forEachIndexed { i, widget ->
            widgets[i] = widget
            mainLayout.addWidget(widget)
        }
    }

    override fun keyPressEvent(event: QKeyEvent?) {
        state.accessor.playCard(getDigitPressed(event?.key() ?: return)?.minus(1) ?: return)

        updateState()
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
