package org.wdfeer.kards.qt

import io.qt.core.QCoreApplication
import io.qt.widgets.QApplication

fun main(args: Array<String>) {
    QApplication.initialize(args)

    GameWidget()

    QCoreApplication.exec()
}