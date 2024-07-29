package org.wdfeer.kards.qt.util

import io.qt.core.Qt

object Input {
    fun getDigitPressed(key: Int?): Int? {
        return when (key) {
            Qt.Key.Key_1.value() -> return 1
            Qt.Key.Key_2.value() -> return 2
            Qt.Key.Key_3.value() -> return 3
            Qt.Key.Key_4.value() -> return 4
            Qt.Key.Key_5.value() -> return 5
            Qt.Key.Key_6.value() -> return 6
            Qt.Key.Key_7.value() -> return 7
            Qt.Key.Key_8.value() -> return 8
            Qt.Key.Key_9.value() -> return 9
            else -> null
        }
    }
}