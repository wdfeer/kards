package org.wdfeer.kards.qt.widget.menu.options

import io.qt.widgets.QDoubleSpinBox

class UpdateIntervalPicker : QDoubleSpinBox() {
    init {
        value = stateUpdateInterval
        setRange(0.1, 1000.0)
        valueChanged.connect(::setStateUpdateInterval)
    }

    private fun setStateUpdateInterval() { stateUpdateInterval = value }

    companion object {
        var stateUpdateInterval: Double = 1.5

    }
}