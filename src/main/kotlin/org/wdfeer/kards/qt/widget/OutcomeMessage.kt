package org.wdfeer.kards.qt.widget

import io.qt.widgets.QMessageBox
import org.wdfeer.kards.common.Outcome

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
    }
}