package org.wdfeer.kards.qt.widget.game

import org.wdfeer.kards.common.client.ClientState

class GameWindow(state: ClientState) : GameWidget(state) {
    init {
        styleSheet = "font-family: ComicShannsMono Nerd Font;"

        windowTitle = "Kards"
        setMinimumSize(800, 600)
        show()
    }
}