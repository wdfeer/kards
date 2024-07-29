package org.wdfeer.kards.qt

import org.wdfeer.kards.common.client.ClientState

class GameWindow(getState: () -> ClientState) : GameWidget(getState) {
    init {
        windowTitle = "Kards"
        setMinimumSize(800, 600)
        show()
    }
}