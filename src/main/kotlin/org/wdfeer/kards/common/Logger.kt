package org.wdfeer.kards.common

import java.time.LocalTime
import java.time.format.DateTimeFormatter

object Logger { // TODO: save the stdout to a log file
    private val time: String get() = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))

    private fun getCallerString(caller: Any) = caller::class.simpleName

    fun Any.info(str: String) = println("[$time][INFO] ${getCallerString(this)}: $str")
    fun Any.warn(str: String) = println("[$time][WARN] ${getCallerString(this)}: $str")
    fun Any.error(str: String) = println("[$time][ERROR] ${getCallerString(this)}: $str")
}