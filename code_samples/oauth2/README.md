# OAuth2 Login Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]

This sample shows how to add an OAuth2 login flow to a JetBrains IDE plugin.

It uses GitHub as the provider and implements the authorization-code flow with PKCE. The user signs in through the browser, GitHub redirects back to the IDE, and the plugin stores the returned access token in `PasswordSafe`.

The sample covers:

- opening the provider authorization page from the IDE,
- receiving the browser redirect through the IDE built-in HTTP server,
- validating the returned `state`,
- exchanging the authorization code with PKCE,
- storing and clearing the token through `PasswordSafe`,
- using the token to call the GitHub API.

## Running the Sample

Create a GitHub OAuth app and configure its callback URL to match the IDE built-in server endpoint:

```text
http://localhost:<built-in-server-port>/api/myplugin
```

The sample builds that redirect URI from `BuiltInServerManager.getInstance().port` and the `RestService` name `myplugin`.

Then replace the placeholder OAuth app values in [`AuthService.kt`][file:AuthService]:

```kotlin
private const val CLIENT_ID = "..."
private const val CLIENT_SECRET = "..."
```

Run the plugin with:

```bash
./gradlew runIde
```

Open **Settings | My Plugin Auth** and click **Login with GitHub**.

## How It Works

The plugin has four moving parts:

| Piece | File | Responsibility |
|-------|------|----------------|
| Plugin descriptor | [`plugin.xml`][file:pluginXml] | Registers the settings page and HTTP callback handler. |
| Settings UI | [`AuthConfigurable`][file:AuthConfigurable] | Shows login, logout, and the connected username. |
| Browser callback | [`AuthRestService`][file:AuthRestService] | Receives `state` and `code` from GitHub. |
| OAuth2 flow | [`AuthService`][file:AuthService] | Starts login, tracks callbacks, exchanges the code, stores the token, and calls the API. |

The flow is:

1. The user clicks **Login with GitHub**.
2. `AuthService` creates `state`, `code_verifier`, and `code_challenge`.
3. The plugin opens GitHub's authorization page in the browser.
4. GitHub redirects to `/api/myplugin` with `state` and a temporary authorization `code`.
5. `AuthRestService` matches `state` to a pending login request and completes it with the code.
6. `AuthService` exchanges the code and original verifier for an access token.
7. The token is stored in `PasswordSafe` and used to fetch the current GitHub username.

## PKCE and Desktop Plugins

PKCE stands for Proof Key for Code Exchange. It is the part that makes the authorization-code flow practical for desktop apps and IDE plugins.

A server-side web app can keep a client secret on the backend. A desktop plugin cannot: anything bundled into the plugin can be inspected. This sample still includes GitHub client setup fields because GitHub's OAuth app flow expects them, but do not treat a bundled client secret as secret.

The useful check is PKCE:

- the plugin sends a derived `code_challenge` when it opens the browser,
- later it sends the original `code_verifier` to the token endpoint,
- GitHub compares them before returning a token.

That means the returned authorization code is not enough on its own.

## Token Storage

Access tokens should not go into regular settings. This sample stores the token in `PasswordSafe`:

```kotlin
private val credentials =
  CredentialAttributes(generateServiceName("MyPluginAuth", "OAuthToken"))
```

The platform APIs involved are:

- [`PasswordSafe`](https://github.com/JetBrains/intellij-community/blob/master/platform/credential-store/src/ide/passwordSafe/PasswordSafe.kt)
- [`CredentialStore`](https://github.com/JetBrains/intellij-community/blob/master/platform/credential-store/src/credentialStore/CredentialStore.kt)
- [`CredentialAttributes`](https://github.com/JetBrains/intellij-community/blob/master/platform/credential-store/src/credentialStore/CredentialAttributes.kt)

For a real plugin, use a stable service name. If the plugin supports multiple accounts, store one credential per provider account.

## Production Notes

This is tutorial code. Before using the pattern in a production plugin:

- handle cancellation and denied authorization as normal paths,
- expire pending `state -> callback` entries,
- keep requested scopes narrow,
- remove the stored token on logout,
- revoke the token too if the provider supports it,
- do not rely on a bundled client secret.

## Extension Points

| Name | Implementation |
|------|----------------|
| `com.intellij.applicationConfigurable` | [`AuthConfigurable`][file:AuthConfigurable] |
| `com.intellij.httpRequestHandler` | [`AuthRestService`][file:AuthRestService] |

## Resources

- [Settings Guide | IntelliJ Platform Plugin SDK][docs:settings]
- [Persisting Sensitive Data | IntelliJ Platform Plugin SDK][docs:sensitive-data]
- [IntelliJ Platform Extension Point and Listener List][docs:extension-points]
- [RFC 6749: The OAuth 2.0 Authorization Framework][oauth:rfc6749]
- [RFC 7636: Proof Key for Code Exchange by OAuth Public Clients][oauth:rfc7636]

[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:settings]: https://plugins.jetbrains.com/docs/intellij/settings-guide.html
[docs:sensitive-data]: https://plugins.jetbrains.com/docs/intellij/persisting-sensitive-data.html
[docs:extension-points]: https://plugins.jetbrains.com/docs/intellij/intellij-platform-extension-point-list.html
[oauth:rfc6749]: https://www.rfc-editor.org/rfc/rfc6749
[oauth:rfc7636]: https://www.rfc-editor.org/rfc/rfc7636

[file:pluginXml]: ./src/main/resources/META-INF/plugin.xml
[file:AuthConfigurable]: ./src/main/kotlin/org.intellij.sdk.oauth2/AuthConfigurable.kt
[file:AuthRestService]: ./src/main/kotlin/org.intellij.sdk.oauth2/AuthRestService.kt
[file:AuthService]: ./src/main/kotlin/org.intellij.sdk.oauth2/AuthService.kt
