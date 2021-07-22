[//]: # (title: Plugin Signing)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Plugin Signing is a mechanism introduced in the 2021.2 release cycle to increase security in JetBrains Marketplace and all of our IntelliJ-based IDEs.

The Marketplace signing is designed to ensure that plugins are not modified over the course of the publishing and delivery pipeline.
If the plugin is not signed by the author or if a certificate has been revoked, a warning dialog will appear in the IDE during installation.

On our side, we will check if the public part of a key is present, and we will verify the signature. This is similar to the [Google upload key](https://developer.android.com/studio/publish/app-signing#generate-key) mechanism.

## How Signing Works

To be sure a file has not been modified, the file will be signed twice – once by the plugin author and once by JetBrains Marketplace.

The plugin author's sign-verify process is as follows:

- A user generates a key pair and uploads the public part to JetBrains Hub.
- A build tool signs a plugin file during the assembly process.
- The user uploads the plugin file to JetBrains Marketplace.
- JetBrains Marketplace checks if the public key is present in the JetBrains Hub user profile.
- JetBrains Marketplace verifies the signature.
- The JetBrains sign-verify process is as follows:

JetBrains CA is used as the source of truth here.
Its public part will be added to the product Java TrustStore, while the private part will be used only once to generate an intermediate certificate.
The private key of JetBrains CA is super-secret; in fact, we've already said too much.

- The intermediate certificate issues a certificate that will be used to sign plugins. 
  This way, it will be possible to re-generate this certificate without access to JetBrains CA's super-secret private key.
  The private key of the intermediate certificate is issued and kept in the AWS Certificate Manager, and no application has access to it; people's access is also limited. 
  So now we have an AWS-based Intermediate CA. 
  The public part of the intermediate certificate will be added to the plugin file together with the signing certificate.
- The certificate used to sign plugins is stored securely, too.
  We used the AWS Key Management Service (KMS) to generate a private key, so it can never be leaked.
  Then we prepared a certificate request (CSR) using the AWS KMS.
  Then the CSR was signed by the Intermediate CA.
  JetBrains Marketplace uses AWS KMS as a signature provider to sign plugin files.

## Signing Methods

To provide a suitable method for plugin signing, we have introduced the [Marketplace ZIP Signer](https://github.com/JetBrains/marketplace-zip-signer) library.
It can be executed using the `signPlugin` task provided by the [Gradle IntelliJ Plugin](https://github.com/JetBrains/gradle-intellij-plugin) if your project is Gradle-based or standalone CLI.

Both methods require a private certificate key to be already present.

### Generate Private Key

To generate an RSA private key, run the `openssl` command in the terminal, as below:

```Bash
openssl genrsa -aes256 -passout pass:gsahdg -out server.pass.key 4096
# you'll be asked to provide a passphrase

openssl rsa -in server.pass.key -out private.key
# repeat the same passphrase as used above
```

Create a self-signed certificate:

```Bash
openssl req -new -key private.key -out server.csr
# provide all the required information that will be used as Distinguished Name (DN)
# provide a challenge password
```

At this point, the content of the generated `private.key` should be provided to the `privateKey` property.

As a next step, we'll generate a certificate chain with:

```Bash
openssl req -key private.key -new -x509 -days 365 -out chain.crt
```

The content of the `chain.crt` will be used for the `certificateChain` property.

### Gradle IntelliJ Plugin

The Gradle IntelliJ Plugin in version `1.x` provides the `signPlugin` task which will be executed automatically right before the `publishPlugin` task as soon as `certificateChain` and `privateKey` signing properties are specified.
Otherwise, it'll be skipped.

An example `pluginSigning` configuration may look like:


<tabs>
<tab title="Gradle">

```Groovy
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
<tab title="Gradle Kotlin DSL">

```Kotlin
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
</tabs>

> Do not commit your credentials to the VCS! To avoid that, you may use environment variables, like:
> ```
> token.set(System.getenv("PUBLISH_TOKEN"))
> ```
>
{type="warning"}

### CLI Tool

Get the latest Marketplace ZIP Signer CLI Tool from the [JetBrains/marketplace-zip-signer](https://github.com/JetBrains/marketplace-zip-signer/releases) GitHub Releases page.
After downloading the `zip-signer-cli.jar`, execute it as below:

```Bash
java -jar zip-signer-cli.jar sign\
  -in "unsigned.zip"
  -out "signed.zip"
  -cert "/path/to/chain.crt"
  -key "/path/to/private.key"
```
