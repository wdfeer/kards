package org.wdfeer.kards.qt.widget.menu.options

import io.qt.widgets.QCheckBox
import org.wdfeer.kards.common.server.ai.AI

class LogAiTimeCheckBox : QCheckBox("Log AI evaluation time") {
    init {
        checked = AI.shouldLogTime
        stateChanged.connect(fun () {
            AI.shouldLogTime = checked
        })
    }
}