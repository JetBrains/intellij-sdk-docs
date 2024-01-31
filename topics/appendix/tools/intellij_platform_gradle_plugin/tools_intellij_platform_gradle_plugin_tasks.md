<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Tasks

<link-summary>IntelliJ Platform Gradle Plugin tasks.</link-summary>

## buildPlugin
{#buildPlugin}


## buildSearchableOptions
{#buildSearchableOptions}

See also:
- [Extension: `intellijPlatform.buildSearchableOptions`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-buildSearchableOptions)
- [Build Features: `noSearchableOptionsWarning`](tools_intellij_platform_gradle_plugin_build_features.md#noSearchableOptionsWarning)


## classpathIndexCleanup
{#classpathIndexCleanup}

> Deprecated?
>
{style="warning"}


## initializeIntelliJPlatformPlugin
{#initializeIntelliJPlatformPlugin}


## instrumentCode
{#instrumentCode}

> Not implemented.
>
{style="warning"}

See also:
- [Extension: `intellijPlatform.instrumentCode`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-instrumentCode)


## instrumentedJar
{#instrumentedJar}

> Not implemented.
>
{style="warning"}


## jarSearchableOptions
{#jarSearchableOptions}


## patchPluginXml
{#patchPluginXml}


### inputFile
{#patchPluginXml-inputFile}

Represents an input `plugin.xml` file.

By default, a <path>plugin.xml</path> file is picked from the main resource location.

{style="narrow"}
Type
: `RegularFileProperty`

Default value:
: <path>src/main/<kotlin|java>/resources/META-INF/plugin.xml</path>


### outputFile
{#patchPluginXml-outputFile}

Represents the output <path>plugin.xml</path> file property for a task.

By default, the file is written to a temporary task directory within the <path>build</path> directory.

{style="narrow"}
Type
: `RegularFileProperty`

Default value
: <path>build/tmp/patchPluginXml/plugin.xml</path>

### pluginId
{#patchPluginXml-pluginId}

A unique identifier of the plugin.

It should be a fully qualified name similar to Java packages and must not collide with the ID of existing plugins.
The ID is a technical value used to identify the plugin in the IDE and [JetBrains Marketplace](https://plugins.jetbrains.com/).

Please use characters, numbers, and `.`/`-`/`_` symbols only and keep it reasonably short.

The provided value will be set as a value of the `<id>` element.

{style="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.id`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-id)

See also:
- [Extension: `intellijPlatform.pluginConfiguration.id`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-id)
- [Plugin Configuration File: `id`](plugin_configuration_file.md#idea-plugin__id)


### pluginName
{#patchPluginXml-pluginName}

The user-visible plugin display name (Title Case).

The provided value will be set as a value of the `<name>` element.

{style="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.name`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-name)

See also:
- [Extension: `intellijPlatform.pluginConfiguration.name`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-name)
- [Plugin Configuration File: `name`](plugin_configuration_file.md#idea-plugin__name)


### pluginVersion
{#patchPluginXml-pluginVersion}

The plugin version is displayed in the Plugins settings dialog and on the JetBrains Marketplace plugin page.

Plugins uploaded to the JetBrains Marketplace must follow semantic versioning.

The provided value will be set as a value of the `<version>` element.

{style="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.version`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-version)

See also:
- [Extension: `intellijPlatform.pluginConfiguration.version`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-version)
- [Plugin Configuration File: `version`](plugin_configuration_file.md#idea-plugin__version)


### pluginDescription
{#patchPluginXml-pluginDescription}

The plugin description is displayed on the JetBrains Marketplace plugin page and in the Plugins settings dialog.
Simple HTML elements, like text formatting, paragraphs, lists, etc., are allowed.

The description content is automatically wrapped with `<![CDATA[... ]]>`.

The provided value will be set as a value of the `<description>` element.

{style="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.description`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-description)

See also:
- [Extension: `intellijPlatform.pluginConfiguration.description`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-description)
- [Plugin Configuration File: `description`](plugin_configuration_file.md#idea-plugin__description)


### changeNotes
{#patchPluginXml-changeNotes}

A short summary of new features, bugfixes, and changes provided with the latest plugin version.
Change notes are displayed on the JetBrains Marketplace plugin page and in the Plugins settings dialog.
Simple HTML elements, like text formatting, paragraphs, lists, etc., are allowed.

The change notes content is automatically wrapped with `<![CDATA[... ]]>`.

The provided value will be set as a value of the `<change-notes>` element.

{style="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.changeNotes`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-changeNotes)

See also:
- [Extension: `intellijPlatform.pluginConfiguration.changeNotes`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-changeNotes)
- [Plugin Configuration File: `change-notes`](plugin_configuration_file.md#idea-plugin__change-notes)


### productDescriptorCode
{#patchPluginXml-productDescriptorCode}

The plugin product code used in the JetBrains Sales System.
The code must be agreed with JetBrains in advance and follow [the requirements](https://plugins.jetbrains.com/docs/marketplace/obtain-a-product-code-from-jetbrains.html).

The provided value will be set as a value of the `<product-descriptor code="">` element attribute.

{style="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.productDescriptor.code`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-productDescriptor-code)

See also:
- [Extension: `intellijPlatform.pluginConfiguration.productDescriptor.code`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-productDescriptor-code)
- [Plugin Configuration File: `product-descriptor`](plugin_configuration_file.md#idea-plugin__product-descriptor)


### productDescriptorReleaseDate
{#patchPluginXml-productDescriptorReleaseDate}

Date of the major version release in the `YYYYMMDD` format.

The provided value will be set as a value of the `<product-descriptor release-date="">` element attribute.

{style="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.productDescriptor.releaseDate`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-productDescriptor-releaseDate)

See also:
- [Extension: `intellijPlatform.pluginConfiguration.productDescriptor.releaseDate`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-productDescriptor-releaseDate)
- [Plugin Configuration File: `product-descriptor`](plugin_configuration_file.md#idea-plugin__product-descriptor)


### productDescriptorReleaseVersion
{#patchPluginXml-productDescriptorReleaseVersion}

A major version in a special number format.

The provided value will be set as a value of the `<product-descriptor release-version="">` element attribute.

{style="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.productDescriptor.releaseVersion`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-productDescriptor-releaseVersion)

See also:
- [Extension: `intellijPlatform.pluginConfiguration.productDescriptor.releaseVersion`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-productDescriptor-releaseVersion)
- [Plugin Configuration File: `product-descriptor`](plugin_configuration_file.md#idea-plugin__product-descriptor)


### productDescriptorOptional
{#patchPluginXml-productDescriptorOptional}

The boolean value determining whether the plugin is a [Freemium](https://plugins.jetbrains.com/docs/marketplace/freemium.html) plugin.

The provided value will be set as a value of the `<product-descriptor optional="">` element attribute.

{style="narrow"}
Type
: `Property<Boolean>`

Default value
: [`intellijPlatform.pluginConfiguration.productDescriptor.optional`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-productDescriptor-optional)

Default value
: `false`

See also:
- [Extension: `intellijPlatform.pluginConfiguration.productDescriptor.optional`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-productDescriptor-optional)
- [Plugin Configuration File: `product-descriptor`](plugin_configuration_file.md#idea-plugin__product-descriptor)


### sinceBuild
{#patchPluginXml-sinceBuild}

The lowest IDE version compatible with the plugin.

The provided value will be set as a value of the `<idea-version since-build=""/>` element attribute.

{style="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.ideaVersion.sinceBuild`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-ideaVersion-sinceBuild)

See also:
- [Extension: `intellijPlatform.pluginConfiguration.ideaVersion.sinceBuild`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-ideaVersion-sinceBuild)
- [Plugin Configuration File: `idea-version`](plugin_configuration_file.md#idea-plugin__idea-version)


### untilBuild
{#patchPluginXml-untilBuild}

The highest IDE version compatible with the plugin.
Undefined value declares compatibility with all the IDEs since the version specified by the `since-build` (also with the future builds that may cause incompatibility errors).

The provided value will be set as a value of the `<idea-version until-build=""/>` element attribute.

{style="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.ideaVersion.untilBuild`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-ideaVersion-untilBuild)

See also:
- [Extension: `intellijPlatform.pluginConfiguration.ideaVersion.untilBuild`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-ideaVersion-untilBuild)
- [Plugin Configuration File: `idea-version`](plugin_configuration_file.md#idea-plugin__idea-version)


### vendorName
{#patchPluginXml-vendorName}

The vendor name or organization ID (if created) in the Plugins settings dialog and on the JetBrains Marketplace plugin page.

The provided value will be set as a value of the `<vendor>` element.

{style="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.vendor.name`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-vendor-name)

See also:
- [Extension: `intellijPlatform.pluginConfiguration.vendor.name`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-vendor-name)
- [Plugin Configuration File: `vendor`](plugin_configuration_file.md#idea-plugin__vendor)


### vendorEmail
{#patchPluginXml-vendorEmail}

The vendor's email address.

The provided value will be set as a value of the `<vendor email="">` element attribute.

{style="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.vendor.email`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-vendor-email)

See also:
- [Extension: `intellijPlatform.pluginConfiguration.vendor.email`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-vendor-email)
- [Plugin Configuration File: `vendor`](plugin_configuration_file.md#idea-plugin__vendor)


### vendorUrl
{#patchPluginXml-vendorUrl}

The link to the vendor's homepage.

The provided value will be set as a value of the `<vendor url="">` element attribute.

{style="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.vendor.url`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-vendor-url)

See also:
- [Extension: `intellijPlatform.pluginConfiguration.vendor.url`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-vendor-url)
- [Plugin Configuration File: `vendor`](plugin_configuration_file.md#idea-plugin__vendor)


## prepareSandbox
{#prepareSandbox}

See also:
- [Extension: `intellijPlatform.sandboxContainer`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-sandboxContainer)
- [Task Awares: `SandboxAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SandboxAware)


## printBundledPlugins
{#printBundledPlugins}


## printProductsReleases
{#printProductsReleases}


## publishPlugin
{#publishPlugin}

The task for publishing plugin to the remote plugins repository, such as [JetBrains Marketplace](https://plugins.jetbrains.com).

See also:
- [Uploading a Plugin to JetBrains Marketplace](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html#uploading-a-plugin-to-jetbrains-marketplace)
- [Plugin upload API](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html)
- [Publishing Plugin With Gradle](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html#publishing-plugin-with-gradle)


### archiveFile
{#publishPlugin-archiveFile}

ZIP archive to be published to the remote repository.

By default, it uses an output `archiveFile` of the [`signPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin) task if plugin signing is configured, otherwise [`buildPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#buildPlugin).

{style="narrow"}
Type
: `Property<RegularFileProperty>`

Default value
: [`signPlugin.archiveFile`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin-archiveFile) or [`buildPlugin.archiveFile`](tools_intellij_platform_gradle_plugin_tasks.md#buildPlugin-archiveFile)

See also:
- [Extension: `intellijPlatform.signing`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing)
- [Tasks: `signPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin)
- [Tasks: `buildPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#buildPlugin)


### host
{#publishPlugin-host}

URL host of a plugin repository.

{style="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.publishing.host`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-host)

See also:
- [Extension `intellijPlatform.publishing.host`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-host)


### token
{#publishPlugin-token}

Authorization token.

{style="narrow"}
Type
: `Property<String>`

Required
: yes

Default value
: [`intellijPlatform.publishing.token`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-token)

See also:
- [Extension: `intellijPlatform.publishing.token`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-token)


### channel
{#publishPlugin-channel}

A channel name to upload plugin to.

{style="narrow"}
Type
: `Property<String>`

Default value:
: [`intellijPlatform.publishing.channel`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-channel)

See also:
- [Extension: `intellijPlatform.publishing.channel`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-channel)


### hidden
{#publishPlugin-hidden}

Publish the plugin update and mark it as hidden to prevent public release after approval.

{style="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.publishing.hidden`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-hidden)

See also:
- [Extension: `intellijPlatform.publishing.hidden`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-hidden)
- [Hidden release](https://plugins.jetbrains.com/docs/marketplace/hidden-plugin.html)


### toolboxEnterprise
{#publishPlugin-toolboxEnterprise}

Specifies if the Toolbox Enterprise plugin repository service should be used.

{style="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.publishing.toolboxEnterprise`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-toolboxEnterprise)

See also:
- [Extension: `intellijPlatform.publishing.toolboxEnterprise`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-toolboxEnterprise)


## runIdePerformanceTest
{#runIdePerformanceTest}

> Not implemented.
>
{style="warning"}


## runIde
{#runIde}


## setupDependencies
{#setupDependencies}

> Deprecated.
>
{style="warning"}


## signPlugin
{#signPlugin}


## testIde
{#testIde}


## testIdeUi
{#testIdeUi}

> Not implemented.
>
{style="warning"}


## verifyPluginProjectConfiguration
{#verifyPluginProjectConfiguration}


## verifyPluginSignature
{#verifyPluginSignature}


## verifyPluginStructure
{#verifyPluginStructure}


## verifyPlugin
{#verifyPlugin}


<include from="snippets.md" element-id="missingContent"/>
