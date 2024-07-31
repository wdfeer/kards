package org.wdfeer.kards.qt.widget.game

import io.qt.core.QTimer
import io.qt.widgets.QApplication
import io.qt.widgets.QMessageBox
import org.wdfeer.kards.common.Logger
import org.wdfeer.kards.common.client.Outcome

class OutcomeMessage(outcome: Outcome, diff: Int) : QMessageBox() {
    private val quitTimer = QTimer().apply { timeout.connect(::quit) }

    private val quitDelaySeconds = 2

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

        Logger.info("Outcome $outcome reached. Game quitting in $quitDelaySeconds seconds.")

        quitTimer.start(quitDelaySeconds * 1000)

        show()
    }

    private fun quit() {
        QApplication.quit()
    }
}