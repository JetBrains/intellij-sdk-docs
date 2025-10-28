// Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.intellij.sdk.qodana

import org.intellij.sdk.qodana.service.AnotherComponent
import org.intellij.sdk.qodana.service.Component
import kotlin.collections.forEach

fun main() {
    val components = listOf(Component(), AnotherComponent())
    components.forEach { println(it) }
}
