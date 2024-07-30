package org.wdfeer.kards.qt.widget

import io.qt.core.QTimer
import io.qt.gui.QKeyEvent
import io.qt.widgets.QApplication
import io.qt.widgets.QVBoxLayout
import io.qt.widgets.QWidget
import org.wdfeer.kards.common.card.Field
import org.wdfeer.kards.common.client.Outcome
import org.wdfeer.kards.common.client.ClientState
import org.wdfeer.kards.qt.util.Input.getDigitPressed

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

        createUpdateTimer()
    }

    private fun createUpdateTimer() {
        QTimer().apply {
            timeout.connect(::updateState)
            start(1000)
        }
    }

    private fun updateState() {
        this.state = state.accessor.updateState(state).first

        widgets.forEach { it?.setParent(null) }
        createWidgets()

        if (state.me.hand.isEmpty() && state.opponent.handSize == 0) {
            val scoreDiff = Field.getScoreDiff(state.fields)
            OutcomeMessage(Outcome.getOutcome(scoreDiff), scoreDiff)
            QTimer().apply {
                timeout.connect(QApplication::quit)
                singleShot = true
                start(2000)
            }
        }
    }

    private fun createWidgets() {
        rows = RowsWidget(state)
        listOf(
            PlayerWidget(state.opponent.name, state.opponent.handSize),
            rows!!,
            PlayerWidget("You", state.me.hand.count())
        ).forEachIndexed { i, widget ->
            widgets[i] = widget
            mainLayout.addWidget(widget)
        }
    }

    fun playCard(card: Int) {
        if (state.me.hand.size <= card) throw IndexOutOfBoundsException("Card $card not found! Hand size: ${state.me.hand.size}")

        state.accessor.playCard(card)

        updateState()
    }

    override fun keyPressEvent(event: QKeyEvent?) {
        val card: Int = (getDigitPressed(event?.key()) ?: return) - 1
        playCard(card)
    }
}
