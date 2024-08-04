package org.wdfeer.kards.qt.widget.menu

import io.qt.widgets.QStackedLayout
import io.qt.widgets.QTabWidget
import io.qt.widgets.QWidget
import org.wdfeer.kards.common.Logger.info
import org.wdfeer.kards.qt.FontLoader
import org.wdfeer.kards.qt.widget.menu.mp.MultiplayerMenu
import org.wdfeer.kards.qt.widget.menu.options.OptionsMenu
import org.wdfeer.kards.qt.widget.menu.sp.SingleplayerMenu

class MenuWindow : QWidget() {
    init {
        info("Initializing")

        styleSheet = "font-family: ${FontLoader.fontFamily};"

        windowTitle = "Kards"
        setMinimumSize(360, 240)

        setLayout(QStackedLayout().apply {
            addWidget(QTabWidget().apply {
                addTab(SingleplayerMenu(), "Singleplayer")
                addTab(MultiplayerMenu(), "Multiplayer")
                addTab(OptionsMenu(), "Options")
            })
        })

        show()
    }
}