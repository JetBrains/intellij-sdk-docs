<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Remote Procedure Calls (RPC)

<primary-label ref="2025.3"/>

<link-summary>Set up RPC between shared, frontend, and backend plugin modules in Split Mode.</link-summary>

This article walks through how remote calls (RPC) are set up in [Split Mode](split_mode_and_remote_development.md) and refers to code in the publicly available [modular plugin template](https://github.com/JetBrains/intellij-platform-modular-plugin-template).

## Modules Overview

The plugin is split into three modules: **shared**, **frontend**, and **backend**.
The following sections explain how the module dependencies are configured.

### Shared Module

The shared module defines the RPC interface.
It needs the `rpc` and `kotlinx.serialization` plugins.

[`shared/build.gradle.kts`](https://github.com/JetBrains/intellij-platform-modular-plugin-template/blob/main/shared/build.gradle.kts):

```kotlin
plugins {
  id("rpc")
  id("org.jetbrains.kotlin.jvm")
  id("org.jetbrains.kotlin.plugin.serialization")
}
dependencies {
  intellijPlatform {
    intellijIdea(libs.versions.intellij.platform)
  }
}
```

### Frontend Module

The frontend module depends on `:shared` and needs the `rpc` plugin as well.

[`frontend/build.gradle.kts`](https://github.com/JetBrains/intellij-platform-modular-plugin-template/blob/main/frontend/build.gradle.kts):

```kotlin
plugins {
  id("rpc")
  id("org.jetbrains.kotlin.jvm")
  id("org.jetbrains.kotlin.plugin.serialization")
}
dependencies {
  intellijPlatform {
    intellijIdea(libs.versions.intellij.platform)
    bundledModule("intellij.platform.frontend")
    // ...
  }
  compileOnly(project(":shared"))
}
```

### Backend Module

The backend module depends on `:shared` and requires `intellij.platform.kernel.backend` and `intellij.platform.rpc.backend`.

[`backend/build.gradle.kts`](https://github.com/JetBrains/intellij-platform-modular-plugin-template/blob/main/backend/build.gradle.kts):

```kotlin
plugins {
  id("rpc")
  id("org.jetbrains.kotlin.jvm")
  id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
  intellijPlatform {
    intellijIdea(libs.versions.intellij.platform)
    bundledModule("intellij.platform.kernel.backend")
    bundledModule("intellij.platform.rpc.backend")
    bundledModule("intellij.platform.backend")
  }
  compileOnly(project(":shared"))
}
```

Also declare the backend platform module dependency in [`backend/src/main/resources/modular.plugin.backend.xml`](https://github.com/JetBrains/intellij-platform-modular-plugin-template/blob/main/backend/src/main/resources/modular.plugin.backend.xml):

```xml
<idea-plugin>
  <dependencies>
    <module name="intellij.platform.backend"/>
    <module name="intellij.platform.kernel.backend"/>
    <module name="modular.plugin.shared"/>
  </dependencies>
  <!-- ... -->
</idea-plugin>
```

## Implementing RPC Communication

### RPC Interface

Introduce the RPC interface in the shared module.

[`ChatRepositoryRpcApi`](https://github.com/JetBrains/intellij-platform-modular-plugin-template/blob/main/shared/src/main/kotlin/org/jetbrains/plugins/template/ChatRepositoryRpcApi.kt):

```kotlin
@Rpc
interface ChatRepositoryRpcApi : RemoteApi<Unit> {
  companion object {
    suspend fun getInstance(): ChatRepositoryRpcApi {
      return RemoteApiProviderService.resolve(
        remoteApiDescriptor<ChatRepositoryRpcApi>()
      )
    }
  }

  suspend fun getMessagesFlow(projectId: ProjectId): Flow<List<ChatMessageDto>>
  suspend fun sendMessage(projectId: ProjectId, messageContent: String)
}
```

RPC interface must follow the rules:

1. The interface must be annotated with [`@Rpc`](%gh-ic%/fleet/rpc/srcCommonMain/fleet/rpc/FleetApi.kt).
2. All functions must be [`suspend`](https://kotlinlang.org/docs/coroutines-basics.html#suspending-functions).
3. All parameters and return types must be `@Serializable`.
   They are essentially data transfer objects (DTOs) widely used in client-server application development.
   * Primitives, `String`, `Flow`, `Deferred` are serializable by default.
   * Enums are not serializable by default. Mark them as `@Serializable` explicitly.
   * Classes must be annotated with `@Serializable` and must contain only other serializable fields

For convenience, introduce `suspend getInstanceAsync()` so the frontend can easily acquire the instance.

### RPC Backend Implementation

Add a class implementing the RPC interface in the backend module.

[`BackendChatRepositoryRpcApi`](https://github.com/JetBrains/intellij-platform-modular-plugin-template/blob/main/backend/src/main/kotlin/org/jetbrains/plugins/template/BackendChatRepositoryRpcApi.kt):

```kotlin
class BackendChatRepositoryRpcApi : ChatRepositoryRpcApi {
  override suspend fun getMessagesFlow(projectId: ProjectId): Flow<List<ChatMessageDto>> {
    val backendProject = projectId.findProjectOrNull()
       ?: return emptyFlow()
    return BackendChatRepositoryModel.getInstance(backendProject)
      .getMessagesFlow()
  }

  override suspend fun sendMessage(
    projectId: ProjectId,
    messageContent: String
  ) {
    val backendProject = projectId.findProjectOrNull() ?: return
    return BackendChatRepositoryModel.getInstance(backendProject)
      .sendMessage(messageContent)
  }
}
```

Implement [`RemoteApiProvider`](%gh-ic%/platform/kernel/rpc.backend/src/RemoteApiProvider.kt), which registers the RPC implementation with the platform.

[`BackendRpcApiProvider`](https://github.com/JetBrains/intellij-platform-modular-plugin-template/blob/main/backend/src/main/kotlin/org/jetbrains/plugins/template/BackendRpcApiProvider.kt):

```kotlin
internal class BackendRpcApiProvider : RemoteApiProvider {
  override fun RemoteApiProvider.Sink.remoteApis() {
    remoteApi(remoteApiDescriptor<ChatRepositoryRpcApi>()) {
      BackendChatRepositoryRpcApi()
    }
  }
}
```

Register the provider in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.platform.rpc.backend.remoteApiProvider"/></include>.

[`backend/src/main/resources/modular.plugin.backend.xml`](https://github.com/JetBrains/intellij-platform-modular-plugin-template/blob/main/backend/src/main/resources/modular.plugin.backend.xml):

```xml
<idea-plugin>
  <!-- ... -->
  <extensions defaultExtensionNs="com.intellij">
    <platform.rpc.backend.remoteApiProvider
      implementation="com.example.BackendRpcApiProvider"/>
  </extensions>
</idea-plugin>
```

### Calling RPC from Frontend

[`ChatRepositoryRpcApi`](https://github.com/JetBrains/intellij-platform-modular-plugin-template/blob/main/shared/src/main/kotlin/org/jetbrains/plugins/template/ChatRepositoryRpcApi.kt) can be called on the frontend:

```kotlin
val chatRepositoryApi = ChatRepositoryRpcApi.getInstance()
chatRepositoryApi.getMessagesFlow(project.projectId())
chatRepositoryApi.sendMessage(project.projectId(), messageContent)
```

Note that all `getInstanceAsync()`, `getMessagesFlow()`, and `sendMessage()` functions are `suspend`.
They must be called in some [coroutine](kotlin_coroutines.md) context.

#### RPC Error Handling

Calling an RPC API may fail with an `RpcClientException`, for example, if there are communication problems, service is not ready, etc.
For instance, this may happen if the client tries to execute the call while the backend is not fully initialized, if a network problem occurs, or if the backend is restarted while a call is being executed.

Such errors can be mitigated by using the [`durable`](%gh-ic%/fleet/rpc/srcCommonMain/fleet/rpc/client/Durable.kt) wrapper function.

```kotlin
durable {
  ChatRepositoryRpcApi.getInstanceAsync()
    .getMessagesFlow(project.projectId())
    .collect { valueFromBackend ->
      ... // process the message
    }
}
```

It will retry the call in case discovery of the backend RPC implementation fails.

> Consider using `durable` especially when working with long-lived RPC flows, so that an exception there is handled properly and the corresponding feature keeps working.

## Serializable Types

All parameters and return types must be `@Serializable`.

> See the [Kotlin serialization documentation](https://kotlinlang.org/docs/serialization.html) for more information about `kotlinx.serialization`.

General rules:

* Primitives, `String`, `Flow`, `Deferred` are serializable by default.
* Enums are **not serializable** by default and must be annotated with `@Serializable`.
* For types like `LocalDateTime`, implement a custom `KSerializer` (see [](#data-transfer-objects)).

### Data Transfer Objects

Domain objects are not always directly serializable for transport over RPC.

In the example plugin, [`ChatMessage`](https://github.com/JetBrains/intellij-platform-modular-plugin-template/blob/main/shared/src/main/kotlin/org/jetbrains/plugins/template/ChatMessage.kt) is the domain model used on both sides, but it contains `LocalDateTime`, which is not natively supported by `kotlinx.serialization`.

The solution is a dedicated DTO class in the shared module, for example, [`ChatMessageDto`](https://github.com/JetBrains/intellij-platform-modular-plugin-template/blob/main/shared/src/main/kotlin/org/jetbrains/plugins/template/dtos.kt):

```kotlin
@Serializable
data class ChatMessageDto(
  val id: String,
  val content: String,
  val author: String,
  val isMyMessage: Boolean,
  @Serializable(with = LocalDateTimeSerializer::class)
  val timestamp: LocalDateTime,
  val type: ChatMessage.ChatMessageType
)

fun ChatMessageDto.toChatMessage(): ChatMessage { /* ... */ }
fun ChatMessage.toChatMessageDto(): ChatMessageDto { /* ... */ }
```

Custom `KSerializer` implementations can handle types that are not natively serializable, for example, [`LocalDateTimeSerializer`](https://github.com/JetBrains/intellij-platform-modular-plugin-template/blob/main/shared/src/main/kotlin/org/jetbrains/plugins/template/serializers.kt):

```kotlin
object LocalDateTimeSerializer : KSerializer<LocalDateTime> {
  private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

  override val descriptor: SerialDescriptor =
    PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

  override fun serialize(encoder: Encoder, value: LocalDateTime) {
    encoder.encodeString(value.format(formatter))
  }

  override fun deserialize(decoder: Decoder): LocalDateTime {
    return LocalDateTime.parse(decoder.decodeString(), formatter)
  }
}
```

The RPC interface uses the DTO, and each side converts to and from the domain model as needed:

* Backend: converts `ChatMessage -> ChatMessageDto` before emitting via `getMessagesFlow()`
* Frontend: converts `ChatMessageDto -> ChatMessage` after receiving via `toChatMessage()`

### ID Types

To pass classes commonly used in IntelliJ Platform through RPC, use ID types and helper functions allowing to serialize and deserialize between the full and ID types:

| Full Type                                                                                          | ID Type                                                                                            | Serialization                                        | Deserialization                                              |
|-------------------------------------------------------------------------------------------------|:------------------------------------------------------------------------------------------------|------------------------------------------------------|--------------------------------------------------------------|
| [`Project`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/Project.java)            | [`ProjectId`](%gh-ic%/platform/project/shared/src/ProjectId.kt)                                 | `Project.projectId()`<br>`Project.projectIdOrNull()` | `ProjectId.findProject()`<br>`ProjectId.findProjectOrNull()` |
| [`VirtualFile`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/VirtualFile.java)        | [`VirtualFileId`](%gh-ic%/platform/platform-impl/rpc/src/com/intellij/ide/vfs/VirtualFileId.kt) | `VirtualFile.rpcId()`                                | `VirtualFileId.virtualFile()`                                |
| [`Editor`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/Editor.java)          | [`EditorId`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/editor/impl/EditorId.kt)   | `Editor.editorId()`<br>`Editor.editorIdOrNull()`     | `EditorId.findEditor()`<br>`EditorId.findEditorOrNull()`         |
| [`Icon`](https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/swing/Icon.html) | [`IconId`](%gh-ic%/platform/platform-impl/rpc/src/com/intellij/ide/ui/icons/IconId.kt)          | `Icon.rpcId()`<br>`Icon.rpcIdOrNull()`                   | `IconId.icon()`                                                             |

Note that these objects are not fully serializable, so the frontend only receives parts of the backend object.
If possible, use only IDs on the frontend and work with the full objects on the backend side.

## RPC Examples

### Subscribing to the Backend State

[`BackendChatRepositoryModel`](https://github.com/JetBrains/intellij-platform-modular-plugin-template/blob/main/backend/src/main/kotlin/org/jetbrains/plugins/template/BackendChatRepositoryModel.kt) holds a `MutableStateFlow` of messages on the backend:

```kotlin
@Service(Service.Level.PROJECT)
class BackendChatRepositoryModel {
  private val _messages = MutableStateFlow(
     listOf(/* initial messages */)
  )

  fun getMessagesFlow(): Flow<List<ChatMessageDto>> {
    return _messages.map { messagesList ->
      messagesList.map(ChatMessage::toChatMessageDto)
    }
  }

  suspend fun sendMessage(messageContent: String) {
    // appends to _messages, triggering flow emissions
  }
}
```

The [`BackendChatRepositoryRpcApi`](https://github.com/JetBrains/intellij-platform-modular-plugin-template/blob/main/backend/src/main/kotlin/org/jetbrains/plugins/template/BackendChatRepositoryRpcApi.kt) RPC implementation simply delegates to this service:

```kotlin
class BackendChatRepositoryRpcApi : ChatRepositoryRpcApi {
  override suspend fun getMessagesFlow(projectId: ProjectId): Flow<List<ChatMessageDto>> {
    val backendProject = projectId.findProjectOrNull()
      ?: return emptyFlow()
    return BackendChatRepositoryModel.getInstance(backendProject)
      .getMessagesFlow()
  }
}
```

On the frontend, [`FrontendChatRepositoryModel`](https://github.com/JetBrains/intellij-platform-modular-plugin-template/blob/main/frontend/src/main/kotlin/org/jetbrains/plugins/template/chatApp/viewmodel/FrontendChatRepositoryModel.kt) subscribes to this flow and exposes it as a `StateFlow`:

```kotlin
@Service(Level.PROJECT)
class FrontendChatRepositoryModel(
  private val project: Project,
  coroutineScope: CoroutineScope
) : ChatRepositoryApi {
  override val messagesFlow: StateFlow<List<ChatMessage>> = flow {
    durable {
      ChatRepositoryRpcApi.getInstanceAsync()
        .getMessagesFlow(project.projectId())
        .collect { valueFromBackend ->
          val mappedValue = valueFromBackend.map { messageDto ->
            messageDto.toChatMessage()
          }
          emit(mappedValue)
      }
    }
  }.stateIn(
     coroutineScope,
     initialValue = emptyList(),
     started = SharingStarted.Lazily
  )
}
```

In the code above, `messagesFlow` is initialized with an empty list.
Since services are initialized lazily, the first subscriber will trigger the RPC connection, but the state will be `emptyList()` until the first backend emission arrives.

If this matters for an implemented feature, make sure the service is initialized before its first use — for example, via subscribing to updates from the backend inside a dedicated [`ProjectActivity`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt).

### Pushing Events from Backend to Frontend

To push events from the backend to the frontend without an explicit request, use [`ApplicationRemoteTopic`](%gh-ic%/platform/remote-topics/shared/src/com/intellij/platform/rpc/topics/ApplicationRemoteTopic.kt) or [`ProjectRemoteTopic`](%gh-ic%/platform/remote-topics/shared/src/com/intellij/platform/rpc/topics/ProjectRemoteTopic.kt) instead of a regular RPC call.

Example:

1. Define topic and event in the shared module:

```kotlin
// shared module
@Serializable
data class NewMessageEvent(
  val projectId: ProjectId,
  val messageId: String
)

val NEW_MESSAGE_TOPIC: ProjectRemoteTopic<NewMessageEvent> =
    ProjectRemoteTopic("chat.newMessage", NewMessageEvent.serializer())
```

2. Send events from the backend module:

```kotlin
// backend module
class BackendChatRepositoryModel {
  suspend fun sendMessage(messageContent: String) {
    val userMessage = chatMessageFactory.createUserMessage(messageContent)
    _messages.value += userMessage

    NEW_MESSAGE_TOPIC.broadcast(
       NewMessageEvent(project.projectId(), userMessage.id)
    )
  }
}
```

3. Handle events in the frontend module:

```kotlin
// frontend module
class NewMessageEventListener : ProjectRemoteTopicListener<NewMessageEvent> {
  override val topic = NEW_MESSAGE_TOPIC

  override fun handleEvent(event: NewMessageEvent) {
    // React to the new message event
  }
}
```

4. Register the listener via extension point in `modular.plugin.frontend.xml`:

```xml
<extensions defaultExtensionNs="com.intellij">
  <platform.rpc.projectRemoteTopicListener
    implementation="org.jetbrains.plugins.template.NewMessageEventListener"/>
</extensions>
```

> See `ApplicationRemoteTopic`/`ProjectRemoteTopic`'s docs for more details.

## FAQ

### What to do with `AbstractMethodError`?

If an error like this occurs:

```
java.lang.AbstractMethodError: Receiver class InterfaceApiClientStub
does not define or inherit an implementation of the resolved method
'abstract void foo()' of interface InterfaceApi.
```

Make sure that all the functions in the interface are `suspend`.

### How to efficiently transfer `byte[]`?

Wrap the data into a [`fleet.rpc.core.Blob`](%gh-ic%/fleet/rpc/srcCommonMain/fleet/rpc/core/Serialization.kt) for the sake of reducing the serialization overhead.

<include from="snippets.topic" element-id="missingContent"/>
