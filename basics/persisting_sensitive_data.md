---
title: Persisting Sensitive Data
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The Credentials Store API allows you to securely store sensitive user data, like passwords, server URLs, etc.

## How to Use
Use [`PasswordSafe`](upsource:///platform/platform-api/src/com/intellij/ide/passwordSafe/PasswordSafe.kt) to work with credentials.

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
    
    private CredentialAttributes createCredentialAttributes(String key) {
        return new CredentialAttributes(CredentialAttributesKt.generateServiceName("MySystem", key));        
    }    
```

### Store Credentials

```java
    CredentialAttributes credentialAttributes = createCredentialAttributes(serverId); // see previous sample
    Credentials credentials = new Credentials(username, password);
    PasswordSafe.getInstance().set(credentialAttributes, credentials);
```

To remove stored credentials, pass `null` for `credentials` parameter.

## Storage
Default storage format depends on OS.

| OS      | Storage |
|---------|---------|
| Windows | File in [KeePass](https://keepass.info) format |
| macOS   | Keychain using [Security Framework](https://developer.apple.com/documentation/security/keychain_services) |
| Linux   | [Secret Service API](https://specifications.freedesktop.org/secret-service/latest/) using [libsecret](https://wiki.gnome.org/Projects/Libsecret) |

Users can override the default behavior in Preferences \| Appearance & Behavior \| System Settings \| Passwords.