[//]: # (title: Language Injection)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

Language injection is the way the IntelliJ Platform handles different languages within the same source file.
Well-known examples are:

* Regular expressions in Java string literals
* SQL queries in Java string literals
* Fenced code blocks within Markdown files

Injected code is always bound to a specific context that depends on the surrounding code, and the IntelliJ Platform treats injected fragments as separate small files that are in a different language.
To ensure highlighting and code-insight features work correctly, these fragments must be a valid statement or expression in the injected language.
The three examples from above would then be shown like this in IntelliJ IDEs:

<tabs>
<tab title="Regex">

![Regex Language Injection](regex_language_injection.png){border-effect="line"}

</tab>
<tab title="SQL">

![SQL Language Injection](sql_language_injection.png){border-effect="line"}

</tab>
<tab title="Markdown">

![Markdown Language Injection](markdown_code_language_injection.png){border-effect="line"}

</tab>
</tabs>

It's not unusual that injected fragments are distributed among, e.g., several strings that are concatenated like it is common for SQL queries.
To solve this, the IntelliJ Platform allows injecting a language into several fragments at once.
Multiple parts are then considered belonging together.

As a plugin author, you can provide language injection in different ways:

* For simple cases, the bundled [IntelliLang plugin](https://plugins.jetbrains.com/plugin/1105-intellilang) can handle injections,
  and plugin authors need to provide a configuration with patterns that specify the context where languages should be injected.
  IntelliLang can also be extended to support unknown custom languages.
* Implementing the `com.intellij.languageInjectionContributor` extension point (EP) provides a high-level API for the injection of other languages.
  For more control over how a language is injected, plugin authors use the `com.intellij.languageInjectionPerformer` EP.
* Implementing the `com.intellij.multiHostInjector` EP gives plugin authors the most control over where and how language injection will take place.

In the following sections, we'll discuss these three options in more detail.

## IntelliLang

First, please read the available [documentation](https://www.jetbrains.com/help/idea/language-injections-settings.html) on IntelliLang.
A good point to start with is to inspect available language injections that you can find in the IntelliLang settings under <menupath>Settings/Preferences | Editor | Language Injections</menupath>.
The injections shown are configured through XML files and loaded automatically.

### Example

Let's take a look at the Java `String.matches()` method that injects the RegExp language into the string of the first argument.
In the IntelliLang settings, it is defined as one possible injection in Java code.

![Language Injection Settings](language_injection_settings.png){border-effect="line"}

Double-clicking on this entry shows the exact context where a RegExp can be injected, and `String.matches()` is one of several possibilities.
On the plugin side, these entries are defined in the file [`javaInjections.xml`](upsource:///plugins/IntelliLang/java-support/resources/javaInjections.xml):

```xml
<injection language="RegExp" injector-id="java">
  <display-name>String (java.lang)</display-name>
  ...
  <place><![CDATA[
    psiParameter()
      .ofMethod(0, psiMethod().withName("matches")
      .withParameters("java.lang.String")
      .definedInClass("java.lang.String"))
  ]]></place>
</injection>
```

The XML file with the injection configurations is loaded through the `org.intellij.intelliLang.injectionConfig` EP in the file
[`intellilang-java-support.xml`](upsource:///plugins/IntelliLang/src/META-INF/intellilang-java-support.xml).

```xml
<extensions defaultExtensionNs="org.intellij.intelliLang">
  <languageSupport
      implementation="org.intellij.plugins.intelliLang.inject.java.JavaLanguageInjectionSupport"/>
  <injectionConfig config="resources/javaInjections.xml"/>
</extensions>
```

### Implementation

It is important to make a distinction between plugin authors who want to provide injections into existing languages and plugin authors who want to provide support for IntelliLang injections in their custom language.
Both define their injections by providing XML configurations and loading them through the <path>plugin.xml</path>.
However, custom language authors need to implement the `org.intellij.intelliLang.languageSupport` EP to make their language and PSI element patterns known to IntelliLang.
Therefore, plugin authors who want to provide injections for known languages can skip the first step.

#### Implement org.intellij.intelliLang.languageSupport EP

Implement the `org.intellij.intelliLang.languageSupport` EP and use
[`AbstractLanguageInjectionSupport`](upsource:///plugins/IntelliLang/src/org/intellij/plugins/intelliLang/inject/AbstractLanguageInjectionSupport.java) as a base class.
Please refer to the API docs of
[`LanguageInjectionSupport`](upsource:///plugins/IntelliLang/src/org/intellij/plugins/intelliLang/inject/LanguageInjectionSupport.java)
for information on methods to override and use
[`JavaLanguageInjectionSupport`](upsource:///plugins/IntelliLang/java-support/org/intellij/plugins/intelliLang/inject/java/JavaLanguageInjectionSupport.java)
as an example implementation.

#### Create Injection Configuration

Create an XML file with the injection configuration.
You can export existing injections from the IntelliLang settings to create a template and then edit it.
[Element patterns](https://plugins.jetbrains.com/docs/intellij/element-patterns.html) are used to specify the context where injections will take place.
Custom language authors can use the specific patterns returned from their implementation of
[`JavaLanguageInjectionSupport.getPatternClasses`](upsource:///plugins/IntelliLang/java-support/org/intellij/plugins/intelliLang/inject/java/JavaLanguageInjectionSupport.java).

The `injection` tag requires the attributes `language` and `injector-id`.
The first one specifies the `language-id`
(see [`Language.getID()`](upsource:///platform/core-api/src/com/intellij/lang/Language.java)) of the language that is injected.
The second one is the id of the host language
(see [`JavaLanguageInjectionSupport.getId()`](upsource:///plugins/IntelliLang/java-support/org/intellij/plugins/intelliLang/inject/java/JavaLanguageInjectionSupport.java)).
For instance, injecting SQLite into Python code is specified by the following opening tag:

```xml
<injection language="SQLite" injector-id="python">
  ...
</injection>
```

Inside an injection, the following tags can be used:

| XML Tag                   | Description                                                                                                                                                                                                                                                                     |
|---------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `<display-name>`          | A short name for the injection.                                                                                                                                                                                                                                                 |
| `<place>`                 | The element pattern that defines where an injection will take place. The content is wrapped in `![CDATA[...]]`.                                                                                                                                                                 |
| `<prefix>` and `<suffix>` | Static content that is wrapped around the injected code, e.g., to make it a valid expression. For example, to a CSS color specification inside a string, it can be wrapped with the prefix `div { color:` and the suffix `;}` to make it a valid CSS expression.                |
| `<value-pattern>`         | A regex for the content that specifies when this injection should be applied. Regex groups can specify the text range of the injection (e.g. `^javascript:(.+)`, see [`xmlInjections-html.xml`](upsource:///plugins/IntelliLang/xml-support/resources/xmlInjections-html.xml)). |
| `<ignore-pattern>`        | A regex for the content that specifies when this injection should not be applied.                                                                                                                                                                                               |

#### Create an XML File to Load the Configuration

Create an XML file <path>myLanguageID-injections.xml</path> next to your <path>plugin.xml</path> that loads the above configuration.
Custom language authors also register their implementation of the `languageSupport` EP there.

```xml
<idea-plugin>
  <extensions defaultExtensionNs="org.intellij.intelliLang">
    <injectionConfig config="path/to/your/injections.xml"/>
  </extensions>
</idea-plugin>
```

#### Load the Injection Configuration in plugin.xml

The injections are an optional dependency that only works when IntelliLang is enabled.
Therefore, you load the configuration optionally in your main <path>plugin.xml</path>:

````xml
<depends
    optional="true"
    config-file="myLanguageID-injections.xml">org.intellij.intelliLang</depends>
````

## LanguageInjectionContributor and LanguageInjectionPerformer

The `com.intellij.languageInjectionContributor` EP provides injection information for the given context in terms of _what_ to inject.
As a plugin author, implement [`LanguageInjectionContributor`](upsource:///platform/core-api/src/com/intellij/lang/injection/general/LanguageInjectionContributor.java) to provide context-specific injections.

For instance, if you want to inject a YAML or JSON to a literal language depending on some conditions, you could implement this interface like this:

```java
public final class MyInjector implements LanguageInjectionContributor {

  public Injection getInjection(@NotNull PsiElement context) {
    if (!isConfigPlace(context)) return null;
    if (shouldInjectYaml(context)) {
      return new SimpleInjection(
          YAMLLanguage.INSTANCE.getID(), "", "", null);
    } else if (shouldInjectJSON(context)) {
      return new SimpleInjection(
          JsonLanguage.INSTANCE.getID(), "", "", null);
    }
    return null;
  }
}
```

Register the implementation in your <path>plugin.xml</path>:

```xml
<languageInjectionContributor
    implementationClass="MyInjector"
    language="YourLanguage"/>
```

If you want more control over how the injection should be done then implement the `com.intellij.languageInjectionPerformer` EP which allows for complex language injections, e.g. for concatenation or interpolation of strings.
If it is not implemented, then the
[`DefaultLanguageInjectionPerformer`](upsource:///plugins/IntelliLang/src/org/intellij/plugins/intelliLang/inject/DefaultLanguageInjectionPerformer.java)
will be used.

For the `com.intellij.languageInjectionPerformer` EP, two methods need to be implemented in [`LanguageInjectionPerformer`](upsource:///platform/core-api/src/com/intellij/lang/injection/general/LanguageInjectionPerformer.java).
First, `isPrimary()` determines if this is the default `LanguageInjectionPerformer` for the language and if it handles most of the injections.
If there is no primary `LanguageInjectionPerformer` found, then a fallback injection will be performed.

The method `performInjection()` does the actual injection into the context PSI element and/or some elements around it if needed in case if they are semantically connected (concatenation injection for instance).

> To use Language Injection API in your project, [add dependency](plugin_dependencies.md#2-project-setup) on the `org.intellij.intelliLang` plugin.
>
{type="note"}

## MultiHostInjector

[`MultiHostInjector`](upsource:///platform/core-api/src/com/intellij/lang/injection/MultiHostInjector.java) registered in `com.intellij.multiHostInjector` EP is a very low-level API, but it gives plugin authors the most freedom.
It performs language injection inside other PSI elements, e.g. inject SQL inside an XML tag text or inject regular expressions into Java string literals.

Plugin authors need to implement `getLanguagesToInject()` to provide a list of places to inject a language to.
For example, to inject regular expressions into Java string literal, you can override this method with something similar to this:

```java
public class MyRegExpToJavaInjector implements MultiHostInjector {

  public void getLanguagesToInject(@NotNull MultiHostRegistrar registrar,
                                   @NotNull PsiElement context) {
    if (context instanceof PsiLiteralExpression && shouldInject(context)) {
      registrar
        .startInjecting(REGEXP_LANG)
        .addPlace(null, null, context, innerRangeStrippingQuotes(context));
    }
  }
}
```

A more complex example is when you need to inject into several fragments at once.
For example, if we have an XML-based DSL:

```xml
<myDSL>
  <method>
    <name>foo</name>
    <body>System.out.println(42);</body>
  </method>
</myDSL>
```

which should be converted to the equivalent Java code:

```java
class MyDsl { void foo() { System.out.println(42); } }
```

Here, we need to inject Java into several places at once, i.e. method name and its body:

```java
public class MyBizarreDSLInjector implements MultiHostInjector {

  public void getLanguagesToInject(@NotNull MultiHostRegistrar registrar,
                                   @NotNull PsiElement context) {
    if (isMethodTag(context)) {
      registrar.startInjecting(JavaLanguage.INSTANCE);

      // construct class header, method header,
      // inject method name, append code block start
      registrar.addPlace("class MyDsl { void ", "() {",
          context, rangeForMethodName(context));

      // inject method body, append closing braces
      // to form a valid Java class structure
      registrar.addPlace(null, "}}", context, rangeForBody(context));
      registrar.doneInjecting();
    }
  }
}
```

Now, inside the editor the injected portion will work as expected where foo is the method name and `System.out.println(42);` will look and feel like a method body with highlighting, completion, and goto definition working.
