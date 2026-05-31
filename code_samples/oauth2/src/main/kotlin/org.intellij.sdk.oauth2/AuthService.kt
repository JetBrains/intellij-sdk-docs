// Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.intellij.sdk.oauth2

import com.intellij.credentialStore.CredentialAttributes
import com.intellij.credentialStore.generateServiceName
import com.intellij.ide.BrowserUtil
import com.intellij.ide.passwordSafe.PasswordSafe
import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.util.Urls
import com.intellij.util.io.DigestUtil
import com.intellij.util.io.HttpRequests.post
import io.netty.handler.codec.http.QueryStringDecoder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.jetbrains.ide.BuiltInServerManager
import org.kohsuke.github.GitHubBuilder
import java.util.*
import java.util.concurrent.ConcurrentHashMap

internal const val SERVICE_NAME = "myplugin"
private const val AUTHORIZATION_URL = "https://github.com/login/oauth/authorize"
private const val ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token"
private const val CLIENT_ID = "Iv23...tt2KT"
private const val CLIENT_SECRET = "0993...1ecc"
private const val SCOPES = "read:user user:email"

sealed interface AuthState {
  data object Disconnected : AuthState
  data class Connected(val username: String? = null) : AuthState
}

@Service(Service.Level.APP)
class AuthService(val coroutineScope: CoroutineScope) {

  internal val callbacks = ConcurrentHashMap<String, CompletableDeferred<String>>()
  private val redirectUri get() = "http://localhost:${BuiltInServerManager.getInstance().port}/api/$SERVICE_NAME"
  private val credentials = CredentialAttributes(generateServiceName("MyPluginAuth", "OAuthToken"))
  private val _state = MutableStateFlow<AuthState>(AuthState.Disconnected)

  val state = _state.asStateFlow()

  @Volatile private var loginJob: Job? = null

  init {
    coroutineScope.launch {
      val token = PasswordSafe.instance.getPassword(credentials) ?: return@launch
      _state.value = AuthState.Connected(fetchUserProfile(token))
    }
  }

  fun login() {
    if (_state.value is AuthState.Connected) return
    loginJob = coroutineScope.launch {
      try {
        val token = requestToken().also(::storeToken)
        _state.value = AuthState.Connected(fetchUserProfile(token))
      } catch (e: CancellationException) {
        throw e
      } catch (t: Throwable) {
        storeToken(null)
        _state.value = AuthState.Disconnected
        thisLogger().warn("OAuth login failed", t)
      } finally {
        loginJob = null
      }
    }
  }

  fun logout() = coroutineScope.launch {
    loginJob?.cancel()
    loginJob = null
    storeToken(null)
    _state.value = AuthState.Disconnected
  }

  private fun storeToken(token: String?) = PasswordSafe.instance.setPassword(credentials, token)

  private suspend fun requestToken(): String {
    val state = UUID.randomUUID().toString()
    val codeVerifier = UUID.randomUUID().toString().padStart(43, '0') // The minimal required GitHub code verifier length is 43 characters
    val callback = CompletableDeferred<String>().also { callbacks[state] = it }

    try {
      BrowserUtil.browse(authorizationUrl(state, codeVerifier))
      return exchangeCodeForToken(callback.await(), codeVerifier)
    } finally {
      callbacks.remove(state)?.cancel()
    }
  }

  private fun authorizationUrl(state: String, codeVerifier: String) = url(
    AUTHORIZATION_URL,
    "client_id" to CLIENT_ID,
    "scope" to SCOPES,
    "state" to state,
    "redirect_uri" to redirectUri,
    "code_challenge" to codeChallenge(codeVerifier),
    "code_challenge_method" to "S256",
  )

  private suspend fun exchangeCodeForToken(code: String, codeVerifier: String) = withContext(Dispatchers.IO) {
    parseAccessToken(post(tokenUrl(code, codeVerifier), null).readString())
  }

  private fun tokenUrl(code: String, codeVerifier: String) = url(
    ACCESS_TOKEN_URL,
    "client_id" to CLIENT_ID,
    "client_secret" to CLIENT_SECRET,
    "code" to code,
    "redirect_uri" to redirectUri,
    "code_verifier" to codeVerifier,
  )

  private suspend fun fetchUserProfile(token: String): String? = withContext(Dispatchers.IO) {
    runCatching { GitHubBuilder().withOAuthToken(token).build().myself.login }
      .onFailure { thisLogger().warn("Failed to fetch user profile", it) }
      .getOrNull()
  }

  private fun url(base: String, vararg parameters: Pair<String, String>) =
    Urls.newFromEncoded(base).addParameters(mapOf(*parameters)).toExternalForm()

  private fun codeChallenge(codeVerifier: String) =
    DigestUtil.sha256().digest(codeVerifier.toByteArray())
      .let { Base64.getUrlEncoder().withoutPadding().encodeToString(it) }

  private fun parseAccessToken(body: String) =
    QueryStringDecoder("/?$body").parameters()["access_token"]?.firstOrNull()
      ?: error("Failed to exchange code for token")
}
