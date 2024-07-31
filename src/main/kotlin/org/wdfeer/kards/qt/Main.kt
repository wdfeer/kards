package org.wdfeer.kards.qt

import io.qt.gui.QFontDatabase
import io.qt.widgets.QApplication
import org.wdfeer.kards.qt.widget.menu.MenuWindow
import java.io.InputStream

fun main(args: Array<String>) {
    QApplication.initialize(args)

    loadFont()

    MenuWindow()

    QApplication.exec()
}

var fontFamily: String = ""
private fun loadFont() {
    val fontPath = "/font/default.otf"
    val fontStream: InputStream? = object {}.javaClass.getResourceAsStream(fontPath)

    if (fontStream != null) {
        val fontId = QFontDatabase.addApplicationFontFromData(fontStream.readAllBytes())
        if (fontId != -1) {
            println("Font loaded successfully with ID $fontId")
            val fontFamilies = QFontDatabase.applicationFontFamilies(fontId)
            if (fontFamilies.isNotEmpty()) {
                fontFamily = fontFamilies[0]
                QApplication.setFont(fontFamily)
                println("Font set to: $fontFamily")
            } else {
                error("No font families found")
            }
        } else {
            error("Failed to load font")
        }
    } else {
        error("Font resource not found")
    }
}