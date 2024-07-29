package org.wdfeer.kards.qt.widget

import io.qt.widgets.QMessageBox
import org.wdfeer.kards.common.Outcome

class OutcomeMessage(outcome: Outcome) : QMessageBox() {
    init {
        windowTitle = when (outcome) {
            Outcome.Victory -> "Victory!"
            Outcome.Defeat -> "Defeat!"
            Outcome.Draw -> "Draw!"
        }

        text = when (outcome) {
            Outcome.Victory -> "You have won."
            Outcome.Defeat -> "You have been defeated."
            Outcome.Draw -> "The game ends in a tie."
        }

        show()
    }
}