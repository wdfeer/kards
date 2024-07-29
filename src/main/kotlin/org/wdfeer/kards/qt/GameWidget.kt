package org.wdfeer.kards.qt

import io.qt.core.Qt
import io.qt.gui.QKeyEvent
import io.qt.widgets.*
import org.wdfeer.kards.common.client.ClientState

class GameWidget(private val state: ClientState) : QWidget() {
    private val mainLayout = QVBoxLayout(this)
    private var widgets: List<QWidget> = emptyList()

    init {
        widgets = createWidgets()
        widgets.forEach { mainLayout.addWidget(it) }
        setLayout(mainLayout)

        windowTitle = "Kards"
        setMinimumSize(800, 600)
        show()
    }

    private fun createWidgets(): List<QWidget> = mutableListOf(
        PlayerWidget("Opponent", state.opponentCardCount)
    ) + initCardRows().map { it.widget } + PlayerWidget("You", state.myCards.count())

    private fun updateCards() {
        val newWidgets = createWidgets()
        newWidgets.forEachIndexed { i, widget ->
            mainLayout.replaceWidget(widgets[i], widget)
        }
        widgets = newWidgets
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

    private fun initCardRows(): List<WrappedBoxLayout<QHBoxLayout>> {
        val rows: List<WrappedBoxLayout<QHBoxLayout>> = buildList { repeat(3) {
            add(WrappedBoxLayout(QHBoxLayout()).apply {
                widget.styleSheet = "border: 1px solid black;"
            })
        } }
        initFields(rows.take(2).map { it.layout })
        initHand(rows.last().layout)
        return rows
    }

    private fun initFields(fields: List<QHBoxLayout>) {
        state.fields.forEachIndexed { i, f ->
            f.forEach {
                fields[i].addWidget(CardWidget(it))
                fields[i].objectName = "field$i"
            }
        }
    }

    private fun initHand(hand: QHBoxLayout) {
        addSpacer(hand)
        state.myCards.forEach {
            hand.addWidget(CardWidget(it))
        }
        hand.objectName = "myHand"
        addSpacer(hand)
    }

    private fun addSpacer(row: QHBoxLayout) {
        row.addSpacerItem(QSpacerItem.ExpMin)
    }
}
