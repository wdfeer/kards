package org.wdfeer.kards.qt.widget.game

import org.wdfeer.kards.common.Logger.info
import org.wdfeer.kards.qt.FontLoader
import org.wdfeer.kards.common.client.ClientState

class GameWindow(state: ClientState) : GameWidget(state) {
    init {
        info("Initializing")

        styleSheet = "font-family: ${FontLoader.fontFamily};"

        windowTitle = "Kards"
        setMinimumSize(800, 600)
        show()
    }
}