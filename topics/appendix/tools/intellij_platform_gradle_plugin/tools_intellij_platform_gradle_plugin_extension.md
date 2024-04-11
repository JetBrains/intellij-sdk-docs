<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ Platform Extension

<link-summary>IntelliJ Platform Gradle Plugin extension.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="EAP_Status"/>
<include from="tools_intellij_platform_gradle_plugin.md" element-id="faq"/>

The _IntelliJ Platform Gradle Plugin_ introduces a top-level `intellijPlatform` extension.
It consists of sections dedicated to the general Gradle plugin configuration, <path>plugin.xml</path> definition, publishing, signing, and verifying of the output plugin for IntelliJ-based IDEs.


## IntelliJ Platform
{#intellijPlatform}

After the IntelliJ Platform Gradle Plugin is [applied](tools_intellij_platform_gradle_plugin.md#usage), the `intellijPlatform` extension can be used to configure the plugin and common settings of the provided tasks.

**Example:**

```kotlin
intellijPlatform {
  buildSearchableOptions = true
  instrumentCode = true
  projectName = project.name
  sandboxContainer = "..."

  pluginConfiguration {
    // ...
  }
  publishing {
    // ...
  }
  signing {
    // ...
  }
  verifyPlugin {
    // ...
  }
}
```


### `cachePath`
{#intellijPlatform-cachePath}

Provides read-only access to the IntelliJ Platform project cache location.

The IntelliJ Platform cache is used for storing IntelliJ Platform Gradle Plugin-specific files, such as:
- XML files generated for the [`localPlatformArtifacts()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#additional-repositories) local Ivy repository
- coroutines Java agent file created by the [`initializeIntelliJPlatformPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#initializeIntelliJPlatformPlugin) task

This path can be changed with the [`org.jetbrains.intellij.platform.intellijPlatformCache`](tools_intellij_platform_gradle_plugin_gradle_properties.md#intellijPlatformCache) Gradle property

{style="narrow"}
Access
: Read-only

Type
: `Path`

Default value
: <path>[rootProject]/.intellijPlatform/</path>


### `platformPath`
{#intellijPlatform-platformPath}

Provides read-only access to the IntelliJ Platform dependency artifact path.

{style="narrow"}
Access
: Read-only

Type
: `Path`

Default value
: Path of the current IntelliJ Platform


### `productInfo`
{#intellijPlatform-productInfo}

Provides read-only access to the [`ProductInfo`](tools_intellij_platform_gradle_plugin_types.md#ProductInfo) object associated with the IntelliJ Platform dependency configured for the current project.

{style="narrow"}
Access
: Read-only

Type
: [`ProductInfo`](tools_intellij_platform_gradle_plugin_types.md#ProductInfo)

Default value
: [`ProductInfo`](tools_intellij_platform_gradle_plugin_types.md#ProductInfo) of the current IntelliJ Platform


### `autoReload`
{#intellijPlatform-autoReload}

Enables auto-reload of dynamic plugins.
Dynamic plugin will be reloaded automatically when its content is modified.

This allows a much faster development cycle by avoiding a full restart of the development instance after code changes.

{style="narrow"}
Type
: `Property<Boolean>`

Default value
: `true`

See also:
- [](ide_development_instance.md#enabling-auto-reload)
- [Task Awares: `AutoReloadAware`](tools_intellij_platform_gradle_plugin_task_awares.md#AutoReloadAware)


### `buildSearchableOptions`
{#intellijPlatform-buildSearchableOptions}

Builds an index of UI components (searchable options) for the plugin.
Controls the execution of the [`buildSearchableOptions`](tools_intellij_platform_gradle_plugin_tasks.md#buildSearchableOptions) task.

{style="narrow"}
Type
: `Property<Boolean>`

Default value
: `true`

See also:
- [Build Features: `noSearchableOptionsWarning`](tools_intellij_platform_gradle_plugin_gradle_properties.md#noSearchableOptionsWarning)


### `instrumentCode`
{#intellijPlatform-instrumentCode}

Enables the [](tools_intellij_platform_gradle_plugin.md#code-instrumentation) of the compiled classes.

Controls the execution of the [`instrumentCode`](tools_intellij_platform_gradle_plugin_tasks.md#instrumentCode) task.

{style="narrow"}
Type
: `Property<Boolean>`

Default value
: `true`


### `projectName`
{#intellijPlatform-projectName}

Defines the project name, which is used for creating file structure and the build archive.

{style="narrow"}
Type
: `Property<String>`

Default value
: `project.name`


### `sandboxContainer`
{#intellijPlatform-sandboxContainer}

The path to the sandbox container where tests and IDE instances read and write data.

{style="narrow"}
Type
: `DirectoryProperty`

Default value
: <path>[buildDirectory]/idea-sandbox</path>

See also:
- [Tasks: `prepareSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox)
- [Task Awares: `SandboxAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SandboxAware)


### `splitMode`
{#intellijPlatform-splitMode}

> Split Mode requires the IntelliJ Platform in version `241.14473` or later.
>
{style="warning"}

Allows for checking how a plugin works in remote development mode, when one machine is running the backend part and another is running a frontend part (JetBrains Client) which connects to the backend.

This property allows running the IDE with backend and frontend parts running in separate processes.
The developed plugin is installed in the backend part.

{style="narrow"}
Type
: `Property<Boolean>`

Default value
: `true`

See also:
- [Task Awares: `SplitModeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SplitModeAware)


## Plugin Configuration
{#intellijPlatform-pluginConfiguration}

Configures the plugin definition and stores values in the `plugin.xml` file.
Data provided to the `intellijPlatform.pluginConfiguration {}` extension is passed to the [`patchPluginXml`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml) task, which augments the <path>plugin.xml</path> file with new values.

Requires the [](tools_intellij_platform_gradle_plugin_plugins.md#build) plugin to be applied.

**Example:**

```kotlin
intellijPlatform {
  // ...

  pluginConfiguration {
    id = "my-plugin-id"
    name = "My Awesome Plugin"
    version = "1.0.0"
    description = "It's an awesome plugin!"
    changeNotes =
      """
      A descriptive release note...
      """.trimIndent()

    productDescriptor {
      // ...
    }
    ideaVersion {
      // ...
    }
    vendor {
      // ...
    }
  }
}
```

See also:
- [](#intellijPlatform-pluginConfiguration-productDescriptor)
- [](#intellijPlatform-pluginConfiguration-ideaVersion)
- [](#intellijPlatform-pluginConfiguration-vendor)


### `id`
{#intellijPlatform-pluginConfiguration-id}

The plugin's unique identifier.
This should mirror the structure of fully qualified Java packages and must remain distinct from the IDs of existing plugins.
This ID is a technical descriptor used not only within the IDE, but also on [JetBrains Marketplace](https://plugins.jetbrains.com/).

Please restrict input to characters, numbers, and `.`/`-`/`_` symbols, and aim for a concise length.

The provided value will populate the [`<id>`](plugin_configuration_file.md#idea-plugin__id) element.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.pluginId`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-pluginId)


### `name`
{#intellijPlatform-pluginConfiguration-name}

The plugin's display name, visible to users.
It should use Title Case.

The provided value is used to populate the [`<name>`](plugin_configuration_file.md#idea-plugin__name) element.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.pluginName`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-pluginName)


### `version`
{#intellijPlatform-pluginConfiguration-version}

The plugin version, presented in the Plugins settings dialog and on its JetBrains Marketplace page.

For plugins uploaded to the JetBrains Marketplace, [semantic versioning](https://plugins.jetbrains.com/docs/marketplace/semver.html) must be adhered to.

The provided value is used as a [`<version>`](plugin_configuration_file.md#idea-plugin__version) element.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.pluginVersion`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-pluginVersion)


### `description`
{#intellijPlatform-pluginConfiguration-description}

The plugin description, which is presented on the JetBrains Marketplace plugin page and in the Plugins settings dialog.
Basic HTML elements such as text formatting, paragraphs, and lists are permitted.

The description content is automatically enclosed in `<![CDATA[... ]]>`.

The provided value is used to populate the [`<description>`](plugin_configuration_file.md#idea-plugin__description) element.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.pluginDescription`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-pluginDescription)


### `changeNotes`
{#intellijPlatform-pluginConfiguration-changeNotes}

A concise summary of new features, bug fixes, and alterations provided in the latest plugin version.
These change notes will be displayed on the JetBrains Marketplace plugin page and in the Plugins settings dialog.
Basic HTML elements such as text formatting, paragraphs, and lists are permitted.

The change notes content is automatically enclosed in `<![CDATA[... ]]>`.

The provided value is used to populate the [`<change-notes>`](plugin_configuration_file.md#idea-plugin__change-notes) element.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.changeNotes`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-changeNotes)


## Product Descriptor
{#intellijPlatform-pluginConfiguration-productDescriptor}

A part of the [](#intellijPlatform-pluginConfiguration) which describes the `product-descriptor` element.

**Example:**

```kotlin
intellijPlatform {
  // ...

  pluginConfiguration {
    // ...

    productDescriptor {
      code = "MY_CODE"
      releaseDate = "20240217"
      releaseVersion = "20241"
      optional = false
    }
  }
}
```

See also:
- [How to add required parameters for paid plugins](https://plugins.jetbrains.com/docs/marketplace/add-required-parameters.html)


### `code`
{#intellijPlatform-pluginConfiguration-productDescriptor-code}

The product code for the plugin, used in the JetBrains Sales System.
The value must be pre-approved by JetBrains and must adhere to [specified requirements](https://plugins.jetbrains.com/docs/marketplace/obtain-a-product-code-from-jetbrains.html).

The provided value is used for a `<product-descriptor code="">` element attribute.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.productDescriptorCode`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-productDescriptorCode)
- [Plugin Configuration File: `product-descriptor`](plugin_configuration_file.md#idea-plugin__product-descriptor)


### `releaseDate`
{#intellijPlatform-pluginConfiguration-productDescriptor-releaseDate}

The release date of the major version, formatted as `YYYYMMDD`.

The provided value is used for the `<product-descriptor release-date="">` element attribute.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.productDescriptorReleaseDate`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-productDescriptorReleaseDate)
- [Plugin Configuration File: `product-descriptor`](plugin_configuration_file.md#idea-plugin__product-descriptor)


### `releaseVersion`
{#intellijPlatform-pluginConfiguration-productDescriptor-releaseVersion}

The major version, represented in a specific numerical format.

The provided value is used for the `<product-descriptor release-version="">` element attribute.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.productDescriptorReleaseVersion`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-productDescriptorReleaseVersion)
- [Plugin Configuration File: `product-descriptor`](plugin_configuration_file.md#idea-plugin__product-descriptor)


### `optional`
{#intellijPlatform-pluginConfiguration-productDescriptor-optional}

The boolean value that indicates if the plugin is a [Freemium](https://plugins.jetbrains.com/docs/marketplace/freemium.html) plugin.

The provided value is used for the `<product-descriptor optional="">` element attribute.

{style="narrow"}
Type
: `Property<Boolean>`

Default value
: `false`

See also:
- [Tasks: `patchPluginXml.productDescriptorOptional`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-productDescriptorOptional)
- [Plugin Configuration File: `product-descriptor`](plugin_configuration_file.md#idea-plugin__product-descriptor)


## Idea Version
{#intellijPlatform-pluginConfiguration-ideaVersion}

A part of the [](#intellijPlatform-pluginConfiguration) which describes the [`<idea-version>`](plugin_configuration_file.md#idea-plugin__idea-version) element.

**Example:**

```kotlin
intellijPlatform {
  // ...

  pluginConfiguration {
    // ...

    ideaVersion {
      sinceBuild = "241"
      untilBuild = "241.*"
    }
  }
}
```

See also:

- [](build_number_ranges.md)

### `sinceBuild`
{#intellijPlatform-pluginConfiguration-ideaVersion-sinceBuild}

The earliest IDE version that is compatible with the plugin.

The provided value is used for the `<idea-version since-build=""/>` element attribute.

The default value is set to the `MAJOR.MINOR` version based on the currently selected IntelliJ Platform, like `233.12345`.

{style="narrow"}
Type
: `Property<String>`

Default value
: `MAJOR.MINOR`

See also:
- [Tasks: `patchPluginXml.sinceBuild`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-sinceBuild)


### `untilBuild`
{#intellijPlatform-pluginConfiguration-ideaVersion-untilBuild}

The latest IDE version that is compatible with the plugin.
An undefined value signifies compatibility with all IDEs starting from the version mentioned in `since-build`,
including potential future builds that may cause compatibility issues.

The provided value is used for the `<idea-version until-build=""/>` element attribute.

The default value is set to the `MAJOR.*` version based on the currently selected IntelliJ Platform, such as `233.*`.

The `until-build` attribute can be unset by setting `provider { null }` as a value.
Note that passing only `null` will make Gradle use a default value instead.

{style="narrow"}
Type
: `Property<String>`

Default value
: `MAJOR.*`

See also:
- [Tasks: `patchPluginXml.untilBuild`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-untilBuild)


## Vendor
{#intellijPlatform-pluginConfiguration-vendor}

A part of the [](#intellijPlatform-pluginConfiguration) which describes the [`<vendor>`](plugin_configuration_file.md#idea-plugin__vendor) element.

**Example:**

```kotlin
intellijPlatform {
  // ...

  pluginConfiguration {
    // ...

    vendor {
      name = "JetBrains"
      email = "hello@jetbrains.com"
      url = "https://www.jetbrains.com"
    }
  }
}
```

### `name`
{#intellijPlatform-pluginConfiguration-vendor-name}

The name of the vendor or the organization ID (if created), as displayed in the <control>Plugins</control> settings dialog and on the JetBrains Marketplace plugin page.

The provided value is used as the value of the [`<vendor>`](plugin_configuration_file.md#idea-plugin__vendor) element.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.vendorName`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-vendorName)


### `email`
{#intellijPlatform-pluginConfiguration-vendor-email}

The email address of the vendor.

The provided value is used for the `<vendor email="">` element attribute.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.vendorEmail`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-vendorEmail)


### `url`
{#intellijPlatform-pluginConfiguration-vendor-url}

The URL to the vendor's homepage.

The provided value is used for the `<vendor url="">` element attribute.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `patchPluginXml.vendorUrl`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-vendorUrl)


## Publishing
{#intellijPlatform-publishing}

Configures the publishing process of the plugin.
All values are passed to the [](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin) task.

Requires the [](tools_intellij_platform_gradle_plugin_plugins.md#publish) plugin to be applied.

**Example:**

```kotlin
intellijPlatform {
  // ...

  publishing {
    host = ""
    token = "7hR4nD0mT0k3n_8f2eG"
    channels = listOf("default")
    ideServices = false
    hidden = false
  }
}
```


### `host`
{#intellijPlatform-publishing-host}

The hostname used for publishing the plugin.

{style="narrow"}
Type
: `Property<String>`

Default value
: `https://plugins.jetbrains.com`

See also:
- [Tasks: `publishPlugin.host`](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin-host)


### `token`
{#intellijPlatform-publishing-token}

Authorization token.

{style="narrow"}
Type
: `Property<String>`

Required
: yes

See also:
- [Tasks: `publishPlugin.token`](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin-token)


### `channels`
{#intellijPlatform-publishing-channels}

A list of channel names to upload plugin to.

{style="narrow"}
Type
: `ListProperty<String>`

Default value:
: `listOf("default")`

See also:
- [Tasks: `publishPlugin.channels`](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin-channels)


### `ideServices`
{#intellijPlatform-publishing-ideServices}

Specify if the IDE Services plugin repository service should be used.

{style="narrow"}
Type
: `Property<String>`

Default value
: `false`

See also:
- [Tasks: `publishPlugin.ideServices`](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin-ideServices)


### `hidden`
{#intellijPlatform-publishing-hidden}

Publish the plugin update and mark it as hidden to prevent public visibility after approval.

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

Requires the [](tools_intellij_platform_gradle_plugin_plugins.md#publish) plugin to be applied.

**Example:**

```kotlin
intellijPlatform {
  // ...

  signing {
    cliPath = file("/path/to/marketplace-zip-signer-cli.jar")
    keyStore = file("/path/to/keyStore.ks")
    keyStorePassword = "..."
    keyStoreKeyAlias = "..."
    keyStoreType = "..."
    keyStoreProviderName = "..."
    privateKey = "..."
    privateKeyFile = file("/path/to/private.pem")
    password = "..."
    certificateChain = "..."
    certificateChainFile = file("/path/to/chain.crt")
  }
}
```

See also:
- [](plugin_signing.md)
- [Tasks: `signPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin)
- [Task Awares: `SigningAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SigningAware)
- [Marketplace ZIP Signer](https://github.com/JetBrains/marketplace-zip-signer)


### `cliPath`
{#intellijPlatform-signing-cliPath}

A path to the local Marketplace ZIP Signer CLI tool to be used.

{style="narrow"}
Type
: `RegularFileProperty`

See also:
- [Task Awares: `SigningAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SigningAware)


### `keyStore`
{#intellijPlatform-signing-keyStore}

KeyStore file path.
Refers to `ks` CLI option.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `signPlugin.keyStore`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-keyStore)


### `keyStorePassword`
{#intellijPlatform-signing-keyStorePassword}

KeyStore password.
Refers to `ks-pass` CLI option.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `signPlugin.keyStorePassword`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-keyStorePassword)


### `keyStoreKeyAlias`
{#intellijPlatform-signing-keyStoreKeyAlias}

KeyStore key alias.
Refers to `ks-key-alias` CLI option.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `signPlugin.keyStoreKeyAlias`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-keyStoreKeyAlias)


### `keyStoreType`
{#intellijPlatform-signing-keyStoreType}

KeyStore type.
Refers to `ks-type` CLI option.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `signPlugin.keyStoreType`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-keyStoreType)


### `keyStoreProviderName`
{#intellijPlatform-signing-keyStoreProviderName}

JCA KeyStore Provider name.
Refers to `ks-provider-name` CLI option.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `signPlugin.keyStoreProviderName`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-keyStoreProviderName)


### `privateKey`
{#intellijPlatform-signing-privateKey}

Encoded private key in the PEM format.
Refers to `key` CLI option.

Takes precedence over the [](#intellijPlatform-signing-privateKeyFile) property.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `signPlugin.privateKey`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-privateKey)


### `privateKeyFile`
{#intellijPlatform-signing-privateKeyFile}

A file with an encoded private key in the PEM format.
Refers to `key-file` CLI option.

{style="narrow"}
Type
: `RegularFileProperty`

See also:
- [Tasks: `signPlugin.privateKeyFile`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-privateKeyFile)


### `password`
{#intellijPlatform-signing-password}

Password required to decrypt the private key.
Refers to `key-pass` CLI option.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `signPlugin.password`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-password)


### `certificateChain`
{#intellijPlatform-signing-certificateChain}

A string containing X509 certificates.
The first certificate from the chain will be used as a certificate authority (CA).
Refers to `cert` CLI option.

Takes precedence over the [](#intellijPlatform-signing-certificateChainFile) property.

{style="narrow"}
Type
: `Property<String>`

See also:
- [Tasks: `signPlugin.certificateChain`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-certificateChain)


### `certificateChainFile`
{#intellijPlatform-signing-certificateChainFile}

Path to the file containing X509 certificates.
The first certificate from the chain will be used as a certificate authority (CA).
Refers to `cert-file` CLI option.

{style="narrow"}
Type
: `RegularFileProperty`

See also:
- [Tasks: `signPlugin.certificateChainFile`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-certificateChainFile)


## Verify Plugin
{#intellijPlatform-verifyPlugin}

IntelliJ Plugin Verifier CLI tool configuration.

Requires the [](tools_intellij_platform_gradle_plugin_plugins.md#verify) plugin to be applied.

**Example:**

```kotlin
intellijPlatform {
  // ...

  verifyPlugin {
    cliPath = file("/path/to/plugin-verifier-cli.jar")
    freeArgs = listOf("foo", "bar")
    homeDirectory = file("/path/to/pluginVerifierHomeDirectory/")
    downloadDirectory = file("/path/to/pluginVerifierHomeDirectory/ides/")
    failureLevel = VerifyPluginTask.FailureLevel.ALL
    verificationReportsDirectory = "build/reports/pluginVerifier"
    verificationReportsFormats = VerifyPluginTask.VerificationReportsFormats.ALL
    externalPrefixes = "com.example"
    teamCityOutputFormat = false
    subsystemsToCheck = VerifyPluginTask.Subsystems.ALL
    ignoredProblemsFile = file("/path/to/ignoredProblems.txt")

    ides {
      // ...
    }
  }
}
```

See also:
- [](verifying_plugin_compatibility.md)
- [Tasks: `verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin)
- [Task Awares: `PluginVerifierAware`](tools_intellij_platform_gradle_plugin_task_awares.md#PluginVerifierAware)
- [](#intellijPlatform-verifyPlugin-ides)
- [IntelliJ Plugin Verifier CLI](https://github.com/JetBrains/intellij-plugin-verifier)


### `cliPath`
{#intellijPlatform-verifyPlugin-cliPath}

A path to the local IntelliJ Plugin Verifier CLI tool to be used.

{style="narrow"}
Type
: `RegularFileProperty`

See also:
- [Task Awares: `PluginVerifierAware`](tools_intellij_platform_gradle_plugin_task_awares.md#PluginVerifierAware)


### `downloadDirectory`
{#intellijPlatform-verifyPlugin-downloadDirectory}

The path to the directory where IDEs used for the verification will be downloaded.

{style="narrow"}
Type
: `DirectoryProperty`

Default value
: <path>[`homeDirectory`](#intellijPlatform-verifyPlugin-homeDirectory)/ides</path>


### `failureLevel`
{#intellijPlatform-verifyPlugin-failureLevel}

Defines the verification level at which the task should fail if any reported issue matches.

{style="narrow"}
Type
: `ListProperty<FailureLevel>`

Default value
: [`FailureLevel.COMPATIBILITY_PROBLEMS`](tools_intellij_platform_gradle_plugin_types.md#FailureLevel)

See also:
- [Tasks: `verifyPlugin.failureLevel`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-failureLevel)


### `externalPrefixes`
{#intellijPlatform-verifyPlugin-externalPrefixes}

The list of class prefixes from the external libraries.
The Plugin Verifier will not report `No such class` for classes of these packages.

{style="narrow"}
Type
: `ListProperty<String>`

See also:
- [Tasks: `verifyPlugin.externalPrefixes`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-externalPrefixes)


### `freeArgs`
{#intellijPlatform-verifyPlugin-freeArgs}

The list of free arguments is passed directly to the IntelliJ Plugin Verifier CLI tool.

They can be used in addition to the arguments that are provided by dedicated options.

{style="narrow"}
Type
: `ListProperty<String>`

See also:
- [Tasks: `verifyPlugin.freeArgs`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-freeArgs)


### `homeDirectory`
{#intellijPlatform-verifyPlugin-homeDirectory}

Retrieve the Plugin Verifier home directory used for storing downloaded IDEs.
Following home directory resolving method is taken directly from the Plugin Verifier to keep the compatibility.

{style="narrow"}
Type
: `DirectoryProperty`

Default value
: - Directory specified with `plugin.verifier.home.dir` system property
  - Directory specified with `XDG_CACHE_HOME` environment variable
  - <path>~/.cache/pluginVerifier</path>
  - <path>[buildDirectory]/tmp/pluginVerifier</path>


### `ignoredProblemsFile`
{#intellijPlatform-verifyPlugin-ignoredProblemsFile}

A file that contains a list of problems that will be ignored in a report.

{style="narrow"}
Type
: `RegularFileProperty`

See also:
- [Tasks: `verifyPlugin.ignoredProblemsFile`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-ignoredProblemsFile)


### `subsystemsToCheck`
{#intellijPlatform-verifyPlugin-subsystemsToCheck}

Which subsystems of the IDE should be checked.

{style="narrow"}
Type
: `Subsystems`

Default value
: [`Subsystems.ALL`](tools_intellij_platform_gradle_plugin_types.md#Subsystems)

See also:
- [Tasks: `verifyPlugin.subsystemsToCheck`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-subsystemsToCheck)


### `teamCityOutputFormat`
{#intellijPlatform-verifyPlugin-teamCityOutputFormat}

A flag that controls the output format.
If set to `true`, the [TeamCity](https://www.jetbrains.com/teamcity/) compatible output will be returned to stdout.

{style="narrow"}
Type
: `Property<Boolean>`

Default value
: `false`

See also:
- [Tasks: `verifyPlugin.teamCityOutputFormat`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-teamCityOutputFormat)


### `verificationReportsDirectory`
{#intellijPlatform-verifyPlugin-verificationReportsDirectory}

The path to the directory where verification reports will be saved.

{style="narrow"}
Type
: `DirectoryProperty`

Default value
: <path>[buildDirectory]/reports/pluginVerifier</path>

See also:
- [Tasks: `verifyPlugin.verificationReportsDirectory`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-verificationReportsDirectory)


### `verificationReportsFormats`
{#intellijPlatform-verifyPlugin-verificationReportsFormats}

The output formats of the verification reports.

{style="narrow"}
Type
: `ListProperty<VerificationReportsFormats>`

Default value
: [`VerificationReportsFormats.PLAIN`](tools_intellij_platform_gradle_plugin_types.md#VerificationReportsFormats), [`FailureVerificationReportsFormats`](tools_intellij_platform_gradle_plugin_types.md#VerificationReportsFormats)

See also:
- [Tasks: `verifyPlugin.verificationReportsFormats`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-verificationReportsFormats)


## Verify Plugin IDEs
{#intellijPlatform-verifyPlugin-ides}

The extension to define the IDEs to be used along with the IntelliJ Plugin Verifier CLI tool for the binary plugin verification.

It provides a set of helpers which add relevant entries to the configuration, which later is used to resolve IntelliJ-based IDE binary releases.

**Example:**

```kotlin
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType

intellijPlatform {
  // ...

  verifyPlugin {
    // ...

    ides {
      ide(IntelliJPlatformType.PhpStorm)
      ide(IntelliJPlatformType.RustRover, "2023.3")
      local(file("/path/to/ide/"))
      recommended()
      select {
        types = listOf(IntelliJPlatformType.PhpStorm)
        channels = listOf(ProductRelease.Channel.RELEASE)
        sinceBuild = "232"
        untilBuild = "241.*"
      }
    }
  }
}
```

See also:
- [](verifying_plugin_compatibility.md)
- [Tasks: `verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin)
- [Types: `IntelliJPlatformType`](tools_intellij_platform_gradle_plugin_types.md#IntelliJPlatformType)
- [Types: `ProductRelease.Channel`](tools_intellij_platform_gradle_plugin_types.md#ProductRelease-Channel)
- [Types: `ProductReleasesValueSource.FilterParameters`](tools_intellij_platform_gradle_plugin_types.md#ProductReleasesValueSource-FilterParameters)

| Function                                            | Description                                                                                                                                                                                         |
|-----------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| <p>`ide(type, version)`</p><p>`ide(definition)`</p> | Adds a dependency to a binary IDE release to be used for testing with the IntelliJ Plugin Verifier.                                                                                                 |
| `local(localPath)`                                  | Adds the local IDE to be used for testing with the IntelliJ Plugin Verifier.                                                                                                                        |
| `recommended()`                                     | Retrieves matching IDEs using the default configuration based on the currently used IntelliJ Platform and applies them for IntelliJ Platform Verifier using the `ide` helper method.                |
| `select(configure)`                                 | Retrieves matching IDEs using custom [`ProductReleasesValueSource.FilterParameters`](tools_intellij_platform_gradle_plugin_types.md#ProductReleasesValueSource-FilterParameters) filter parameters. |


<include from="snippets.md" element-id="missingContent"/>
