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

    fun Any.info(str: String) = println("[$time][INFO] ${getCallerString(this)}: $str")
    fun Any.warn(str: String) = println("[$time][WARN] ${getCallerString(this)}: $str")
    fun Any.error(str: String) = println("[$time][ERROR] ${getCallerString(this)}: $str")
}