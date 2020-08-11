# Conditional Operator Converter [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Code Intentions in IntelliJ SDK Docs][docs:conditional_operator_intention]*

## Quickstart

Conditional Operator Converter provides an intention for converting the *ternary operator*
into the *if* statement, i.e.:

```java
public class X {
  void f(boolean isMale) {
    String title = isMale ? "Mr." : "Ms.";
    System.out.println("title = " + title);
 }
}
```

will become:

```java
public class X {
  void f(boolean isMale) {
    String title;
    if (isMale) {
      title = "Mr.";
    } else {
      title = "Ms.";
    }
    System.out.println("title = " + title);
  }
}
```

To invoke the intention action, it is necessary to place the caret on the `?` character of the ternary operator.
The converter in the `isAvailable` method, has defined the token check to match `JavaTokenType.QUEST`, which is `?`
character. 

## Structure

Conditional Operator Converter
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Extension Points

| Name                           | Implementation Class                                              | Interface                                                          |
| ------------------------------ | ----------------------------------------------------------------- | ------------------------------------------------------------------ |
| `com.intellij.intentionAction` | [ConditionalOperatorConverter][file:ConditionalOperatorConverter] | [PsiElementBaseIntentionAction][sdk:PsiElementBaseIntentionAction] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:conditional_operator_intention]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/code_intentions.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html

[file:ConditionalOperatorConverter]: ./src/main/java/org/intellij/sdk/intention/ConditionalOperatorConverter.java
[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml

[sdk:PsiElementBaseIntentionAction]: upsource:///platform/lang-api/src/com/intellij/codeInsight/intention/PsiElementBaseIntentionAction.java
