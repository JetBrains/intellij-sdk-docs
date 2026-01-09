<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Persisting Sensitive Data

<link-summary>Storing passwords, tokens, and other sensitive data securely with Credentials Store API.</link-summary>

The Credentials Store API allows you to store sensitive user data securely, like passwords, server URLs, etc.

## How to Use
Use [`PasswordSafe`](%gh-ic%/platform/credential-store/src/ide/passwordSafe/PasswordSafe.kt) to work with credentials.

_Common Utility Method:_

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
private fun createCredentialAttributes(key: String): CredentialAttributes {
  return CredentialAttributes(generateServiceName("MySystem", key))
}
```

</tab>
<tab title="Java" group-key="java">

```java
private CredentialAttributes createCredentialAttributes(String key) {
  return new CredentialAttributes(
    CredentialAttributesKt.generateServiceName("MySystem", key)
  );
}
```

</tab>
</tabs>

### Retrieve Stored Credentials

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
val key = "serverURL" // e.g. serverURL, accountID
val attributes = createCredentialAttributes(key)
val passwordSafe = PasswordSafe.instance

val credentials = passwordSafe.get(attributes)
val password = credentials?.getPasswordAsString()

// or get password only
val passwordOnly = passwordSafe.getPassword(attributes)
```

</tab>
<tab title="Java" group-key="java">

```java
String key = null; // e.g. serverURL, accountID
CredentialAttributes attributes = createCredentialAttributes(key);
PasswordSafe passwordSafe = PasswordSafe.getInstance();

Credentials credentials = passwordSafe.get(attributes);
if (credentials != null) {
  String password = credentials.getPasswordAsString();
}

// or get password only
String password = passwordSafe.getPassword(attributes);
```

</tab>
</tabs>

> `PasswordSafe.get()` is blocking and shouldn't be called on EDT.
>
{style="warning"}

#### Retrieving Credentials in Remote Development Context

Since 2025.3, a new method was introduced in `PasswordSafe`:
```kotlin
suspend fun getAsync(attributes: CredentialAttributes): Ephemeral<Credentials>
```

Besides being coroutine-friendly, it returns "ephemeral" credentials that are valid only while the client is connected to the backend in the [Remote Development](https://www.jetbrains.com/help/idea/remote-development-overview.html) context.
When the client disconnects, the credentials are erased so that nothing can be done on the user's behalf without the user.

### Store Credentials

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
val attributes = createCredentialAttributes(key)
val credentials = Credentials(username, password)
PasswordSafe.instance.set(attributes, credentials)
```

</tab>
<tab title="Java" group-key="java">

```java
CredentialAttributes attributes = createCredentialAttributes(key);
Credentials credentials = new Credentials(username, password);
PasswordSafe.getInstance().set(attributes, credentials);
```

</tab>
</tabs>

To remove stored credentials, pass `null` for the `credentials` parameter.

> `PasswordSafe.set()` is blocking and shouldn't be called on EDT.
>
{style="warning"}

## Storage

The default storage format depends on the OS.

| OS      | Storage                                               |
|---------|-------------------------------------------------------|
| Windows | File in [KeePass][Windows] format                     |
| macOS   | Keychain using [Security Framework][macOS]            |
| Linux   | [Secret Service API][linux] using [libsecret][linux2] |

[Windows]: https://keepass.info
[macOS]: https://developer.apple.com/documentation/security/keychain_services
[linux]: https://specifications.freedesktop.org/secret-service-spec/latest/
[linux2]: https://wiki.gnome.org/Projects/Libsecret

Users can override the default behavior in <ui-path>Settings | Appearance & Behavior | System Settings | Passwords</ui-path>.

### Storage in Remote Development Context

Before 2025.3, passwords were stored on the backend side in plain text.

Since 2025.3, they are being transparently redirected to the frontend and are stored according to the local environment and settings (KeePass, keychain, etc.).
