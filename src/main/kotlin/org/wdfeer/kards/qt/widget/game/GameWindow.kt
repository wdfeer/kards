package org.wdfeer.kards.qt.widget.game

import org.wdfeer.kards.common.client.ClientState
import org.wdfeer.kards.qt.fontFamily

class GameWindow(state: ClientState) : GameWidget(state) {
    init {
        styleSheet = "font-family: $fontFamily;"

        windowTitle = "Kards"
        setMinimumSize(800, 600)
        show()
    }
}