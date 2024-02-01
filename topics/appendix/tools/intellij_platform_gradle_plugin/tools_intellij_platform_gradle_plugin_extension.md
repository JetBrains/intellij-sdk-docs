<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ Platform Extension

<link-summary>IntelliJ Platform Gradle Plugin extension.</link-summary>

The _IntelliJ Platform Gradle Plugin_ introduces a top-level `intellijPlatform` extension.
It consists of sections dedicated to the general Gradle plugin configuration, <path>plugin.xml</path> definition, publishing, signing, and verifying of the output plugin for IntelliJ-based IDEs.

## IntelliJ Platform
{#intellijPlatform}

After the IntelliJ Platform Gradle Plugin is applied, the `intellijPlatform` extension can be used to configure the plugin and common settings of the provided tasks.

**Example:**

```kotlin
intellijPlatform {
  instrumentCode.set(true)
  buildSearchableOptions.set(true)
  sandboxContainer.set("...")

  pluginConfiguration { ... }
  publishing { ... }
  signing { ... }
  verifyPlugin { ... }
}
```

### instrumentCode
{#intellijPlatform-instrumentCode}

> Not implemented.
>
{style="warning"}

Enables the compiled classes instrumentation.
The compiled code will be enhanced with:
- nullability assertions
- post-processing of forms created by IntelliJ GUI Designer

Controls the execution of the [](tools_intellij_platform_gradle_plugin_tasks.md#instrumentCode) task.

{style="narrow"}
Type
: `Property<Boolean>`

Default value
: `true`

See also:
- [Tasks: `instrumentCode`](tools_intellij_platform_gradle_plugin_tasks.md#instrumentCode)


### buildSearchableOptions
{#intellijPlatform-buildSearchableOptions}

Builds an index of UI components (searchable options) for the plugin.
Controls the execution of the [](tools_intellij_platform_gradle_plugin_tasks.md#buildSearchableOptions) task.

{style="narrow"}
Type
: `Property<Boolean>`

Default value
: `true`

See also:
- [Tasks: `buildSearchableOptions`](tools_intellij_platform_gradle_plugin_tasks.md#buildSearchableOptions)
- [Build Features: `noSearchableOptionsWarning`](tools_intellij_platform_gradle_plugin_build_features.md#noSearchableOptionsWarning)


### sandboxContainer
{#intellijPlatform-sandboxContainer}

The path to the sandbox container where tests and IDE instances read and write data.

{style="narrow"}
Type
: `DirectoryProperty`

Default value
: <path>build/<Sandbox.CONTAINER></path>

See also:
- [Tasks: `prepareSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox)
- [Task Awares: `SandboxAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SandboxAware)


## Plugin Configuration
{#intellijPlatform-pluginConfiguration}

Configures the plugin definition and stores in the `plugin.xml` file.
Data provided to the `intellijPlatform.pluginConfiguration {}` extension is passed to the [](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml) task, which overrides the <path>plugin.xml</path> file with new values.

**Example:**

```kotlin
intellijPlatform {
  ...

  pluginConfiguration {
    id.set("my-plugin-id")
    name.set("My Awesome Plugin")
    version.set("1.0.0")
    description.set("It's an awesome plugin!")
    changeNotes.set(
      """
      A descriptive release note...
      """.trimIndent()
    )

    productDescriptor { ... }
    ideaVersion { ... }
    vendor { ... }
  }
}
```

See also:
- [](#intellijPlatform-pluginConfiguration-productDescriptor)
- [](#intellijPlatform-pluginConfiguration-ideaVersion)
- [](#intellijPlatform-pluginConfiguration-vendor)


### id
{#intellijPlatform-pluginConfiguration-id}

The plugin's unique identifier.
This should mirror the structure of fully qualified Java packages and must remain distinct from the IDs of existing plugins.
This ID is a technical descriptor used not only within the IDE, but also on [JetBrains Marketplace](https://plugins.jetbrains.com/).

Please restrict input to characters, numbers, and `.`/`-`/`_` symbols , and aim for a concise length.

The entered value will populate the `<id>` element.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.pluginId`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-pluginId)
- [Plugin Configuration File: `id`](plugin_configuration_file.md#idea-plugin__id)


### name
{#intellijPlatform-pluginConfiguration-name}

The plugin display name, visible to users (Title Case).

The inputted value will be used to populate the `<name>` element.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.pluginName`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-pluginName)
- [Plugin Configuration File: `name`](plugin_configuration_file.md#idea-plugin__name)


### version
{#intellijPlatform-pluginConfiguration-version}

The plugin version, presented in the Plugins settings dialog and on its JetBrains Marketplace page.

For plugins uploaded to the JetBrains Marketplace, semantic versioning must be adhered to.

The specified value will be used as an `<version>` element.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.pluginVersion`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-pluginVersion)
- [Plugin Configuration File: `version`](plugin_configuration_file.md#idea-plugin__version)


### description
{#intellijPlatform-pluginConfiguration-description}

The plugin description, which is presented on the JetBrains Marketplace plugin page and in the Plugins settings dialog.
Basic HTML elements such as text formatting, paragraphs, and lists are permitted.

The description content is automatically enclosed by `<![CDATA[... ]]>`.

The supplied value will populate the `<description>` element.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.pluginDescription`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-pluginDescription)
- [Plugin Configuration File: `description`](plugin_configuration_file.md#idea-plugin__description)


### changeNotes
{#intellijPlatform-pluginConfiguration-changeNotes}

A concise summary of new features, bug fixes, and alterations provided in the latest plugin version.
These change notes will be displayed on the JetBrains Marketplace plugin page and in the Plugins settings dialog.
Basic HTML elements such as text formatting, paragraphs, and lists are permitted.

The change notes content is automatically encapsulated in `<![CDATA[... ]]>`.

The inputted value will populate the `<change-notes>` element.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.changeNotes`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-changeNotes)
- [Plugin Configuration File: `change-notes`](plugin_configuration_file.md#idea-plugin__change-notes)


## Product Descriptor
{#intellijPlatform-pluginConfiguration-productDescriptor}

A part of the [](#intellijPlatform-pluginConfiguration) which describes the `product-descriptor` element.

**Example:**

```kotlin
intellijPlatform {
  ...

  pluginConfiguration {
    ...

    productDescriptor {
      code.set("MY_CODE")
      releaseDate.set("20240217")
      releaseVersion.set("20241")
      optional.set(false)
    }
  }
}
```

See also:
- [How to add required parameters for paid plugins](https://plugins.jetbrains.com/docs/marketplace/add-required-parameters.html)

### code
{#intellijPlatform-pluginConfiguration-productDescriptor-code}

The product code for the plugin, used in the JetBrains Sales System, needs to be pre-approved by JetBrains and must adhere to [specified requirements](https://plugins.jetbrains.com/docs/marketplace/obtain-a-product-code-from-jetbrains.html).

The given value will be utilized as a `<product-descriptor code="">` element attribute.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.productDescriptorCode`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-productDescriptorCode)
- [Plugin Configuration File: `product-descriptor`](plugin_configuration_file.md#idea-plugin__product-descriptor)


### releaseDate
{#intellijPlatform-pluginConfiguration-productDescriptor-releaseDate}

The release date of the major version, formatted as `YYYYMMDD`.

The supplied value will be used to populate the `<product-descriptor release-date="">` element attribute.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.productDescriptorReleaseDate`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-productDescriptorReleaseDate)
- [Plugin Configuration File: `product-descriptor`](plugin_configuration_file.md#idea-plugin__product-descriptor)


### releaseVersion
{#intellijPlatform-pluginConfiguration-productDescriptor-releaseVersion}

The major version, represented in a specific numerical format.

The given value will be used as the `<product-descriptor release-version="">` element attribute.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.productDescriptorReleaseVersion`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-productDescriptorReleaseVersion)
- [Plugin Configuration File: `product-descriptor`](plugin_configuration_file.md#idea-plugin__product-descriptor)


### optional
{#intellijPlatform-pluginConfiguration-productDescriptor-optional}

The boolean value that indicates if the plugin is a [Freemium](https://plugins.jetbrains.com/docs/marketplace/freemium.html) plugin.

The inputted value will be used for the `<product-descriptor optional="">` element attribute.

{style="narrow"}
Type
: `Property<Boolean>`

Default value
: `false`

See also:
- [Tasks: `patchPluginXml.productDescriptorOptional`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-productDescriptorOptional)
- [Plugin Configuration File: `product-descriptor`](plugin_configuration_file.md#idea-plugin__product-descriptor)


## IDEA Version
{#intellijPlatform-pluginConfiguration-ideaVersion}

Configures the `idea-version` section of the plugin.

A part of the [](#intellijPlatform-pluginConfiguration) which describes the `idea-version` element.

**Example:**

```kotlin
intellijPlatform {
  ...

  pluginConfiguration {
    ...

    ideaVersion {
      sinceBuild.set("241")
      untilBuild.set("241.*")
    }
  }
}
```

### sinceBuild
{#intellijPlatform-pluginConfiguration-ideaVersion-sinceBuild}

The earliest IDE version that is compatible with the plugin.

The supplied value will be utilized as the `<idea-version since-build=""/>` element attribute.

The default value is set to the `MAJOR.MINOR` version based on the currently selected IntelliJ Platform, like `233.12345`.

{style="narrow"}
Type
: `Property<String>`

Default value
: `MAJOR.MINOR`

See also:
- [Tasks: `patchPluginXml.sinceBuild`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-sinceBuild)
- [Plugin Configuration File: `idea-version`](plugin_configuration_file.md#idea-plugin__idea-version)


### untilBuild
{#intellijPlatform-pluginConfiguration-ideaVersion-untilBuild}

The latest IDE version that is compatible with the plugin. An undefined value signifies compatibility with all IDEs starting from the version mentioned in `since-build`, including potential future builds that may cause compatibility issues.

The given value will be assigned to the `<idea-version until-build=""/>` element attribute.

The default value is set to the `MAJOR.*` version based on the currently selected IntelliJ Platform, such as `233.*`.

{style="narrow"}
Type
: `Property<String>`

Default value
: `MAJOR.*`

See also:
- [Tasks: `patchPluginXml.untilBuild`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-untilBuild)
- [Plugin Configuration File: `idea-version`](plugin_configuration_file.md#idea-plugin__idea-version)


## Vendor
{#intellijPlatform-pluginConfiguration-vendor}

Configures the `idea-version` section of the plugin.

A part of the [](#intellijPlatform-pluginConfiguration) which describes the `vendor` element.

**Example:**

```kotlin
intellijPlatform {
  ...

  pluginConfiguration {
    ...

    vendor {
      name.set("JetBrains")
      email.set("hello@jetbrains.com")
      url.set("https://www.jetbrains.com")
    }
  }
}
```


### name
{#intellijPlatform-pluginConfiguration-vendor-name}

The name of the vendor or the organization ID (if created), as displayed in the Plugins settings dialog and on the JetBrains Marketplace plugin page.

The supplied value will be used as the value for the `<vendor>` element.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.vendorName`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-vendorName)
- [Plugin Configuration File: `vendor`](plugin_configuration_file.md#idea-plugin__vendor)


### email
{#intellijPlatform-pluginConfiguration-vendor-email}

The email address of the vendor.

The given value will be utilized as the `<vendor email="">` element attribute.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.vendorEmail`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-vendorEmail)
- [Plugin Configuration File: `vendor`](plugin_configuration_file.md#idea-plugin__vendor)


### url
{#intellijPlatform-pluginConfiguration-vendor-url}

The URL to the vendor's homepage.

The supplied value will be assigned to the `<vendor url="">` element attribute.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.vendorUrl`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-vendorUrl)
- [Plugin Configuration File: `vendor`](plugin_configuration_file.md#idea-plugin__vendor)


## Publishing
{#intellijPlatform-publishing}

Configures the publishing process of the plugin.
All values are passed to the [](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin) task.

**Example:**

```kotlin
intellijPlatform {
  ...

  publishing {
    host.set("")
    token.set("7hR4nD0mT0k3n_8f2eG")
    channel.set("default")
    toolboxEnterprise.set(false)
    hidden.set(false)
  }
}
```


### host
{#intellijPlatform-publishing-host}

The hostname used for publishing the plugin.

{style="narrow"}
Type
: `Property<String>`

Default value
: `"https://plugins.jetbrains.com"`

See also:
- [Tasks: `publishPlugin.host`](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin-host)


### token
{#intellijPlatform-publishing-token}

Authorization token.

{style="narrow"}
Type
: `Property<String>`

Required
: yes

See also:
- [Tasks: `publishPlugin.token`](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin-token)


### channel
{#intellijPlatform-publishing-channel}

A channel name to upload plugin to.

{style="narrow"}
Type
: `Property<String>`

Default value:
: `"default"`

See also:
- [Tasks: `publishPlugin.channel`](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin-channel)


### toolboxEnterprise
{#intellijPlatform-publishing-toolboxEnterprise}

Specifies if the Toolbox Enterprise plugin repository service should be used.

{style="narrow"}
Type
: `Property<String>`

Default value
: `false`

See also:
- [Tasks: `publishPlugin.toolboxEnterprise`](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin-toolboxEnterprise)


### hidden
{#intellijPlatform-publishing-hidden}

Publish the plugin update and mark it as hidden to prevent public release after approval.

{style="narrow"}
Type
: `Property<String>`

Default value
: `false`

See also:
- [Tasks: `publishPlugin.hidden`](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin-hidden)
- [Hidden release](https://plugins.jetbrains.com/docs/marketplace/hidden-plugin.html)


## Signing
{#intellijPlatform-signing}

Plugin signing configuration.

See also:
- [](plugin_signing.md)
- [Task Awares: `SigningAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SigningAware)
- [Marketplace ZIP Signer](https://github.com/JetBrains/marketplace-zip-signer)

### cliPath
{#intellijPlatform-signing-cliPath}

A path to the local Marketplace ZIP Signer CLI tool to be used.

{style="narrow"}
Type
: `RegularFileProperty`


### keyStore
{#intellijPlatform-signing-keyStore}

KeyStore file path.
Refers to `ks` CLI option.

{style="narrow"}
Type
: `Property<String>`


### keyStorePassword
{#intellijPlatform-signing-keyStorePassword}

KeyStore password.
Refers to `ks-pass` CLI option.

{style="narrow"}
Type
: `Property<String>`


### keyStoreKeyAlias
{#intellijPlatform-signing-keyStoreKeyAlias}

KeyStore key alias.
Refers to `ks-key-alias` CLI option.

{style="narrow"}
Type
: `Property<String>`


### keyStoreType
{#intellijPlatform-signing-keyStoreType}

KeyStore type.
Refers to `ks-type` CLI option.

{style="narrow"}
Type
: `Property<String>`


### keyStoreProviderName
{#intellijPlatform-signing-keyStoreProviderName}

JCA KeyStore Provider name.
Refers to `ks-provider-name` CLI option.

{style="narrow"}
Type
: `Property<String>`


### privateKey
{#intellijPlatform-signing-privateKey}

Encoded private key in the PEM format.
Refers to `key` CLI option.

{style="narrow"}
Type
: `Property<String>`


### privateKeyFile
{#intellijPlatform-signing-privateKeyFile}

A file with an encoded private key in the PEM format.
Refers to `key-file` CLI option.

{style="narrow"}
Type
: `RegularFileProperty`


### password
{#intellijPlatform-signing-password}

Password required to decrypt the private key.
Refers to `key-pass` CLI option.

{style="narrow"}
Type
: `Property<String>`


### certificateChain
{#intellijPlatform-signing-certificateChain}

A string containing X509 certificates.
The first certificate from the chain will be used as a certificate authority (CA).
Refers to `cert` CLI option.

{style="narrow"}
Type
: `Property<String>`


### certificateChainFile
{#intellijPlatform-signing-certificateChainFile}

Path to the file containing X509 certificates.
The first certificate from the chain will be used as a certificate authority (CA).
Refers to `cert-file` CLI option.

{style="narrow"}
Type
: `RegularFileProperty`


## Verify Plugin
{#intellijPlatform-verifyPlugin}


<include from="snippets.md" element-id="missingContent"/>
