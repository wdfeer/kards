package org.wdfeer.kards.qt.widget.menu.sp

import io.qt.widgets.QComboBox
import org.wdfeer.kards.common.server.ai.AI
import org.wdfeer.kards.common.server.ai.AiDifficulty

class DifficultyPicker(private val pickDifficulty: (AiDifficulty) -> Unit) : QComboBox() {
    init {
        AiDifficulty.entries.forEach { addItem(it.name, it) }

        currentIndex = AiDifficulty.entries.indexOf(AI.difficulty)

        currentIndexChanged.connect(::function)
    }

    private fun function(index: Int) {
        val selectedDifficulty = itemData(index) as? AiDifficulty
        selectedDifficulty?.let { pickDifficulty(it) }
    }
}
