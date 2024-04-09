<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Plugin Signing

<link-summary>Plugin signing ensures that end users get unmodified plugin distribution that was actually published to JetBrains Marketplace.</link-summary>

Plugin Signing is a mechanism introduced in the 2021.2 release cycle to increase security in [JetBrains Marketplace](https://plugins.jetbrains.com) and all of our IntelliJ-based IDEs.

The Marketplace signing is designed to ensure that plugins are not modified over the course of the publishing and delivery pipeline.
If the author does not sign the plugin or has a revoked certificate, a warning dialog will appear in the IDE during installation.

On our side, we will check if the public part of a key is present, and we will verify the signature.
This is similar to the [Google upload key](https://developer.android.com/studio/publish/app-signing#generate-key) mechanism.

## How Signing Works

To be sure a file has not been modified, the file will be signed twice – first by the plugin author, then by JetBrains Marketplace.

The plugin author's sign-verify process is as follows:

- A plugin author generates a key pair ~~and uploads the public part to JetBrains Marketplace~~ (this feature is not yet available).
- A build tool signs a plugin file during the assembly process.
- The user uploads the plugin file to JetBrains Marketplace.
- JetBrains Marketplace checks if the public key is present in the user profile.
- JetBrains Marketplace verifies the signature.
- The JetBrains sign-verify process is as follows:
  - JetBrains CA is used as the source of truth here.
  - Its public part will be added to the IDE Java TrustStore, while the private part will be used only once to generate an intermediate certificate.
  - The private key of JetBrains CA is super-secret; in fact, we've already said too much.

The intermediate certificate issues a certificate that will be used to sign plugins.
This way, it will be possible to re-generate this certificate without access to JetBrains CA's super-secret private key.
The private key of the intermediate certificate is issued and kept in the AWS Certificate Manager, and no application has access to it; people's access is also limited.
So now we have an AWS-based Intermediate CA.
The public part of the intermediate certificate will be added to the plugin file together with the signing certificate.

The certificate used to sign plugins is stored securely, too.
JetBrains Marketplace uses AWS KMS as a signature provider to sign plugin files.

## Signing Methods

To provide a suitable method for plugin signing, we have introduced the [Marketplace ZIP Signer](https://github.com/JetBrains/marketplace-zip-signer) library.
It can be executed using the [`signPlugin`](tools_gradle_intellij_plugin.md#tasks-signplugin) task provided by the [](tools_gradle_intellij_plugin.md) if your project is Gradle-based.
Alternatively, a standalone [CLI Tool](#cli-tool) can be used.

Both methods require a private certificate key to be already present.

### Generate Private Key

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

At this point, the generated <path>private.pem</path> content should be provided to the [`signPlugin.privateKey`](tools_gradle_intellij_plugin.md#tasks-signplugin-privatekey) property.
Provided password should be specified as the [`signPlugin.password`](tools_gradle_intellij_plugin.md#tasks-signplugin-password) property in the [`signPlugin`](tools_gradle_intellij_plugin.md#tasks-signplugin) configuration.

As a next step, we will generate a <path>chain.crt</path> certificate chain with:

```bash
openssl req\
  -key private.pem\
  -new\
  -x509\
  -days 365\
  -out chain.crt
```

The content of the <path>chain.crt</path> file will be used for the [`signPlugin.certificateChain`](tools_gradle_intellij_plugin.md#tasks-signplugin-certificatechain) property.

> Information about generating a public key based on the private key will be added later, when uploading public keys to JetBrains Marketplace is available.

### Gradle IntelliJ Plugin

In version `1.x`, the Gradle IntelliJ Plugin provides the [`signPlugin`](tools_gradle_intellij_plugin.md#tasks-signplugin) task, which will be executed automatically right before the [`publishPlugin`](tools_gradle_intellij_plugin.md#tasks-publishplugin) task when [`signPlugin.certificateChain`](tools_gradle_intellij_plugin.md#tasks-signplugin-certificatechain) and [`signPlugin.privateKey`](tools_gradle_intellij_plugin.md#tasks-signplugin-privatekey) signing properties are specified.
Otherwise, it'll be skipped.

An example [`signPlugin`](tools_gradle_intellij_plugin.md#tasks-signplugin) task configuration may look like:

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

> Do not commit your credentials to the Version Control System! To avoid that, you may use environment variables, like:
> ```
> token.set(providers.environmentVariable("PUBLISH_TOKEN"))
> password.set(providers.environmentVariable("PRIVATE_KEY_PASSWORD"))
> ```
>
{style="warning"}

Instead of using the [`signPlugin.privateKey`](tools_gradle_intellij_plugin.md#tasks-signplugin-privatekey) and [`signPlugin.certificateChain`](tools_gradle_intellij_plugin.md#tasks-signplugin-certificatechain) properties which expect the key and certificate chain content to be provided directly, it's also possible to specify the paths to the files containing the key and certificate chain content.
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

To specify secrets like `PUBLISH_TOKEN` and values required for the [`signPlugin`](tools_gradle_intellij_plugin.md#tasks-signplugin) task, modify your Gradle configuration as follows:

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

In the <control>Run/Debug Configuration</control> for [`publishPlugin`](tools_gradle_intellij_plugin.md#tasks-publishplugin) Gradle task, provide <control>Environment Variables</control> using relevant environment variable names:

![Run/Debug Configuration Environment Variables](plugin_singing_env_variables.png)

> Note that both the private key and certificate chain are multi-line values.
> It is necessary to transform them first using Base64 encoding before providing the single-line field in the <control>Environment Variables</control> panel.
>
> [`signPlugin.privateKey`](tools_gradle_intellij_plugin.md#tasks-signplugin-privatekey) and [`signPlugin.certificateChain`](tools_gradle_intellij_plugin.md#tasks-signplugin-certificatechain) properties will automatically detect and decode the Base64-encoded values.
>
> {style="warning"}

### CLI Tool

CLI tool is required if you don't rely on the Gradle IntelliJ Plugin – i.e., when working with [Themes](developing_themes.md).

To get the latest Marketplace ZIP Signer CLI Tool, visit the [JetBrains/marketplace-zip-signer](https://github.com/JetBrains/marketplace-zip-signer/releases) GitHub Releases page.
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

Signing plugins hosted on a custom repository can be accomplished for added trust between the repository and installation.
However, unlike Marketplace, the custom repository will not re-sign the plugin with the JetBrains key.
Instead, a trusted private CA or self-signed certificate can be used to sign and validate plugins.

### Verification

Before looking at how we can sign a plugin, let's first review how verification works when a non-JetBrains certificate is used.
As of 2021.2, during verification, IntelliJ-based IDEs check if the plugin was signed by the JetBrains CA certificate or any public key provided by the user via <ui-path>Settings | Plugins | Manage Plugin Certificates</ui-path>. In 2021.2.1, a system property has been added: `intellij.plugins.truststore`, pointing to a trusted JKS TrustStore.
During verification, the plugin's public key is extracted from the signature.
The last certificate entry in the chain matched against the certificates stored in one of the storages from above.

### Using a Trusted Internal CA

If an internal CA is available, you can use this to generate certificates for signing.
When choosing this route, the certificate chain includes the root CA public key at the end of the chain.

With this approach, existing internal TrustStores may exist and could be used.
Be sure when choosing a TrustStore that the CAs are limited to the internal CAs you trust.
Using a TrustStore with public CAs can expose the users to an attack vector.

If adding a TrustStore to a user's environment is not possible, the user may also add the root CAs public key to <ui-path>Settings | Plugins | Manage Plugin Certificates</ui-path>.

### Using Self-Signed Certificates

Using a self-signed certificate is an option if no internal CAs exist.
To generate the key and public key, see: [Generate Private Key](#generate-private-key)

If providing users with a TrustStore, you can generate one with the public key using `keytool`:

```bash
keytool -import -alias IdeaPlugin -file chain.crt -keystore pluginKeystore.jks -storepass changeit
```
(note: the TrustStore password must remain `changeit`)

Otherwise, users may add the public key manually to <ui-path>Settings | Plugins | Manage Plugin Certificates</ui-path>.

## Plugin Signature Verification

To verify the signature of a plugin, you can use the [`verifyPluginSignature`](tools_gradle_intellij_plugin.md#tasks-verifypluginsignature) task.

By default, this task will use the same certificate chain as provided to the [`signPlugin`](tools_gradle_intellij_plugin.md#tasks-signplugin) task in the previous section.

To verify the signature using [CLI tool](#cli-tool), execute the `verify` command as below:

```bash
java -jar marketplace-zip-signer-cli.jar verify\
  -in "signed.zip"\
  -cert "/path/to/chain.crt"
```
