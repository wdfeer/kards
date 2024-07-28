package org.wdfeer.kards.qt

import io.qt.core.QCoreApplication
import io.qt.widgets.QApplication
import org.wdfeer.kards.common.server.ServerState

fun main(args: Array<String>) {
    QApplication.initialize(args)

    val state = ServerState()
    GameWidget(state.createClientState(1))

    QCoreApplication.exec()
}