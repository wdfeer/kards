package org.wdfeer.kards.common.util

fun <T> Collection<T>.randoms(count: Int): List<T> = this.shuffled().take(count)