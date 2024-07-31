package org.wdfeer.kards.qt

import io.qt.gui.QFontDatabase
import io.qt.widgets.QApplication
import org.wdfeer.kards.common.Logger.error
import org.wdfeer.kards.common.Logger.info
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
                    info("\"$fontFamily\" loaded")
                } else {
                    this.error("No font families found")
                }
            } else {
                this.error("Failed to load font")
            }
        } else {
            this.error("Font resource not found")
        }
    }
}