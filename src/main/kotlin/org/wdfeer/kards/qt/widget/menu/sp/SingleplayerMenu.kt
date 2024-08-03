package org.wdfeer.kards.qt.widget.menu.sp

import io.qt.widgets.*
import kotlinx.coroutines.cancel
import org.wdfeer.kards.common.server.ServerCoroutine
import org.wdfeer.kards.common.server.ServerState
import org.wdfeer.kards.common.server.ai.AI
import org.wdfeer.kards.common.server.ai.AiDifficulty
import org.wdfeer.kards.qt.widget.game.GameWindow
import org.wdfeer.kards.qt.widget.menu.Button

class SingleplayerMenu : QWidget() {
    private val cardCountPicker = CardCountPicker()

    init {
        setLayout(QVBoxLayout().apply {
            addWidget(QLabel("Choose Difficulty:"))
            addWidget(DifficultyPicker(::changeDifficulty))

            addWidget(QLabel("Card Count:"))
            addWidget(cardCountPicker)

            addSpacerItem(QSpacerItem(0, 70))
            addWidget(Button("Play", ::startGame))
        })
    }

    private fun changeDifficulty(difficulty: AiDifficulty) {
        AI.difficulty = difficulty
    }

    private fun startGame() {
        close()

        val state = ServerState(cardCountPicker.value)
        GameWindow(state.createClientState(1))

        QApplication.instance()?.aboutToQuit?.connect(fun () {
            ServerCoroutine.scope.cancel("Application quitting")
        })
    }
}