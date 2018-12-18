---
title: PSI References
---

A *reference* in a PSI tree is an object that represents a link from a *usage* of a certain element in the code
to the corresponding *declaration*. *Resolving* a reference means locating the declaration to which a specific usage
refers.

The most common type of references is defined by language semantics. For example, consider a simple Java method:

```java
public void hello(String message) {
    System.out.println(message);
}
```

This simple code fragment contains five references. The references created by the identifiers `String`, `System`, `out` and
`println` can be resolved to the corresponding declarations in the JDK: the `String` class, the `System` class, the `out`
field and the `println` method. The reference for the `message` identifier (created by it second occurrence: 
`println(message)`) can be resolved to the `message` parameter, declared by `String message` in the method header.

Note that `message` identifier, declared by `String message`, is not a reference, and cannot be resolved. Instead, it's a 
_declaration_. It does not refer to any name defined elsewhere; instead, it defines a name by itself.

A reference is an instance of a class implementing the [`PsiReference`](upsource:///platform/core-api/src/com/intellij/psi/PsiReference.java) interface.
Note that references are distinct from PSI elements. You can obtain the references created by a PSI element by calling
`PsiElement.getReferences()`, and can go back from a reference to an element by calling `PsiReference.getElement()`.

To *resolve* the reference - to locate the declaration being referenced - you call `PsiReference.resolve()`. It's very
important to understand the difference between the `getElement()` and `resolve()` methods at `PsiReference` interface. 
The former method returns the _source_ of a reference, while the latter returns its _target_. In the example above, 
for the `message` reference, `getElement()` will return the `message` identifier at `println(message)`, and 
`resolve()` will return the `message` identifier at `String message`.

The process of resolving references is distinct from parsing, and is not performed at the same time. Moreover, it is
not always successful. If the code currently open in the IDE does not compile, or in other situations, it's normal
for `PsiReference.resolve()` to return `null`, and if you work with references, you need to be able to handle that in your code.


## Contributed References

In addition to references defined by the semantics of the programming language, IntelliJ IDEA recognizes many references
which are determined by the semantics of the APIs and frameworks used in your code. Consider the following example:

```java
File f = new File("foo.txt");
```

Here, "foo.txt" has no special meaning from the point of view of the Java syntax - it's just a string literal. However,
if you open this example in IntelliJ IDEA, and if you have a file called "foo.txt" in the same directory, you'll notice
that you're able to Ctrl-click on "foo.txt" and navigate to the file. This works because IntelliJ IDEA recognizes the
semantics of `new File(...)` and _contributes a reference_ into the string literal passed as a parameter to the method.

Normally, references can be contributed into elements which don't have their own references, such as string literals
and comments. References are also often contributed into non-code files, such as XML or JSON.

Contributing references is one of the most common ways to extend an existing language. For example, your plugin can
contribute references to Java code, even though the Java PSI is part of the platform and not defined in your plugin.

To contribute your own references, see the [reference contributor tutorial](/tutorials/custom_language_support/reference_contributor.md).


## References with Optional or Multiple Resolve Results

In the simplest case, a reference resolves to a single element, and if the resolve fails, this means that the
code is incorrect and the IDE needs to highlight it as an error. However, there are cases when the situation is different.

The first case is *soft references*. Consider the `new File("foo.txt")` example above. If IntelliJ IDEA can't find
the file "foo.txt", it doesn't mean that an error needs to be highlighted - maybe the file is only available at runtime.
Such references return `true` from the `PsiReference.isSoft()` method.

The second case is *polyvariant references*. Consider the case of a JavaScript program. JavaScript is a dynamically
typed language, so the IDE cannot always precisely determine which method is being called at a particular location.
To handle this, it provides a reference that can be resolved to multiple possible elements.
Such references implement the [`PsiPolyVariantReference`](upsource:///platform/core-api/src/com/intellij/psi/PsiPolyVariantReference.java) interface.

For resolving a `PsiPolyVariantReference`, you call its `multiResolve()` method. The call returns an array of
[`ResolveResult`](upsource:///platform/core-api/src/com/intellij/psi/PsiResolveResult.java) objects. Each of the
objects identifies a PSI element and also specifies whether the result is valid. For example, if you have multiple
Java method overloads and a call with arguments not matching any of the overloads, you will get
back `ResolveResult` objects for all of the overloads, and `isValidResult()` will return false for all of them.


## Searching for References

As you already know, resolving a reference means going from a usage to the corresponding declaration. To perform the
navigation in the opposite direction - from a declaration to its usages - you need to perform a **references search**.

To perform a references search, you use the 
[`ReferencesSearch`](upsource:///platform/indexing-api/com/intellij/psi/search/searches/ReferencesSearch.java) class.
To perform a search, you need to specify the *element* to search for, and optionally other parameters such as the
scope in which the reference needs to be searched. You get back a *query* object that allows you to get all results
as an array, or to iterate over the results one by one. If you don't need to collect all the results, it's more efficient
to use the iteration, because it allows you to stop the processing once you've found the element you need.


## Implementing References

The documentation above covers the key points of accessing references. If you need to create your own references
(if you're implementing a custom language or a reference contributor for an existing language),
please refer to the [guide](/reference_guide/custom_language_support/references_and_resolve.md) and
[tutorial](/tutorials/custom_language_support/reference_contributor.md) for implementing references.
