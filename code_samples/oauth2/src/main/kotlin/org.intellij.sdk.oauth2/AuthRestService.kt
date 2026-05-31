// Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.intellij.sdk.oauth2

import com.intellij.openapi.components.service
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.http.FullHttpRequest
import io.netty.handler.codec.http.QueryStringDecoder
import org.jetbrains.ide.RestService
import org.jetbrains.io.response

private const val HTML_RESPONSE = "<p><b>Authentication Successful!</b> Close this tab and return to the IDE.</p>"

internal class AuthRestService : RestService() {
  override fun getServiceName() = SERVICE_NAME

  override fun execute(
    urlDecoder: QueryStringDecoder,
    request: FullHttpRequest,
    context: ChannelHandlerContext,
  ): String? {
    val parameters = urlDecoder.parameters()
    val state = parameters["state"]?.firstOrNull() ?: return "No authorization state found"
    val code = parameters["code"]?.firstOrNull() ?: return "No authorization code found"
    val callback = service<AuthService>().callbacks.remove(state) ?: return "No active OAuth request found"

    callback.complete(code)
    sendResponse(request, context, response("text/html", Unpooled.wrappedBuffer(HTML_RESPONSE.toByteArray())))
    return null
  }
}
