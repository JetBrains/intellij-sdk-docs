<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Postfix Templates

<link-summary>Postfix templates implement possibility to modify or wrap the existing code in additional constructs without navigating the caret back.</link-summary>

The IntelliJ Platform allows plugins to provide custom postfix templates specific to the supported languages, frameworks, or libraries.

To provide custom postfix templates for an existing or custom language, register an implementation of
[`PostfixTemplateProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/template/postfix/templates/PostfixTemplateProvider.java)
in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.codeInsight.template.postfixTemplateProvider"/></include>.

The `PostfixTemplateProvider` extension contains the list of templates that extend the
[`PostfixTemplate`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/template/postfix/templates/PostfixTemplate.java)
class.
During the code completion mechanism, all postfix template providers registered for the current language are queried for their templates.
All templates enabled and applicable in the current context will be added to the completion popup items set.

**Examples:**
- [`JavaPostfixTemplateProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/template/postfix/templates/JavaPostfixTemplateProvider.java) providing Java postfix templates
- [`PyPostfixTemplateProvider`](%gh-ic%/python/src/com/jetbrains/python/codeInsight/postfix/PyPostfixTemplateProvider.java) providing Python postfix templates

## Postfix Templates Implementation

The simplest way to create a postfix template is by extending the
[`PostfixTemplate`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/template/postfix/templates/PostfixTemplate.java)
class and implementing the key methods:
- `boolean isApplicable()` determining whether the template can be used in the context described by parameters
- `void expand()` inserting the template content in the editor

**Examples:**
- [`InstanceofExpressionPostfixTemplate`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/template/postfix/templates/InstanceofExpressionPostfixTemplate.java) surrounding an expression with the _instanceof_ check
- [`TryWithResourcesPostfixTemplate`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/template/postfix/templates/TryWithResourcesPostfixTemplate.java) wrapping a selected expression in _try-with-resources_ block

> See the [](advanced_postfix_templates.md) section for information on how to implement postfix templates with more advanced features and editing possibilities.
>
{style="note"}

## Postfix Template Description

All postfix templates must provide descriptions and examples showing the code before and after a template is expanded.
The files describing the template must be placed in the plugin's <path>resources</path> in the <path>postfixTemplates/\$TEMPLATE_NAME\$</path> where the _\$TEMPLATE_NAME\$_ directory must match the simple name of the template class,
e.g., for a template implemented in `com.example.IntroduceVariablePostfixTemplate` class, the directory name should be named as <path>IntroduceVariablePostfixTemplate</path>.

Providing the description explaining the template purpose and context details is achieved by creating the <path>description.html</path> file.

Providing the code snippets showing the template in "before" and "after" expanding states is achieved via the <path>before.\$EXTENSION\$.template</path> and <path>after.\$EXTENSION\$.template</path> files accordingly.
The _\$EXTENSION\$_ placeholder should be replaced with the extension of the template language, e.g., <path>before.kt.template</path> for a Kotlin template.

> If a plugin project is multi-module, and it combines resources into a single JAR, make sure that all template description files have unique names or paths.
> Otherwise, only the last packed description file will exist in the distribution package.
>
{style="warning"}

> See the [](providing_translations.md#bundled-translations) section for information about how to provide postfix template description translations in plugins.

The code snippets included in the example files can use the `<spot>` marker, which should surround the most important code parts, e.g., expression to expand and position of the caret after expanding.
Marked parts will be highlighted in the <ui-path>Settings | Editor | General | Postfix Completion</ui-path> settings page, making it easier for users to understand how a template is expanded, e.g.:
- <path>before.java.template</path>:
  ```java
  <spot>cart.getProducts()</spot>.var
  ```

- <path>after.java.template</path>:
  ```java
  List<Product> products = cart.getProducts();<spot></spot>
  ```

Template example files can also use the `$key` placeholder which is replaced with the actual template key in the preview UI, e.g., consider a template with the `var` key:

<compare first-title="Content" second-title="Rendition">

```java
cart.getProducts()$key
```

```java
cart.getProducts().var
```

</compare>

The gutter icons for a postfix template class allow navigating to the corresponding description and before/after files in plugin resources.

**Example:**
[`TryWithResourcesPostfixTemplate`](%gh-ic%/java/java-impl/resources/postfixTemplates/TryWithResourcesPostfixTemplate)
directory containing description files for
[`TryWithResourcesPostfixTemplate`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/template/postfix/templates/TryWithResourcesPostfixTemplate.java)
template.
