---
title: Incompatible Changes in IntelliJ Platform and Plugins API 2018.*
---

<!--
See the note on how to document new problems on the main page reference_guide/api_changes_list.md 
-->

<style>
  table {
    width:100%;
  }
  th, tr, td {
    width:50%;
  }
</style>

## Changes in IntelliJ Platform 2018.1

|  Change | How to deal with it |
|---------|---------------------|
| `com.google.common.base.Objects.ToStringHelper` class removed | Use `com.google.common.base.MoreObjects.ToStringHelper` instead |
| `com.google.common.util.concurrent.Futures.get` method removed | Use `com.google.common.util.concurrent.Futures.getChecked` instead |
| [`org.tmatesoft.svn`](https://svnkit.com/index.html) package removed | Use classes from `org.jetbrains.idea.svn` instead |
| `org.jetbrains.idea.svn.svnkit.SvnKitManager` class removed | Use `org.jetbrains.idea.svn.api.ClientFactory` instead |
| `clojure` package removed | Clojure 1.8 jars are removed from IntelliJ Ultimate package, provide your own jars if needed |
| `com.intellij.ide.structureView.newStructureView.StructureViewComponent.getTreeBuilder` method removed | Use `queueUpdate` and `getTree` instead |

## Changes in PhpStorm and PHP plugin 2018.1

|  Change | How to deal with it |
|---------|---------------------|
| `com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.DOC_COMMENT` field type changed from `com.intellij.psi.tree.ILazyParseableElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocCommentElementType` | In most of the cases it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type. |
| `com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocSpecialTag` field type changed from `com.intellij.psi.tree.ILazyParseableElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocMethodTagElementType` | In most of the cases it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type. |
| `com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocTag` field type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocTagElementType` | In most of the cases it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type. |
| `com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocReturn` field type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocTagElementType` | In most of the cases it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type. |
| `com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocMixin` field type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocTagElementType` | In most of the cases it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type. |
| `com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocThrows` field type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocTagElementType` | In most of the cases it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type. |
| `com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocParam` field type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocTagElementType` | In most of the cases it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type. |
| `com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocInlineTag` field type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocTagElementType` | In most of the cases it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type. |
| `com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocMethod` field type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocMethodElementType` | In most of the cases it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type. |
| `com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocProperty` field type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocPropertyElementType` | In most of the cases it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type. |
| `com.jetbrains.php.lang.documentation.phpdoc.parser.tags.PhpDocTagParser.getElementType` method return type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.intellij.psi.tree.IElementType` | In most of the cases it's enough to recompile the code. It's also necessary to change the return type of subclasses. |
| `com.jetbrains.php.lang.documentation.phpdoc.psi.impl.PhpDocCommentImpl` superclass changed from `com.intellij.psi.impl.source.tree.LazyParseablePsiElement` to `com.jetbrains.php.lang.psi.elements.impl.PhpPsiElementImpl` | It's necessary to check that the code doesn't rely on the superclass. |
| `com.jetbrains.php.lang.documentation.phpdoc.psi.impl.tags.PhpDocTagImpl` superclass changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.impl.PhpDocPsiElementImpl` to `com.jetbrains.php.lang.psi.elements.impl.PhpTypedElementImpl` | It's necessary to check that the code doesn't rely on the superclass. The change also affects all the inheritors of `PhpDocTagImpl`. |
| `com.jetbrains.php.lang.documentation.phpdoc.psi.impl.tags.PhpDocMethodTagImpl` superclass changed from `com.intellij.psi.impl.source.tree.LazyParseablePsiElement` to `com.jetbrains.php.lang.documentation.phpdoc.psi.impl.tags.PhpDocTagImpl` | It's necessary to check that the code doesn't rely on the superclass. |
| `com.jetbrains.php.lang.documentation.phpdoc.psi.impl.tags.PhpDocMethodImpl` superclass changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.impl.PhpDocPsiElementImpl` to `com.jetbrains.php.lang.psi.elements.impl.PhpNamedElementImpl` | It's necessary to check that the code doesn't rely on the superclass. |