[//]: # (title: Persisting Sensitive Data)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The Credentials Store API allows you to store sensitive user data securely, like passwords, server URLs, etc.

## How to Use
Use [`PasswordSafe`](upsource:///platform/remote-core/src/ide/passwordSafe/PasswordSafe.kt) to work with credentials.

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
    CredentialAttributes credentialAttributes = createCredentialAttributes(key);

    Credentials credentials = PasswordSafe.getInstance().get(credentialAttributes);
    if (credentials != null) {
      String password = credentials.getPasswordAsString();
    }

    // or get password only
    String password = PasswordSafe.getInstance().getPassword(credentialAttributes);

```

### Store Credentials

```java
    CredentialAttributes credentialAttributes = createCredentialAttributes(serverId); // see previous sample
    Credentials credentials = new Credentials(username, password);
    PasswordSafe.getInstance().set(credentialAttributes, credentials);
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
[linux]: https://specifications.freedesktop.org/secret-service/latest/
[linux2]: https://wiki.gnome.org/Projects/Libsecret

Users can override the default behavior in <menupath>Settings/Preferences | Appearance & Behavior | System Settings | Passwords</menupath>.
