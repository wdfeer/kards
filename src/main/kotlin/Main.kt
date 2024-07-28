package org.wdfeer

import io.qt.core.QCoreApplication
import io.qt.widgets.QApplication
import io.qt.widgets.QPushButton
import io.qt.widgets.QWidget

fun main(args: Array<String>) {
    // Initialize the Qt application
    QApplication.initialize(args)

    // Create the main window
    val mainWindow = QWidget()
    mainWindow.setWindowTitle("Hello QtJambi")

    // Create a button and set its text
    val button = QPushButton("Click Me", mainWindow)
    button.setGeometry(50, 50, 150, 40)

    // Set the geometry of the window
    mainWindow.setGeometry(100, 100, 300, 200)

    // Show the window
    mainWindow.show()

    // Run the application event loop
    QCoreApplication.exec()
}
