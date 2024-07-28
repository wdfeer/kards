package org.wdfeer.kards.qt

import io.qt.core.QCoreApplication
import io.qt.widgets.QApplication
import org.wdfeer.kards.common.GameState

fun main(args: Array<String>) {
    QApplication.initialize(args)

    val state = GameState()
    GameWidget(state)

    QCoreApplication.exec()
}