package org.wdfeer.kards.qt

import io.qt.widgets.QApplication
import org.wdfeer.kards.common.Logger.info
import org.wdfeer.kards.qt.widget.menu.MenuWindow

private class Main
private val loggerObject = Main()

fun main(args: Array<String>) {
    loggerObject.info("Kards starting")
    QApplication.initialize(args)

    FontLoader.loadFont()

    MenuWindow()

    QApplication.exec()
}

