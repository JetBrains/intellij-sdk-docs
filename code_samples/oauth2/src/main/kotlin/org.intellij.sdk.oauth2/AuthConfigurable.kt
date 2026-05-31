// Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.intellij.sdk.oauth2

import com.intellij.openapi.components.service
import com.intellij.openapi.options.BoundConfigurable
import com.intellij.ui.components.panels.Wrapper
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.panel
import kotlinx.coroutines.launch

class AuthConfigurable : BoundConfigurable("My Plugin Auth") {

  private val authService by lazy { service<AuthService>() }

  override fun createPanel() = panel {
    val content = Wrapper()

    row {
      cell(content).align(AlignX.FILL)
    }

    authService.coroutineScope.launch {
      authService.state.collect { content.setContent(createView(it)) }
    }
  }

  private fun createView(state: AuthState) = panel {
    when (state) {
      is AuthState.Connected -> row("Username") {
        label(state.username ?: "Unknown")
        button("Logout") { authService.logout() }
      }

      is AuthState.Disconnected -> row {
        button("Login with GitHub") { authService.login() }
      }
    }
  }
}
