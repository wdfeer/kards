package org.wdfeer.kards.qt.widget

import org.wdfeer.kards.common.client.ClientState

class GameWindow(state: ClientState) : GameWidget(state) {
    init {
        windowTitle = "Kards"
        setMinimumSize(800, 600)
        show()
    }
}