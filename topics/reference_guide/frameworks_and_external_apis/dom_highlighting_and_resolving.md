<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# DOM Highlighting and Resolving

<link-summary>Error highlighting in DOM-based XML files and resolving references between DOM elements.</link-summary>

DOM provides code insight in XML files described by a [DOM model](defining_dom_model.md):
error highlighting based on the model, and resolving, completion, Find Usages, and Rename refactoring for references between model elements.

## Highlighting Annotations

DOM supports error checking and highlighting based on annotations added to DOM elements
(not to be confused with Java annotations).
Implement `DomElementsAnnotator`
and return its instance from the overridden `DomFileDescription.createAnnotator()` method.
In `DomElementsAnnotator.annotate(DomElement element, DomElementsProblemsHolder annotator)`,
report all errors and warnings in the element's subtree via [`DomElementsProblemsHolder.createProblem()`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/highlighting/DomElementsProblemsHolder.java).

## Automatic Highlighting

An inspection extending [`BasicDomElementsInspection`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/highlighting/BasicDomElementsInspection.java) highlights the following errors automatically:

- [`@Required`](defining_dom_model.md#required-children) element missing or having empty text
- XML value that can't be converted by a `Converter`
- non-unique name where it must be unique

The last check requires annotating the name getter with [`@NameValue`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/NameValue.java).
It uses the `DomFileDescription.getIdentityScope()` method to get the element defining the root scope in which the name must be unique.

To suppress spellchecking, annotate the DOM element with [`@NoSpellchecking`](%gh-ic%/spellchecker/src/com/intellij/spellchecker/xml/NoSpellchecking.java).

## Resolving

[`GenericDomValue<T>`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/GenericDomValue.java) and its sub-interface [`GenericAttributeValue<T>`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/GenericAttributeValue.java) accept any class as `T`.
For example, `GenericDomValue<PsiClass>` can be interpreted as a reference to a class.
In general, such a value can be considered a reference to an object of class `T`.
This is not very useful for strings or enums, but the idea applies to DOM elements.
XML often declares an object in one place and references it in a tag or attribute value elsewhere.
To model such a reference, declare a method like `GenericValue<MyDomElement> getMyDomElementReference()`,
and specify a converter that finds an instance of `MyDomElement` in the model with the name specified in the `GenericDomValue`.

DOM provides such a converter out of the box: `DomResolveConverter`.
Reference getters don't need any annotations, as DOM resolves names automatically.
It searches elements by the name returned from the method annotated with `@NameValue`.
The `DomResolveConverter` constructor takes a parameter, so the class can't be referenced in the [`@Convert`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/Convert.java) annotation, but its subclasses can.
To explicitly resolve a reference to a [`DomElement`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomElement.java) model-wide, use the [`@Resolve`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/Resolve.java) annotation parameterized with the target class.
The resolve scope is taken from `DomFileDescription.getResolveScope()`.

Auto-resolving in DOM also provides code insight in the XML editor: error highlighting, completion, Find Usages, and Rename refactoring.
Unresolved references are highlighted and completed.
To provide this code insight in a custom converter,
extend [`ResolvingConverter`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ResolvingConverter.java)
instead of [`Converter`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/Converter.java).
Its `getVariants()` method must return all targets the reference can resolve to,
similar to [`PsiReference`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReference.java).

To choose a `Converter` depending on other values (for example, in a sibling or parent element) or a runtime condition (for example, the presence or version of a library), use [`WrappingConverter`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/WrappingConverter.java).
See also [`GenericDomValueConvertersRegistry`](%gh-ic%/java/openapi/src/com/intellij/util/xml/converters/values/GenericDomValueConvertersRegistry.java) for managing an extensible registry of available converters.
