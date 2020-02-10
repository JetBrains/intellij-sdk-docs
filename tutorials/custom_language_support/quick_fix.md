---
title: 18. Quick Fix
---

A quick fix for a custom language supports the IntelliJ Platform-based IDE feature [Intention Actions](https://www.jetbrains.com/help/idea/intention-actions.html#apply-intention-actions). 
For the Simple language, this tutorial adds a quick fix that helps to define an unresolved property from its usage.

* bullet list
{:toc}

## 18.1. Update the Element Factory
The `SimpleElementFactory` is updated to include two new methods to support the user choice of creating a new property for the Simple language quick fix.
The new `createCRLF()` method supports adding a newline to the end of the [`test.simple`](/tutorials/custom_language_support/lexer_and_parser_definition.md#run-the-project) file before adding a new property. 
A new overload of `createProperty()` creates a new `key`-`value` pair for Simple language.
```java
{% include /code_samples/simple_language/src/main/java/com/intellij/sdk/language/psi/SimpleElementFactory.java %}
```

## 18.2. Define an Intention Action
The `SimpleCreatePropertyQuickFix` will create a property in the file chosen by the user - in this case a Java file containing a `prefix:key` - and navigate to this property after creation.
Under the hood, `SimpleCreatePropertyQuickFix` is an intention action.
For a more in-depth example of an intention action, see [`conditional_operator_intention`](/code_samples/conditional_operator_intention). 
```java
{% include /code_samples/simple_language/src/main/java/com/intellij/sdk/language/SimpleCreatePropertyQuickFix.java %}
```

## 18.3. Update the Annotator
When a `badProperty` annotation is created, the `badProperty.registerFix()` method is called.
This method call registers the `SimpleCreatePropertyQuickFix` as the intention action for the Intellij Platform to use to correct the problem. 
```java
{% include /code_samples/simple_language/src/main/java/com/intellij/sdk/language/SimpleAnnotator.java %}
```

## 18.4. Run the project
Open the test [Java file](/tutorials/custom_language_support/annotator.md#run-the-project) in an IDE Development Instance running the `simple_language` plugin.

To test `SimpleCreatePropertyQuickFix`, change `simple:website` to `simple:website.url`.
The key `website.url` is highlighted by `SimpleAnnotator` as an invalid key, as shown below.
Choose "Create Property". 

![Quick Fix](img/quick_fix.png){:width="800px"}

The IDE will open the `test.simple` file and add `website.url` as a new key.
Add the new value `jetbrains.com` for the new `website.url` key.

![New Property](img/new_property.png){:width="800px"}

Now switch back to the Java file; the new key is highlighted as valid. 