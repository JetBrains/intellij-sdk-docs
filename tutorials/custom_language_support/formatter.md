---
title: 15. Formatter
---

*A formatter allows to reformat the code automatically based on code style settings.*

### 15.1. Define a block

The formatter uses the blocks to receive formatting rules for each PSI element.
Our goal is to cover each PSI element with such block. Since each block builds own children blocks we can generate extra blocks or skip any PSI elements.

```java
package com.simpleplugin;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.simpleplugin.psi.SimpleTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SimpleBlock extends AbstractBlock {
    private SpacingBuilder spacingBuilder;

    protected SimpleBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment,
                          SpacingBuilder spacingBuilder) {
        super(node, wrap, alignment);
        this.spacingBuilder = spacingBuilder;
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<Block>();
        ASTNode child = myNode.getFirstChildNode();
        ASTNode previousChild = null;
        while (child != null) {
            if (child.getElementType() != TokenType.WHITE_SPACE &&
                    (previousChild == null || previousChild.getElementType() != SimpleTypes.CRLF ||
                            child.getElementType() != SimpleTypes.CRLF)) {
                Block block = new SimpleBlock(child, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment(),
                        spacingBuilder);
                blocks.add(block);
            }
            previousChild = child;
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Override
    public Indent getIndent() {
        return Indent.getNoneIndent();
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}
```

### 15.2. Define a formatting model builder

Let's define a formatter which removes extra spaces except the single ones around the property separator.

```java
package com.simpleplugin;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.simpleplugin.psi.SimpleTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleFormattingModelBuilder implements FormattingModelBuilder {
    @NotNull
    @Override
    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
        return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(),
                new SimpleBlock(element.getNode(), Wrap.createWrap(WrapType.NONE, false),
                        Alignment.createAlignment(), createSpaceBuilder(settings)), settings);
    }

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings).
                around(SimpleTypes.SEPARATOR).spaceIf(settings.SPACE_AROUND_ASSIGNMENT_OPERATORS).
                before(SimpleTypes.PROPERTY).none();
    }

    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }
}
```

### 15.3. Register the formatter

```xml
<lang.formatter language="Simple" implementationClass="com.simpleplugin.SimpleFormattingModelBuilder"/>
```

### 15.4. Run the project

Now add some extra spaces and reformat the code via *⌥⌘L* shortcut.

![Formatter](img/formatter.png)

[Previous](structure_view_factory.md)
[Top](../custom_language_support_tutorial.md)
[Next](code_style_settings.md)



