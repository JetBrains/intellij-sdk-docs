<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Persisting Sensitive Data

<link-summary>Storing passwords, tokens, and other sensitive data securely with the Credentials Store API.</link-summary>

The Credentials Store API stores sensitive user data securely, including passwords, API keys, private keys, and server URLs.

Use [`PasswordSafe`](%gh-ic%/platform/credential-store/src/ide/passwordSafe/PasswordSafe.kt) to manage credentials.
The required elements are:

- a human-readable description of the credentials in the form of a _service name_,
- _credential attributes_ (metadata) that include a service name and an optional username or other identity,
- the actual credential value, such as a password or API key, usually as a `String`.

This is a service-like class, and the [service retrieval rules apply](plugin_services.md#retrieving-a-service).

## Human-Readable Description of Credentials with Service Names

The [`generateServiceName()`](%gh-ic%/platform/credential-store/src/credentialStore/CredentialAttributes.kt) function available in the IntelliJ SDK API helps name credentials in a consistent way so that they can be easily recognized in password managers and when users are asked to allow the IDE to access a secret.
Pass a subsystem name that identifies the plugin or its area of functionality, and a key that identifies the specific secret, such as an account name for a password or server name for an access token.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import com.intellij.credentialStore.generateServiceName

val serviceName =
    generateServiceName("My Service", "john.doe")
```
</tab>
<tab title="Java" group-key="java">

```java
import com.intellij.credentialStore.CredentialAttributesKt;

String serviceName = CredentialAttributesKt
    .generateServiceName("My Service", "john.doe");
```
</tab>
</tabs>

This will generate the following service name:

```text
IntelliJ Platform My Service - john.doe
```

For example, such a service name appears in the macOS Keychain Access application as a keychain entry name.

## Attaching Identity to Credentials with Credentials Attributes

A [`CredentialAttributes`](%gh-ic%/platform/credential-store/src/credentialStore/CredentialAttributes.kt) instance wraps credential metadata.
To store a username, account name or other identity, set it along with the service name.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import com.intellij.credentialStore.generateServiceName

//...

private fun credentialAttributesOf(username: String): CredentialAttributes {
  val serviceName = generateServiceName("My Service", username)
  return CredentialAttributes(serviceName, username)
}
```

</tab>
<tab title="Java" group-key="java">

```java
import com.intellij.credentialStore.CredentialAttributesKt;

// ...

private static CredentialAttributes credentialAttributesOf(String username) {
  String serviceName = CredentialAttributesKt
      .generateServiceName("My Service", username);
  return new CredentialAttributes(serviceName, username);
}
```

</tab>
</tabs>

## Password Management

### Storing Passwords

To store a `String`-based password, use the `PasswordSafe` instance:

1. Generate [a service name](#human-readable-description-of-credentials-with-service-names).
2. Create [credential attributes](#attaching-identity-to-credentials-with-credentials-attributes).
3. Use the `setPassword()` method with [credential attributes](#attaching-identity-to-credentials-with-credentials-attributes) and the password value.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
@Service
class PasswordService {
  suspend fun save(username: String, password: String) {
    withContext(Dispatchers.IO) {
      PasswordSafe.instance
          .setPassword(credentialAttributesOf(username), password)
    }
  }

  private fun credentialAttributesOf(username: String): CredentialAttributes {
    // see above for definition
  }
}
```
</tab>
<tab title="Java" group-key="java">

```java
@Service
public final class PasswordService {
  @RequiresBackgroundThread
  public void save(String username, String password) {
    PasswordSafe.getInstance()
        .setPassword(credentialAttributesOf(username), password);
  }

  private CredentialAttributes credentialAttributesOf(String username) {
    // see above for definition
  }
}
```
</tab>
</tabs>

> The `setPassword()` method of `PasswordSafe` is blocking and must not be invoked on EDT.
> In Kotlin, it is recommended to declare a [Light Service](plugin_services.md#light-services) with a suspending function that calls `PasswordSafe` methods on the I/O dispatcher.
{style="warning" title="Do not use `PasswordSafe` on EDT"}

The password is persisted in [OS-specific storage](#storage).

### Retrieving Passwords

To retrieve a stored password:

1. Generate [a service name](#human-readable-description-of-credentials-with-service-names).
2. Create [credential attributes](#attaching-identity-to-credentials-with-credentials-attributes).
3. Use the `getPassword()` method with these credential attributes to query for the password.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
suspend fun find(username: String): String? = withContext(Dispatchers.IO) {
  PasswordSafe.instance.getPassword(credentialAttributesOf(username))
}
```
</tab>
<tab title="Java" group-key="java">

```java
@RequiresBackgroundThread
public String find(String username) {
  return PasswordSafe.getInstance()
      .getPassword(credentialAttributesOf(username));
}
```
</tab>
</tabs>

> The `getPassword()` method of `PasswordSafe` is blocking and must not be invoked on EDT.
{style="warning" title="Do not use `PasswordSafe` on EDT"}

> `PasswordSafe` does not provide an API to retrieve all stored credentials,
> nor does it allow retrieving all stored usernames or similar identites.
> If the current user identity must be persisted, for example, authenticated user, store it separately using [](persisting_state_of_components.md).
{style="note"}

## API Key Management

API key management is broadly similar to [password management](#password-management), with two distinct changes.

1. The service name can be a constant string.
2. The `userName` property in `CredentialAttributes` is not set.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
@Service
class ApiKeyService {
  private val serviceName =
      generateServiceName("My Credentials Storage", "API Key")

  suspend fun save(apiKey: String) = withContext(Dispatchers.IO) {
    PasswordSafe.instance
        .setPassword(CredentialAttributes(serviceName), apiKey)
  }

  suspend fun load(): String? = withContext(Dispatchers.IO) {
    PasswordSafe.instance
        .getPassword(CredentialAttributes(serviceName))
  }
}
```

</tab>

<tab title="Java" group-key="java">

```java
@Service
public final class ApiKeyService {
  private static final String SERVICE_NAME = CredentialAttributesKt
      .generateServiceName("My Credentials Storage", "API Key");

  @RequiresBackgroundThread
  public void save(String apiKey) {
    PasswordSafe.getInstance()
        .setPassword(new CredentialAttributes(SERVICE_NAME), apiKey);
  }

  @RequiresBackgroundThread
  public String load() {
    return PasswordSafe.getInstance()
        .getPassword(new CredentialAttributes(SERVICE_NAME));
  }
}
```

</tab>
</tabs>

> The same rules for thread-safety apply: both methods must not be invoked on EDT.
> Running these suspending functions on the correct dispatcher ensures correctness.
{style="warning" title="Do not use `PasswordSafe` on EDT"}

## Private Keys and Other Non-String Credentials

When storing complex credentials that are not easily representable by strings, such as private keys, use map-like behavior of `PasswordSafe`.
Use `CredentialAttributes` as keys, while `Credential` instances represent values.

Use the following `PasswordSafe` methods:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
operator fun set(attributes: CredentialAttributes, credentials: Credentials?)
operator fun get(attributes: CredentialAttributes): Credentials?
```

Both functions [allow `[key]` shorthand syntax](https://kotlinlang.org/docs/map-operations.html#retrieve-keys-and-values).
</tab>
<tab title="Java" group-key="java">

```java
public void set(CredentialAttributes attributes, @Nullable Credentials credentials);
public @Nullable Credentials get(CredentialAttributes attributes);
```
</tab>
</tabs>

Construct a `Credential` instance that allows setting both the username and credential value from `String` instances, byte arrays, and char arrays.

When retrieving `Credential` values, their credentials are represented by `OneTimeString`, a wrapper of sensitive data that can be used and cleared.
Such string can be serialized to standard `String` instances, arrays of characters, or arrays of bytes.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
@Service
class PrivateKeyService {

  suspend fun save(identity: String, privateKey: PrivateKey) = withContext(Dispatchers.IO) {
    val credentialAttributes = credentialAttributesOf(identity)
    privateKey.encoded?.let { keyBytes: ByteArray ->
      PasswordSafe.instance[credentialAttributes] = Credentials(identity, keyBytes)
    }
  }

  suspend fun find(identity: String): PrivateKey? = withContext(Dispatchers.IO) {
    val credentialAttributes = credentialAttributesOf(identity)
    val credentials: Credentials? = PasswordSafe.instance[credentialAttributes]
    val keyBytes: ByteArray? = credentials?.password?.toByteArray()
    keyBytes?.let {
      loadPrivateKey(keyBytes)
    }
  }

  private fun credentialAttributesOf(identity: String): CredentialAttributes
    // see password management example

  private fun loadPrivateKey(keyBytes: ByteArray): PrivateKey {
    // implement as necessary
  }
}
```
</tab>
<tab title="Java" group-key="java">

```java
@Service
public final class PrivateKeyService {

  @RequiresBackgroundThread
  public void save(String identity, PrivateKey key) {
    byte[] keyBytes = key.getEncoded();
    if (keyBytes == null) {
      return;
    }

    CredentialAttributes attributes = createCredentialAttributes(identity);
    PasswordSafe.getInstance().set(attributes, new Credentials(identity, keyBytes));
  }

  @RequiresBackgroundThread
  public PrivateKey find(String identity) {
    CredentialAttributes credentialAttributes = createCredentialAttributes(identity);
    Credentials credentials = PasswordSafe.getInstance().get(credentialAttributes);
    if (credentials == null) {
      return null;
    }

    OneTimeString password = credentials.getPassword();
    if (password == null) {
      return null;
    }

    byte[] keyBytes = password.toByteArray();
    if (keyBytes == null) {
      return null;
    }
    return loadPrivateKey(keyBytes);
  }

  private CredentialAttributes credentialAttributesOf(String identity) {
    // see password management example
  }

  private PrivateKey loadPrivateKey(byte[] keyBytes) {
    // implement as necessary
  }
}
```
</tab>
</tabs>

## Removing Credentials

To remove stored credentials, pass `null` for the `credentials` parameter.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
PasswordSafe.instance[credentialAttributesOf(identity)] = null
```
</tab>
<tab title="Java" group-key="java">

```java
PasswordSafe.getInstance().set(credentialAttributesOf(identity), null);
```
</tab>
</tabs>

## Retrieving Credentials in Remote Development Context
<primary-label ref="2025.3"/>

For [Remote Development](https://www.jetbrains.com/help/idea/remote-development-overview.html), `PasswordSafe` provides an alternative way to retrieve credentials in Kotlin code.

```kotlin
suspend fun getAsync(attributes: CredentialAttributes): Ephemeral<Credentials>
```

Besides being coroutine-friendly, it returns "ephemeral" credentials that are valid only while the client is connected to the backend in the [Remote Development](https://www.jetbrains.com/help/idea/remote-development-overview.html) context.
When the client disconnects, the credentials are erased, preventing further actions on the user's behalf.

> The `getAsync()` and `Ephemeral` APIs are under development and experimental.
> Additionally, there are available in Kotlin only.
>
> Some aspects of it may change in future releases.
>
{style="warning" title="Experimental"}

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

Users can override the default behavior <ui-path>Settings | Appearance & Behavior | System Settings | Passwords</ui-path>.

### Storage in Remote Development Context

Since 2025.3, credentials are transparently redirected to the frontend and stored according to the local environment and settings, such as KeePass or macOS Keychain.

Before 2025.3, credentials are stored on the backend side in plain text.
