package org.wdfeer.kards.qt

import io.qt.widgets.QSizePolicy

/**
 * Variable names consist of:
 * 1. Horizontal policy
 * 2. Vertical policy
 */
object SizePolicies {
    val ExpMin: QSizePolicy
        get() = QSizePolicy(QSizePolicy.Policy.Expanding, QSizePolicy.Policy.Minimum)
    val MinMin: QSizePolicy
        get() = QSizePolicy(QSizePolicy.Policy.Minimum, QSizePolicy.Policy.Minimum)
}