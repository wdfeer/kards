package qt

import io.qt.core.QCoreApplication
import io.qt.widgets.QApplication

fun main(args: Array<String>) {
    QApplication.initialize(args)

    val window = GameWidget()
    window.windowTitle = "Kards"

    window.show()

    QCoreApplication.exec()
}