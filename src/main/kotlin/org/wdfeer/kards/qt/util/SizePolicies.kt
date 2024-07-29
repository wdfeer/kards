package org.wdfeer.kards.qt.util

import io.qt.widgets.QSizePolicy

/**
 * Variable names consist of:
 * 1. Horizontal policy
 * 2. Vertical policy
 */
object SizePolicies {
    val ExpMin: QSizePolicy
        get() = QSizePolicy(QSizePolicy.Policy.Expanding, QSizePolicy.Policy.Minimum)
    val ExpFix: QSizePolicy
        get() = QSizePolicy(QSizePolicy.Policy.Expanding, QSizePolicy.Policy.Fixed)
    val FixMin: QSizePolicy
        get() = QSizePolicy(QSizePolicy.Policy.Fixed, QSizePolicy.Policy.Minimum)
    val MinFix: QSizePolicy
        get() = QSizePolicy(QSizePolicy.Policy.Minimum, QSizePolicy.Policy.Fixed)
    val MinMin: QSizePolicy
        get() = QSizePolicy(QSizePolicy.Policy.Minimum, QSizePolicy.Policy.Minimum)
}