<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 2. Parsing Test

<tldr>

**Tested Functionality**: [](lexer_and_parser_definition.md)

</tldr>

<include from="tests_prerequisites.md" element-id="custom_language_testing_tutorial_header"></include>

For more complex Lexers (e.g., having additional logic), it is advisable to add separate tests inheriting from [`LexerTestCase`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/LexerTestCase.java).

## Update Grammar and Regenerate the Parser
Before creating the parsing test, ensure the parser definition (<path>Simple.bnf</path>) includes the lines shown below.
These additional lines facilitate testing incorrect keys.

If the lines below are not present in <path>Simple.bnf</path>, replace the existing `property` definition with the lines below.
Don't forget to regenerate the parser after updating the file!
Right-click on the <path>Simple.bnf</path> file and select <control>Generate Parser Code</control>.

```bnf
property ::= (KEY? SEPARATOR VALUE?) | KEY {
  pin=3
  recoverWhile="recover_property"
  mixin="org.intellij.sdk.language.psi.impl.SimpleNamedElementImpl"
  implements="org.intellij.sdk.language.psi.SimpleNamedElement"
  methods=[getKey getValue getName setName getNameIdentifier getPresentation]
}
private recover_property ::= !(KEY|SEPARATOR|COMMENT)
```

## Define Input Test Data
Create the <path>ParsingTestData.simple</path> properties file in the <path>testData</path> folder.
Note the last few lines define a purposely incorrect key.

```properties
```
{src="simple_language_plugin/src/test/testData/ParsingTestData.simple"}

## Copy the Expected PSI Tree
The easiest way to get the expected PSI structure for any file is to use the [built-in PSI viewer](explore_api.md#internalMode).
Run the project and use <ui-path>Tools | View PSI Structure...</ui-path>
(enable [Internal Mode](enabling_internal.md) explicitly for the IDE instance if the menu is not available).

![PSI Tree Copy](plugin_copy_psi.png)

Use the <control>Copy PSI</control> button to copy the expected PSI structure to the clipboard.

## Define the Output Reference Test Data
Create a file <path>ParsingTestData.txt</path> with the copied PSI tree.

```properties
```
{src="simple_language_plugin/src/test/testData/ParsingTestData.txt"}

## Define a Parsing Test
Subclass [`ParsingTestCase`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/ParsingTestCase.java) to create `SimpleParsingTest`:
Override `getTestDataPath()`, and return the path from the root of this plugin module to the <path>testData</path> directory.

```java
```
{src="simple_language_plugin/src/test/java/org/intellij/sdk/language/SimpleParsingTest.java"}

## Run the Test

<include from="custom_language_testing_snippets.md" element-id="runTests"/>
