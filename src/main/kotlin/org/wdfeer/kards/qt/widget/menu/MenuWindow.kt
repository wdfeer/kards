package org.wdfeer.kards.qt.widget.menu

import io.qt.widgets.*
import kotlinx.coroutines.cancel
import org.wdfeer.kards.qt.FontLoader
import org.wdfeer.kards.common.server.ServerCoroutine
import org.wdfeer.kards.common.server.ServerState
import org.wdfeer.kards.common.server.ai.AI
import org.wdfeer.kards.common.server.ai.AiDifficulty
import org.wdfeer.kards.qt.widget.game.GameWindow

class MenuWindow : QWidget() {
    init {
        styleSheet = "font-family: ${FontLoader.fontFamily};"

        windowTitle = "Kards"
        setMinimumSize(200, 200)

        setLayout(QVBoxLayout().apply {
            addWidget(QLabel("Choose Difficulty:"))
            addWidget(DifficultyPicker(::changeDifficulty))

            addSpacerItem(QSpacerItem(0, 70))
            addWidget(PlayButton(::startGame))
        })

        show()
    }

    private fun changeDifficulty(difficulty: AiDifficulty) {
        AI.difficulty = difficulty
    }

    private fun startGame() {
        close()

        val state = ServerState()
        GameWindow(state.createClientState(1))

        QApplication.instance()?.aboutToQuit?.connect(fun () {
            ServerCoroutine.scope.cancel("Application quitting")
        })
    }
}