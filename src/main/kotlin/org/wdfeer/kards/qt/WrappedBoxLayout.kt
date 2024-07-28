package org.wdfeer.kards.qt

import io.qt.widgets.QBoxLayout
import io.qt.widgets.QWidget

class WrappedBoxLayout <T : QBoxLayout> (val widget: QWidget, val layout: T)