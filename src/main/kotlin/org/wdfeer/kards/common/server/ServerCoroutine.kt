package org.wdfeer.kards.common.server

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object ServerCoroutine {
    val scope = CoroutineScope(Job())

    fun launch(action: suspend () -> Unit) = scope.launch { action() }
}