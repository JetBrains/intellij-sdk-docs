<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# PSI References

<link-summary>PSI Reference represents a link from a usage of a particular element in the code to the corresponding declaration.</link-summary>

A *reference* in a PSI tree is an object that represents a link from a *usage* of a particular element in the code to the corresponding *declaration*. *Resolving* a reference means locating the declaration to which a specific usage refers.

The most common type of reference is defined by language semantics.
For example, consider a simple Java method:

```java
public void hello(String message) {
  System.out.println(message);
}
```

This simple code fragment contains five references.
The references created by the identifiers `String`, `System`, `out`, and `println` can be resolved to the corresponding declarations in the JDK: the `String` and `System` classes, the `out` field, and the `println` method.
The reference created by the second occurrence of the `message` identifier in `println(message)` can be resolved to the `message` parameter, declared by `String message` in the method header.

Note that `String message` is not a reference and cannot be resolved.
Instead, it's a _declaration_.
It does not refer to any name defined elsewhere; instead, it defines a name by itself.

A reference is an instance of a class implementing the [`PsiReference`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReference.java) interface.
Note that references are distinct from PSI elements.
References created by a PSI element are returned from `PsiElement.getReferences()`, the underlying PSI element of a reference can be obtained from `PsiReference.getElement()`.

To *resolve* the reference - to locate the declaration being referenced - call `PsiReference.resolve()`.
It's very important to understand the difference between `PsiReference.getElement()` and `PsiReference.resolve()`.
The former method returns the _source_ of a reference, while the latter returns its _target_.
In the example above, for the `message` reference, `getElement()` will return the `message` identifier on the second line of the snippet, and `resolve()` will return the `message` identifier on the first line (inside the parameter list).

The process of resolving references is distinct from parsing and is not performed at the same time.
Moreover, it is not always successful.
If the code currently open in the IDE does not compile, or in other situations, it's normal for `PsiReference.resolve()` to return `null` - all code working with references must be prepared to handle that.

> Please see also [](psi_performance.md#cache-results-of-heavy-computations).
>

## Contributed References

In addition to references defined by the semantics of the programming language, the IDE recognizes many references determined by the semantics of the APIs and frameworks used in code.
Consider the following example:

```java
File file = new File("foo.txt");
```

Here, "foo.txt" has no special meaning from the point of view of the Java syntax - it's just a string literal.
However, opening this example in IntelliJ IDEA and having a file called "foo.txt" in the same directory, one can <shortcut>Ctrl/Cmd+Click</shortcut> on "foo.txt" and navigate to the file.
This works because the IDE recognizes the semantics of `new File(...)` and contributes a reference into the string literal passed as a parameter to the method.

Typically, references can be contributed to elements that don't have their own references, such as string literals and comments.
References are also often contributed to non-code files, such as XML or JSON.

Contributing references is one of the most common ways to extend an existing language.
For example, your plugin can contribute references to Java code, even though the Java PSI is part of the platform and not defined in your plugin.

Implement [`PsiReferenceContributor`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReferenceContributor.java) registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.psi.referenceContributor"/></include>.

Attribute `language` should be set to the Language ID where this contributor applies to.
The exact places to contribute references to are then specified using [](element_patterns.md) in calls to `PsiReferenceRegistrar.registerReferenceProvider()`.

See also [Reference Contributor tutorial](reference_contributor.md).

## References with Optional or Multiple Resolve Results

In the simplest case, a reference resolves to a single element, and if resolving fails, the code is incorrect, and the IDE needs to highlight it as an error.
However, there are cases when the situation is different.

The first case is *soft references*.
Consider the `new File("foo.txt")` example above.
If the IDE can't find the file "foo.txt", it doesn't mean that an error needs to be highlighted - maybe the file is only available at runtime.
Such references return `true` from the `PsiReference.isSoft()` method, which can then be used in inspection/annotator to skip highlighting them completely or use a lower severity.

The second case is *polyvariant references*.
Consider the case of a JavaScript program.
JavaScript is a dynamically typed language, so the IDE cannot always precisely determine which method is being called at a particular location.
To handle this, it provides a reference that can be resolved to multiple possible elements.
Such references implement the [`PsiPolyVariantReference`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiPolyVariantReference.java) interface.

For resolving a `PsiPolyVariantReference`, you call its `multiResolve()` method.
The call returns an array of [`ResolveResult`](%gh-ic%/platform/core-api/src/com/intellij/psi/ResolveResult.java) objects.
Each of the objects identifies a PSI element and also specifies whether the result is valid.
For example, suppose you have multiple Java method overloads and a call with arguments not matching any of the overloads.
In that case, you will get back `ResolveResult` objects for all the overloads, and `isValidResult()` returns `false` for all of them.

## Searching for References

Resolving a reference means going from usage to the corresponding declaration.
To perform the navigation in the opposite direction - from a declaration to its usages - perform a _references search_.

To perform a search using [`ReferencesSearch`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/searches/ReferencesSearch.java), specify the element to search for, and optionally other parameters such as the scope in which the reference needs to be searched.
The created [`Query`](%gh-ic%/platform/core-api/src/com/intellij/util/Query.java) allows obtaining all results at once or iterating over the results one by one.
The latter allows stopping processing as soon as the first (matching) result has been found.

## Implementing References

Please refer to the [guide](references_and_resolve.md) and corresponding [tutorial](reference_contributor.md) for more information.
