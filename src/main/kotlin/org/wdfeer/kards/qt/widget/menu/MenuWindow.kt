package org.wdfeer.kards.qt.widget.menu

import io.qt.widgets.QApplication
import io.qt.widgets.QVBoxLayout
import io.qt.widgets.QWidget
import kotlinx.coroutines.cancel
import org.wdfeer.kards.common.server.ServerCoroutine
import org.wdfeer.kards.common.server.ServerState
import org.wdfeer.kards.qt.widget.game.GameWindow

class MenuWindow : QWidget() {
    init {
        styleSheet = "font-family: ComicShannsMono Nerd Font;"

        windowTitle = "Kards"
        setMinimumSize(200, 200)

        setLayout(QVBoxLayout().apply {
            addWidget(PlayButton(::startGame))
        })

        show()
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