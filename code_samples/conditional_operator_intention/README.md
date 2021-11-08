# Conditional Operator Converter [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Code Intentions in IntelliJ SDK Docs][docs:conditional_operator_intention]*

## Quickstart

Conditional Operator Converter provides an intention for converting the *ternary operator* into the *if* statement, i.e.:

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
The converter in the `isAvailable` method, has defined the token check to match `JavaTokenType.QUEST`, which is `?` character.

### Extension Points

| Name                           | Implementation                                                    | Extension Point Class           |
|--------------------------------|-------------------------------------------------------------------|---------------------------------|
| `com.intellij.intentionAction` | [ConditionalOperatorConverter][file:ConditionalOperatorConverter] | `PsiElementBaseIntentionAction` |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:conditional_operator_intention]: https://plugins.jetbrains.com/docs/intellij/code-intentions.html
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html

[file:ConditionalOperatorConverter]: ./src/main/java/org/intellij/sdk/intention/ConditionalOperatorConverter.java
