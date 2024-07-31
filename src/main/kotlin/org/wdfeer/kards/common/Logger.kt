package org.wdfeer.kards.common

import java.time.LocalTime
import java.time.format.DateTimeFormatter

object Logger { // TODO: save the stdout to a log file
    private val time: String get() = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))

    fun info(str: String) = println("[$time][INFO] $str")
    fun warn(str: String) = println("[$time][WARN] $str")
    fun error(str: String) = println("[$time][ERROR] $str")
}