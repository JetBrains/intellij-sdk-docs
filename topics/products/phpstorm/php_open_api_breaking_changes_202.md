<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Incompatible PHP OpenAPI changes in PhpStorm 2020.2

<link-summary>List of known breaking PHP Open API changes in 2020.2.</link-summary>

## Union Types Support
PhpStorm 2020.2 introduced support for [PHP union types](https://wiki.php.net/rfc/union_types_v2), which resulted in some PSI-breaking changes.

In earlier versions, type hints in parameters, properties, and return types were parsed inconsistently:
* Return types used a separate `PhpReturnType` wrapper element, which contained the nullability question mark and the actual type's class reference.
* Parameters and properties used no wrapper element: class references and nullability question mark were plain children of `Parameter` or `CLASS_FIELDS`.

As of PhpStorm 2020.2, class references with the question mark are uniformly wrapped into the `PhpTypeDeclaration` element, which is the parent for `PhpReturnType`, `PhpFieldType`, and `PhpParameterType`.

If your existing code fetches a class reference directly from the parent element, it is now required to get `PhpTypeDeclaration` first, and then call `PhpTypeDeclaration#getClassReferences()`.

Before 2020.2:

```java
private void handleParameterBefore(Parameter parameter) {
  ClassReference classReference = PsiTreeUtil.getChildOfType(parameter, ClassReference.class);
  handleReference(classReference);
}
```

After 2020.2:

```java
private void handleParameterAfter(Parameter parameter) {
  PhpTypeDeclaration typeDeclaration = PsiTreeUtil.getChildOfType(parameter, PhpTypeDeclaration.class);
  for (ClassReference classReference : typeDeclaration.getClassReferences()) {
    handleReference(classReference);
  }
}
```

## Deprecated `PhpReturnType.getClassReference()`
As of PhpStorm 2020.2, `PhpReturnType.getClassReference()` is deprecated, since there can be multiple class references.
This method also became nullable, since in earlier versions an incomplete `?` type was parsed just as a question mark, but now it is parsed as `PhpTypeDeclaration` with empty `getClassReferences()`.

Before 2020.2:

```java
private void handleReturnTypeBefore(PhpReturnType returnType) {
  ClassReference classReference = returnType.getClassReference();
  handleReference(classReference);
}
```

After 2020.2:

```java
private void handleReturnTypeAfter(PhpReturnType returnType) {
  for (ClassReference classReference : returnType.getClassReferences()) {
    handleReference(classReference);
  }
}
```
