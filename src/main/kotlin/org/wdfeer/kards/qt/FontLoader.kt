package org.wdfeer.kards.qt

import io.qt.gui.QFontDatabase
import io.qt.widgets.QApplication
import org.wdfeer.kards.common.Logger
import java.io.InputStream

object FontLoader {
    private const val FONT_PATH = "/font/default.otf"

    var fontFamily: String = ""

    fun loadFont() {
        val fontStream: InputStream? = object {}.javaClass.getResourceAsStream(FONT_PATH)

        if (fontStream != null) {
            val fontId = QFontDatabase.addApplicationFontFromData(fontStream.readAllBytes())
            if (fontId != -1) {
                val fontFamilies = QFontDatabase.applicationFontFamilies(fontId)
                if (fontFamilies.isNotEmpty()) {
                    fontFamily = fontFamilies[0]
                    QApplication.setFont(fontFamily)
                    Logger.info("Font \"$fontFamily\" loaded")
                } else {
                    Logger.error("No font families found")
                }
            } else {
                Logger.error("Failed to load font")
            }
        } else {
            Logger.error("Font resource not found")
        }
    }
}