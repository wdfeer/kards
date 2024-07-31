package org.wdfeer.kards.qt

import io.qt.widgets.QApplication
import org.wdfeer.kards.common.Logger
import org.wdfeer.kards.qt.widget.menu.MenuWindow

fun main(args: Array<String>) {
    Logger.info("Kards starting")
    QApplication.initialize(args)

    FontLoader.loadFont()

    MenuWindow()

    QApplication.exec()
}