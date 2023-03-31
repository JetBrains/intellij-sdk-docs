<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Incompatible PHP OpenAPI changes in PhpStorm 2020.3

<link-summary>List of known breaking PHP Open API changes in 2020.3.</link-summary>

## PHP 8 Support
PhpStorm 2020.3 introduces support for the upcoming PHP 8, which results in several noticeable changes in the PhpStorm internals.

### Throw Expression
In PHP 8, the [throw expression has been converted to a statement](https://wiki.php.net/rfc/throw_expression), so that it can now be used in arrow functions and coalescing and ternary expressions.

The following changes were introduced:
* The PSI element `com.jetbrains.php.lang.psi.elements.PhpThrow` is now deprecated, and the new PSI element `com.jetbrains.php.lang.psi.elements.PhpThrowExpression` is introduced instead.

* We encourage using `PhpThrowExpression`, since such expressions can appear in more places.

* `PhpThrow` PSI layout has changed: it is now a `Statement` with a single child `PhpThrowExpression`. The `PhpThrow#getArgument` delegates to `PhpThrowExpression#getArgument` for now.

* In PhpStorm 2020.2, the `PhpThrow` class will be removed and replaced with `StatementImpl`.

See the [YouTrack issue](https://youtrack.jetbrains.com/issue/WI-54357) for details.

### Trailing Comma in Parameter Lists
Previously supported only in argument lists, [trailing commas can now be used in parameter lists](https://wiki.php.net/rfc/trailing_comma_in_parameter_list), as well.

The following changes were introduced:
* The last psi-element of parameter lists can now be `PhpTokenTypes.opCOMMA`. This might affect getting the last parameter: earlier the last psi-element of `ParameterList` was always a parameter but now it can also  be a comma.

* If you are using `PsiElement.getLastChild()` to get the last parameter, it is now recommended replacing it with getting a parameter by index via `ParameterList.getParameter(int)`.

See the [YouTrack issue](https://youtrack.jetbrains.com/issue/WI-54481) for details.

### Non-Capturing Catches
With this PHP 8 change, it is now possible to catch exceptions [without capturing them to variables](https://wiki.php.net/rfc/non-capturing_catches).

The following changes were introduced:
* `Catch` now might not have a variable inside.
  The `@Nullable` annotation for the method `Catch.getException()` that returns a variable was already available.
  However, if you are getting a variable from `Catch` differently, or ignoring the `@Nullable` annotation, you need to be aware of this case effective since PHP 8.0.

See the [YouTrack issue](https://youtrack.jetbrains.com/issue/WI-54484) for details.

### Constructor Property Promotion
The new shorthand syntax allows defining class properties by ["promoting" the constructor parameters](https://wiki.php.net/rfc/constructor_promotion) with a visibility keyword (`public`, `protected`, or `private`).

The following changes were introduced:
* `com.jetbrains.php.lang.psi.elements.Parameter` now can contain one of `com.jetbrains.php.lang.lexer.PhpTokenTypes#tsVARIABLE_MODIFIERS` as a child element.

See the [YouTrack issue](https://youtrack.jetbrains.com/issue/WI-54485) for details.

### Nullsafe Operator
The [new nullsafe operator](https://wiki.php.net/rfc/nullsafe_operator) `?->` with full short-circuiting allows applying the null-coalescing behavior to method calls, thus eliminating the need for additional null checks.

The following changes were introduced:
* `com.jetbrains.php.lang.psi.elements.MemberReference` now has an optional child `com.jetbrains.php.lang.lexer.PhpTokenTypes#opQUEST` between the class reference and `com.jetbrains.php.lang.lexer.PhpTokenTypes#ARROW`.

See the [YouTrack issue](https://youtrack.jetbrains.com/issue/WI-54639) for details.

### Named Parameters
With [named parameters](https://wiki.php.net/rfc/named_params), you can pass arguments to a function based on the parameter name, rather than its position.

The following changes were introduced:
* The layout of `com.jetbrains.php.lang.psi.elements.ParameterList` for call sites has changed.
  Earlier, it was a comma-separated list of arguments; now, each argument can have optional previous siblings: `com.jetbrains.php.lang.lexer.PhpTokenTypes#IDENTIFIER` and `com.jetbrains.php.lang.lexer.PhpTokenTypesopCOLON`.

See the [YouTrack issue](https://youtrack.jetbrains.com/issue/WI-54640) for details.

### Attributes
With [attributes](https://wiki.php.net/rfc/attributes_v2), you can provide structured, syntactic metadata to declarations of classes, properties, functions, methods, parameters, and constants.

The following changes were introduced:
* New PSI elements `PhpAttribute` and `PhpAttributesList` were added.

* Possible attributes' owners (`com.jetbrains.php.lang.psi.elements.Function`, `com.jetbrains.php.lang.psi.elements.Parameter`, and so on) can now have zero or more instances of `PhpAttributesList` as first children.
  This means that now it is not safe to assume that `PhpPsiElement#getFirstChild` will return some token.
  We encourage finding the needed tokens by `IElementType` manually.

* If a PHPDoc comment of a named element is preceded by `PhpAttribute`, it will not be a sibling of a named element, but rather a child.
  We encourage using `PhpNamedElement#getDocComment` (which is already updated to reflect the changes) instead of finding the PHPDoc comment manually.

* The stub tree structure has changed for some elements.
  If this causes problems in plugins, consider using higher-level facilities or [contact support](https://www.jetbrains.com/help/phpstorm/getting-started.html#contact-support) for additional help.

See the [YouTrack issue](https://youtrack.jetbrains.com/issue/WI-53163) for details.

### Match Expression
New [match expressions](https://wiki.php.net/rfc/match_expression_v2) provide functionality similar to `switch`, but with safer semantics and the ability to return values.

The following changes were introduced:
* New PSI elements: `PhpMatchExpression` and `PhpMatchArm`.

* A list of match arms is obtained via `PhpMatchExpression#getMatchArms()`. The default match arm is not included in this list and is obtained by calling `PhpMatchExpression#getDefaultMatchArm()`.

* Each `PhpMatchArm` provides a list of conditions via `PhpMatchArm#getConditions()`. The method returns an empty list for the default match arm.

* Match arm body expression is obtained by invoking `PhpMatchArm#getBodyExpression()`.

See the [YouTrack issue](https://youtrack.jetbrains.com/issue/WI-54356) for details.

## Twig Support Changes
The following changes were introduced:
* PSI layout of Twig variables and fields has been changed.
  Earlier, Twig variables and fields used to be parsed as `PsiIdentifier`; now they are wrapped into new PSI elements: `TwigVariable` and `TwigField`.

* `TwigField` may be nested and contain a variable or other fields inside.
