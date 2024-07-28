package org.wdfeer.kards.qt

import io.qt.widgets.QSizePolicy
import io.qt.widgets.QSpacerItem

object QSpacerItem {
    private fun create(w: Int, h: Int, policy: QSizePolicy): QSpacerItem =
        QSpacerItem(w, h, policy.horizontalPolicy(), policy.verticalPolicy())

    val ExpMin: QSpacerItem
        get() = create(20, 20, SizePolicies.ExpMin)
}
