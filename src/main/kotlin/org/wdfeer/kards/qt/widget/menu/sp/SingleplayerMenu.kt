package org.wdfeer.kards.qt.widget.menu.sp

import io.qt.widgets.*
import kotlinx.coroutines.cancel
import org.wdfeer.kards.common.server.ServerCoroutine
import org.wdfeer.kards.common.server.ServerState
import org.wdfeer.kards.common.server.ai.AI
import org.wdfeer.kards.common.server.ai.AiDifficulty
import org.wdfeer.kards.qt.widget.abstract.Menu
import org.wdfeer.kards.qt.widget.game.GameWindow
import org.wdfeer.kards.qt.widget.menu.Button

class SingleplayerMenu : Menu() {
    private lateinit var cardCountPicker: CardCountPicker

    override fun QVBoxLayout.initLayout() {
        addWidget(QLabel("Choose Difficulty:"))
        addWidget(DifficultyPicker(::changeDifficulty))

        addWidget(QLabel("Card Count:"))
        cardCountPicker = CardCountPicker()
        addWidget(cardCountPicker)

        addSpacerItem(QSpacerItem(0, 70))
        addWidget(Button("Play", ::startGame))
    }

    private fun changeDifficulty(difficulty: AiDifficulty) {
        AI.difficulty = difficulty
    }

    private fun startGame() {
        window()?.close() ?: error("Failed to close the window!")

        val state = ServerState(cardCountPicker.value)
        GameWindow(state.createClientState(1))

        QApplication.instance()?.aboutToQuit?.connect(fun () {
            ServerCoroutine.scope.cancel("Application quitting")
        })
    }
}