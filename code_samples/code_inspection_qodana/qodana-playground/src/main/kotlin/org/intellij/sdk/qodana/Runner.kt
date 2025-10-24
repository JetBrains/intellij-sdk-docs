package org.intellij.sdk.qodana

import org.intellij.sdk.qodana.service.AnotherComponent
import org.intellij.sdk.qodana.service.Component
import kotlin.collections.forEach

fun main() {
    val components = listOf(Component(), AnotherComponent())
    components.forEach { println(it) }
}
