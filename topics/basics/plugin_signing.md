<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Plugin Signing

<link-summary>Plugin signing ensures that end users install an unmodified plugin distribution.</link-summary>

Plugin Signing is a mechanism introduced in the 2021.2 release cycle to increase security on the [JetBrains Marketplace](https://plugins.jetbrains.com)
and all IntelliJ-based IDEs.

The JetBrains Marketplace signing process is designed to ensure that plugins are not modified over the course of the publishing and delivery pipeline.
If the author does not sign the plugin or has a revoked certificate, a warning dialog will appear in the IDE during installation.

On our side, we will check if the public part of a key is present, and we will verify the signature.
This is similar to the [Google upload key](https://developer.android.com/studio/publish/app-signing#generate-key) mechanism.

## How Signing Works

To be sure a file has not been modified, the file will be signed twice – first by the plugin author, then by JetBrains Marketplace.

<procedure title="Signing and Verification Process">

- The plugin author generates a key pair ~~and uploads the public part to JetBrains Marketplace~~ (not available yet).
- A build tool signs the plugin distribution file during the assembly process.
- The user uploads the plugin distribution file to the [JetBrains Marketplace](https://plugins.jetbrains.com).
- JetBrains Marketplace checks if the public key is present in the user's profile.
- JetBrains Marketplace verifies the signature.
- The JetBrains signing and verification process is as follows:
  - JetBrains CA is used as the source of truth.
  - Its public part will be added to the IDE's Java TrustStore, while the private part is used only once to generate an intermediate certificate.
  - The private key of the JetBrains CA is supersecret; in fact, we've already said too much.

</procedure>

The intermediate certificate issues a certificate that will be used to sign plugins.
This way, it will be possible to re-generate this certificate without access to JetBrains CA's supersecret private key.
The private key of the intermediate certificate is issued and kept in the AWS Certificate Manager, and no application has access to it; people's access is also limited.
So now we have an AWS-based Intermediate CA.
The public part of the intermediate certificate will be added to the plugin distribution file together with the signing certificate.

The certificate used to sign plugins is stored securely, too.
JetBrains Marketplace uses AWS KMS as a signature provider to sign plugin distribution files.

## Signing Methods

For signing, the [Marketplace ZIP Signer](https://github.com/JetBrains/marketplace-zip-signer) library is used.
For Gradle-based projects, it can be used conveniently using the provided tasks via [](#gradle-integration).
Alternatively, a standalone [](#cli-tool) can be used.

Both methods require a private certificate key to be present.

### Generate Private Key

> Never commit your credentials to the Version Control System!
> Instead, use environment variables, like:
> ```
> token.set(providers.environmentVariable("PUBLISH_TOKEN"))
> password.set(providers.environmentVariable("PRIVATE_KEY_PASSWORD"))
> ```
>
{style="warning" title="Storing Secrets"}

To generate an RSA <path>private.pem</path> private key, run the `openssl genpkey` command in the terminal, as below:

```bash
openssl genpkey\
  -aes-256-cbc\
  -algorithm RSA\
  -out private_encrypted.pem\
  -pkeyopt rsa_keygen_bits:4096
```

After that, it's required to convert it into the RSA form with:

```bash
openssl rsa\
  -in private_encrypted.pem\
  -out private.pem
````

At this point, the generated <path>private.pem</path> content should be provided to the
`signPlugin.privateKey` property
([2.x](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-privateKey), [1.x](tools_gradle_intellij_plugin.md#tasks-signplugin-privatekey)).
The provided password should be specified as the `signPlugin.password` property
([2.x](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-password), [1.x](tools_gradle_intellij_plugin.md#tasks-signplugin-password)).

As a next step, we will generate a <path>chain.crt</path> certificate chain with:

```bash
openssl req\
  -key private.pem\
  -new\
  -x509\
  -days 365\
  -out chain.crt
```

The content of the <path>chain.crt</path> file will be used for the `signPlugin.certificateChain` property
([2.x](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-certificateChain), [1.x](tools_gradle_intellij_plugin.md#tasks-signplugin-certificatechain)).

> Information about generating a public key based on the private key will be added later when uploading public keys to JetBrains Marketplace becomes available.

### Gradle Integration

#### IntelliJ Platform Gradle Plugin (2.x)

The [](tools_intellij_platform_gradle_plugin.md) provides the [`signPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin) task,
which will be executed automatically right before the [`publishPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin) task
when [`signPlugin.certificateChain`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-certificateChain) and [`signPlugin.privateKey`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-privateKey) properties are specified.
Otherwise, it'll be skipped.

See [`intellijPlatform.signing`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing) for configuration reference.

##### Configuration using Files

Instead of using the `signPlugin.certificateChain` and `signPlugin.privateKey` properties which expect the certificate chain and key to be provided directly,
it's also possible to specify the paths to the files containing the certificate chain and key content.
Use [`signPlugin.certificateChainFile`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-certificateChainFile) and [`signPlugin.privateKeyFile`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-privateKeyFile) properties instead.


#### Gradle IntelliJ Plugin (1.x)
{collapsible="true" default-state="collapsed"}

<primary-label ref="Obsolete"/>

The [](tools_gradle_intellij_plugin.md) provides the [`signPlugin`](tools_gradle_intellij_plugin.md#tasks-signplugin) task, which will be executed automatically right before the [`publishPlugin`](tools_gradle_intellij_plugin.md#tasks-publishplugin) task
when [`signPlugin.certificateChain`](tools_gradle_intellij_plugin.md#tasks-signplugin-certificatechain) and [`signPlugin.privateKey`](tools_gradle_intellij_plugin.md#tasks-signplugin-privatekey) properties are specified.
Otherwise, it'll be skipped.

An example [`signPlugin`](tools_gradle_intellij_plugin.md#tasks-signplugin) task configuration may look like this:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
signPlugin {
  certificateChain.set("""
    -----BEGIN CERTIFICATE-----
    MIIElgCCAn4CCQDo83LWYj2QSTANBgkqhkiG9w0BAQsFADANMQswCQYDVQQGEwJQ
    ...
    gdZzxCN8t1EmH8kD2Yve6YKGFCRAIIzveEg=
    -----END CERTIFICATE-----
  """.trimIndent())

  privateKey.set("""
    -----BEGIN RSA PRIVATE KEY-----
    MIIJKgIBAAKCAgEAwU8awS22Rw902BmwVDDBMlTREX440BAAVM40NW3E0lJ7YTJG
    ...
    EnNBfIVFhh6khisKqTBWSEo5iS2RYJcuZs961riCn1LARztiaXL4l17oW8t+Qw==
    -----END RSA PRIVATE KEY-----
  """.trimIndent())

  password.set("8awS22%#3(4wVDDBMlTREX")
}

publishPlugin {
  token.set("perm:a961riC....l17oW8t+Qw==")
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
signPlugin {
  certificateChain = """
    -----BEGIN CERTIFICATE-----
    MIIElgCCAn4CCQDo83LWYj2QSTANBgkqhkiG9w0BAQsFADANMQswCQYDVQQGEwJQ
    ...
    gdZzxCN8t1EmH8kD2Yve6YKGFCRAIIzveEg=
    -----END CERTIFICATE-----
  """.stripIndent()

  privateKey = """
    -----BEGIN RSA PRIVATE KEY-----
    MIIJKgIBAAKCAgEAwU8awS22Rw902BmwVDDBMlTREX440BAAVM40NW3E0lJ7YTJG
    ...
    EnNBfIVFhh6khisKqTBWSEo5iS2RYJcuZs961riCn1LARztiaXL4l17oW8t+Qw==
    -----END RSA PRIVATE KEY-----
  """.stripIndent()

  password = "8awS22%#3(4wVDDBMlTREX"
}

publishPlugin {
  token = "perm:a961riC....l17oW8t+Qw=="
}
```

</tab>
</tabs>

##### Using Files

Instead of using the [`signPlugin.privateKey`](tools_gradle_intellij_plugin.md#tasks-signplugin-privatekey) and [`signPlugin.certificateChain`](tools_gradle_intellij_plugin.md#tasks-signplugin-certificatechain) properties which expect the key and certificate chain
content to be provided directly, it's also possible to specify the paths to the files containing the key and certificate chain content.
To do that, use the [`signPlugin.privateKeyFile`](tools_gradle_intellij_plugin.md#tasks-signplugin-privatekeyfile) and [`signPlugin.certificateChainFile`](tools_gradle_intellij_plugin.md#tasks-signplugin-certificatechainfile) properties instead.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
signPlugin {
  certificateChainFile.set(file("certificate/chain.crt"))
  privateKeyFile.set(file("certificate/private.pem"))
  password.set("8awS22%#3(4wVDDBMlTREX")
}

publishPlugin {
  token.set("perm:a961riC....l17oW8t+Qw==")
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
signPlugin {
  certificateChainFile = file("certificate/chain.crt")
  privateKeyFile = file("certificate/private.pem")
  password = "8awS22%#3(4wVDDBMlTREX"
}

publishPlugin {
  token = "perm:a961riC....l17oW8t+Qw=="
}
```

</tab>
</tabs>

### Provide Secrets to IDE

To avoid storing hard-coded values in the project configuration, the most suitable method for local development would be using environment variables provided within the _Run/Debug Configuration_.

To specify secrets like `PUBLISH_TOKEN` and values required for the
`signPlugin` Gradle task ([2.x](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin), [1.x](tools_gradle_intellij_plugin.md#tasks-signplugin)),
modify your Gradle configuration as follows:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
signPlugin {
  certificateChain.set(providers.environmentVariable("CERTIFICATE_CHAIN"))
  privateKey.set(providers.environmentVariable("PRIVATE_KEY"))
  password.set(providers.environmentVariable("PRIVATE_KEY_PASSWORD"))
}

publishPlugin {
  token.set(providers.environmentVariable("PUBLISH_TOKEN"))
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
signPlugin {
  certificateChain = providers.environmentVariable("CERTIFICATE_CHAIN")
  privateKey = providers.environmentVariable("PRIVATE_KEY")
  password = providers.environmentVariable("PRIVATE_KEY_PASSWORD")
}

publishPlugin {
  token = providers.environmentVariable("PUBLISH_TOKEN")
}
```

</tab>
</tabs>

In the <control>Run/Debug Configuration</control> for `publishPlugin` Gradle task
([2.x](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin), [1.x](tools_gradle_intellij_plugin.md#tasks-publishplugin)),
provide <control>Environment Variables</control> using relevant environment variable names:

![Run/Debug Configuration Environment Variables](plugin_singing_env_variables.png)

> Note that both the private key and certificate chain are multi-line values.
> It is necessary to transform them first using Base64 encoding before providing the single-line field in the <control>Environment Variables</control> panel.
>
> The corresponding Gradle task properties will automatically detect and decode the Base64-encoded values.
>
{style="warning" title="Encoding Values"}

### CLI Tool

<tldr id="tldr">

**Current Release**: %marketplace-zip-signer-version%

**GitHub**: [Releases & Changelog](https://github.com/JetBrains/marketplace-zip-signer/releases), [Issue Tracker](https://github.com/JetBrains/marketplace-zip-signer/issues)

**JetBrains Platform Forum**: [Marketplace](https://platform.jetbrains.com/c/marketplace/8) category

</tldr>

CLI tool is required if you don't rely on either Gradle plugin – i.e., when working with [Themes](developing_themes.md).

Get the latest Marketplace ZIP Signer CLI Tool version from the GitHub Releases page.
After downloading the <path>marketplace-zip-signer-cli.jar</path>, execute it as below:

```bash
java -jar marketplace-zip-signer-cli.jar sign\
  -in "unsigned.zip"\
  -out "signed.zip"\
  -cert-file "/path/to/chain.crt"\
  -key-file "/path/to/private.pem"\
  -key-pass "PRIVATE_KEY_PASSWORD"
```

## Signing for Custom Repositories

Signing plugins hosted on a [](custom_plugin_repository.md) can be achieved for added trust between the repository and installation.
However, unlike JetBrains Marketplace, the custom repository will not re-sign the plugin with the JetBrains key.
Instead, a trusted private CA or self-signed certificate can be used to sign and validate plugins.

### Verification

Before looking at how we can sign a plugin, let's first review how verification works when a non-JetBrains certificate is used.
As of 2021.2, during verification, IntelliJ-based IDEs check if the plugin was signed with the JetBrains CA certificate or any public key provided by the user via <ui-path>Settings | Plugins | Manage Plugin Certificates</ui-path>.
In 2021.2.1, a system property has been added: `intellij.plugins.truststore`, pointing to a trusted JKS TrustStore.
During verification, the plugin's public key is extracted from the signature.
The last certificate entry in the chain matched against the certificates stored in one of the storages from above.

### Using a Trusted Internal CA

If an internal CA is available, it can be used to generate certificates for signing.
When choosing this route, the certificate chain includes the root CA public key at the end of the chain.

With this approach, existing internal TrustStores may exist and could be used.
Be sure when choosing a TrustStore that the CAs are limited to the internal CAs you trust.
Using a TrustStore with public CAs can expose users to an attack vector.

If adding a TrustStore to a user's environment is not possible, the user may also add the root CAs public key to <ui-path>Settings | Plugins | Manage Plugin Certificates</ui-path>.

### Using Self-Signed Certificates

Using a self-signed certificate is an option if no internal CAs exist.
To generate the key and public key, see [](#generate-private-key).

If providing users with a TrustStore, you can generate one with the public key using `keytool`:

```bash
keytool -import -alias IdeaPlugin -file chain.crt -keystore pluginKeystore.jks -storepass changeit
```
(note: the TrustStore password must remain `changeit`)

Otherwise, users may add the public key manually to <ui-path>Settings | Plugins | Manage Plugin Certificates</ui-path>.

## Plugin Signature Verification

The signature of a plugin can be verified using the `verifyPluginSignature` Gradle task
([2.x](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginSignature), [1.x](tools_gradle_intellij_plugin.md#tasks-verifypluginsignature)).

By default, it will use the same certificate chain as provided to the `signPlugin` Gradle task (see [](#gradle-integration)).

To verify the signature using the [CLI tool](#cli-tool), execute the `verify` command as below:

```bash
java -jar marketplace-zip-signer-cli.jar verify\
  -in "signed.zip"\
  -cert "/path/to/chain.crt"
```
