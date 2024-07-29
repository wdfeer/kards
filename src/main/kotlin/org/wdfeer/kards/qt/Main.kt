package org.wdfeer.kards.qt

import io.qt.widgets.QApplication
import org.wdfeer.kards.common.server.ServerState
import org.wdfeer.kards.qt.widget.GameWindow

fun main(args: Array<String>) {
    QApplication.initialize(args)

    val state = ServerState()
    GameWindow(state.createClientState(1))

    QApplication.exec()
}