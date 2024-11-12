<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Persisting Sensitive Data

<link-summary>Storing passwords, tokens, and other sensitive data securely with Credentials Store API.</link-summary>

The Credentials Store API allows you to store sensitive user data securely, like passwords, server URLs, etc.

## How to Use
Use [`PasswordSafe`](%gh-ic%/platform/credential-store/src/ide/passwordSafe/PasswordSafe.kt) to work with credentials.

_Common Utility Method:_

```java
private CredentialAttributes createCredentialAttributes(String key) {
  return new CredentialAttributes(
    CredentialAttributesKt.generateServiceName("MySystem", key)
  );
}
```

### Retrieve Stored Credentials

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

### Store Credentials

```java
CredentialAttributes attributes = createCredentialAttributes(key);
Credentials credentials = new Credentials(username, password);
PasswordSafe.getInstance().set(attributes, credentials);
```

To remove stored credentials, pass `null` for the `credentials` parameter.

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
