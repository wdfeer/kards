package org.wdfeer.kards.qt.widget.game

import io.qt.core.QTimer
import io.qt.gui.QKeyEvent
import io.qt.widgets.QApplication
import io.qt.widgets.QVBoxLayout
import io.qt.widgets.QWidget
import org.wdfeer.kards.common.card.Field
import org.wdfeer.kards.common.client.ClientApp
import org.wdfeer.kards.common.client.ClientState
import org.wdfeer.kards.common.client.Outcome
import org.wdfeer.kards.qt.util.Input.getDigitPressed

open class GameWidget(override var state: ClientState) : QWidget(), ClientApp {
    override var oldState: ClientState? = null

    private val mainLayout = createLayout()
    private fun createLayout(): QVBoxLayout = QVBoxLayout(this)

    private val widgets: Array<QWidget?> = arrayOfNulls(3)
    private var rows: RowsWidget?
        get() = widgets[1] as RowsWidget?
        set(value) { widgets[1] = value }

    init {
        redrawState()
        setLayout(mainLayout)

        QTimer().apply {
            timeout.connect(::updateState)
            start(1000)

            QApplication.instance()?.aboutToQuit?.connect(this::stop)
        }
    }

    private var outcomeMessage: OutcomeMessage? = null

    final override fun redrawState() {
        widgets.forEach { it?.setParent(null) }
        createWidgets()

        if (outcomeMessage == null && hasGameEnded()) {
            val scoreDiff = Field.getScoreDiff(state.fields)
            outcomeMessage = OutcomeMessage(Outcome.getOutcome(scoreDiff), scoreDiff)
        }
    }

    private fun createWidgets() {
        rows = RowsWidget(state, getDeltas())
        listOf(
            PlayerWidget(state.opponent.name, state.opponent.playing, state.opponent.handSize),
            rows!!,
            PlayerWidget("You", state.me.playing, state.me.hand.count())
        ).forEachIndexed { i, widget ->
            widgets[i] = widget
            mainLayout.addWidget(widget)
        }
    }

    override fun keyPressEvent(event: QKeyEvent?) {
        val card: Int = (getDigitPressed(event?.key()) ?: return) - 1

        if (canPlayCard(card)) playCard(card)
    }
}
