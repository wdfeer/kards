package org.wdfeer.kards.qt.widget

import io.qt.core.QTimer
import io.qt.widgets.QApplication
import io.qt.widgets.QMessageBox
import org.wdfeer.kards.common.client.Outcome

class OutcomeMessage(outcome: Outcome, diff: Int) : QMessageBox() {
    init {
        windowTitle = when (outcome) {
            Outcome.Victory -> "Victory!"
            Outcome.Defeat -> "Defeat!"
            Outcome.Draw -> "Draw!"
        }

        text = when (outcome) {
            Outcome.Victory -> "You have won by $diff points."
            Outcome.Defeat -> "You have been defeated by ${-diff} points."
            Outcome.Draw -> "The game ends in a tie."
        }

        show()


        QTimer().apply {
            timeout.connect(QApplication::quit)
            singleShot = true
            start(2000)
        }
    }
}