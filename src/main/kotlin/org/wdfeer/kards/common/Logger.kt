package org.wdfeer.kards.common

import java.time.LocalTime
import java.time.format.DateTimeFormatter

object Logger { // TODO: save the stdout to a log file
    private val time: String get() = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))

    private fun getCallerString(caller: Any) = caller::class.simpleName

    fun <T : Any> logTime(caller: Any, getMessage: (ms: Long) -> String, function: () -> T): T {
        val startTime = System.currentTimeMillis()

        val result = function()

        val elapsedTime = System.currentTimeMillis() - startTime

        val message = getMessage(elapsedTime)

        if (elapsedTime > 1000)
            caller.warn(message)
        else
            caller.info(message)

        return result
    }

    fun <T : Any> logTimeIf(condition: Boolean, caller: Any, getMessage: (ms: Long) -> String, function: () -> T): T =
        if (condition) logTime(caller, getMessage, function) else function()

    fun Any.info(str: String) = println("[$time][INFO] ${getCallerString(this)}: $str")
    fun Any.warn(str: String) = printlnColor("[$time][WARN] ${getCallerString(this)}: $str", Color.YELLOW)
    fun Any.error(str: String) = printlnColor("[$time][ERROR] ${getCallerString(this)}: $str", Color.RED)

    private fun printlnColor(str: String, color: Color) {
        val colorCode = when (color) {
            Color.YELLOW -> "\u001B[33m"
            Color.RED -> "\u001B[31m"
        }
        println("$colorCode$str\u001B[0m")
    }

    private enum class Color {
        YELLOW,
        RED
    }
}