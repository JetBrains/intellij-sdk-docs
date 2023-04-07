<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Postfix Completion

<link-summary>Postfix completion allows generating or wrapping existing code into additional constructs without navigating the caret back.</link-summary>

<tldr>

**Product Help:** [Postfix Completion](https://www.jetbrains.com/help/idea/auto-completing-code.html#postfix_completion)

</tldr>

The Postfix Completion functionality allows developers to wrap a code fragment with a predefined template by typing a template abbreviation just after an expression meant to be wrapped, expanded, or modified.
It relieves developers from typing repetitive or non-trivial code or helps to create the code faster, e.g., often it is convenient to write a code part and surround it with the required block without navigating the caret backward.

Consider a situation where a developer is not very familiar with a current Java project API and doesn't know how to name a variable that will be a result of an expression they want to type.
The postfix completion makes it possible to write an expression at first and create a variable assignment with suggested names by adding a postfix template abbreviation at the end of the expression.

Assume that a user typed the following Java code:

```java
void confirmOrder(Cart cart) {
  cart.getDeliveryType().getDeliveryCost()<caret>
}
```

To avoid moving the caret to the beginning of the line, the user can quickly create a variable assignment by adding the `.var` postfix abbreviation and expanding the template:

```java
void confirmOrder(Cart cart) {
  cart.getDeliveryType().getDeliveryCost().var<ENTER>
}
```

When the template is applied, the above code is expanded to:

```java
void confirmOrder(Cart cart) {
  Money deliveryCost = cart.getDeliveryType().getDeliveryCost();<caret>
}
```

In addition, the user can choose the best matching variable name from the name suggestions popup.

These sections describe how to implement Postfix Templates, and their associated building blocks, to plugins:
- [](postfix_templates.md)
- [](advanced_postfix_templates.md)
