<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Color Scheme Management

<link-summary>Developing new color schemes for existing and custom languages.</link-summary>

Color scheme management in IntelliJ IDEA 12.1 was changed to ease scheme designers' work and make schemes look equally well for different programming languages even if not designed specifically for these languages.
Previously, language plugins were using fixed default colors incompatible, for example, with dark schemes.

The new implementation allows specifying a dependency on a set of standard text attributes linked to a scheme but not to any specific language.
Language-specific attributes still can be set by a scheme designer if needed, but it's optional.
New color schemes have got a new <path>.icls</path> (Idea CoLor Scheme) extension to avoid confusion about compatibility problems with older platform versions:
if only standard attributes are set, they will not be used by the version before 12.1, resulting in different highlighting colors.

## Plugin Developers

### Text Attribute Key Dependency

The easiest and the best way to specify highlighting text attributes is to specify a dependency on one of standard keys defined in [`DefaultLanguageHighlighterColors`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/DefaultLanguageHighlighterColors.java):

```java
static final TextAttributesKey MY_KEYWORD =
  TextAttributesKey.createTextAttributesKey("MY_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
```

The color scheme manager will search first for text attributes specified by the `MY_KEYWORD` key.
If those are not defined explicitly or if all the attributes are empty (undefined), it will search them using the `DEFAULT_KEYWORD` key.
If neither is defined, it will further fall back to a default scheme.

Text attribute keys can be chained, for example, you can define another key as:

```java
static final TextAttributesKey MY_PREDEFINED_SYMBOL =
  TextAttributesKey.createTextAttributesKey("MY_PREDEFINED_SYMBOL", MY_KEYWORD);
```

The rule is the same: if text attributes can not be found by the `MY_PREDEFINED_SYMBOL` key or are empty, the color scheme manager will search for `MY_KEYWORD` and if not found (empty) will further look for `DEFAULT_KEYWORD`.

> A use of fixed default attributes is _strongly discouraged_.
>
{style="note"}

If you are unsure which base key to use, it's better to pick the most generic one, for example, `DefaultLanguageHighlighterColors.IDENTIFIER`.
Remember that using fixed default attributes *will force* a scheme designer to explicitly set up a color for this element.
Otherwise, its default colors may visually conflict with a color scheme.
If the scheme designer doesn't have a language plugin, he will not be able to fix this at all.

### Providing Attributes for Specific Schemes

A language plugin may provide default text attributes for "Default" and "Darcula" bundled schemes or basically for any other scheme if the scheme's name is known.
This can be done in <path>[plugin.xml](plugin_configuration_file.md)</path> by adding an <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.additionalTextAttributes"/></include> providing the name of the file containing desired text attributes, for example:

```xml
<extensions defaultExtensionNs="com.intellij">
  ...
  <additionalTextAttributes
      scheme="Default"
      file="colorSchemes/MyLangDefault.xml"/>
  ...
</extensions>
```

It tells the IDE that the file <path>MyLangDefault.xml</path> must be searched in resources under <path>colorSchemes</path>.
Note that the path should *not* start with a backslash and its fully qualified name (in our case <path>colorSchemes/MyLangDefault.xml</path>) *MUST BE UNIQUE* to avoid naming collisions between different providers.
Thus, adding a language prefix, for example, "MyLang", is highly recommended.

The file itself is an extract from a color scheme with required attributes, for example:

```xml
<?xml version='1.0'?>
<list>
  <option name="MY_VAR">
    <value>
      <option name="FOREGROUND" value="660000"/>
    </value>
  </option>
  <option name="MY_SPECIAL_CHAR">
    <value>
      <option name="FOREGROUND" value="008000"/>
      <option name="BACKGROUND" value="e3fcff"/>
      <option name="FONT_TYPE" value="1"/>
    </value>
  </option>
</list>
```

*Note:* When the scheme is copied via <control>Save as...</control> all its attributes, including the ones defined in the extension, will be copied to the new scheme.
A scheme designer may need to check that these copied attributes do not conflict with their color scheme, although in this case, the plugin is installed, and it should not cause any problems.
Anyway, try to stick with a simple key dependency if possible (note that it works well for "Darcula") and provide explicit attributes only if necessary.

## Scheme Designers

<procedure title="A Typical Workflow for a New Scheme Creation">

1. Choose a scheme which will be used as a base, for example, "Default".
2. Click <control>Save As...</control> and give a name for the new scheme.
3. First set attributes in the <control>General</control> section and proceed with <control>Language Defaults</control>.
4. Check all the languages and adjust language-specific text attributes if necessary.
  In most cases, this may not be needed, but two cases may require an extra action:

    * There is an obsolete plugin which does not use the new color scheme management API and therefore does not utilize the attributes set in <control>Language Defaults</control>.
      Ideally, a report must be created for the language plugin so that its author will fix it eventually.
    * A plugin intentionally sets some default colors and, if the scheme was created from a default one, the colors are copied to the newly created scheme.
      This can be fixed either by resetting all the attributes to restore the inheritance from <control>Language Defaults</control> (see below) or by setting other colors suitable for the scheme.

    The first way is preferable since it will require less effort to change the color scheme later.

</procedure>

### Text Attributes Inheritance

For many language text attributes that do not have any values, there will be a line indicating that the attributes are inherited from a specific section/attributes, such as <control>Keyword</control> in <control>Language Defaults</control>.
If an element has *any* attributes set, only these attributes are used.
All attributes from the base element are ignored.
To *restore* the inheritance, uncheck all the boxes, and click <control>Apply</control>.
