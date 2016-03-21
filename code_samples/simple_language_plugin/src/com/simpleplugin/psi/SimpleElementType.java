package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.simpleplugin.SimpleLanguage;
import org.jetbrains.annotations.*;

public class SimpleElementType extends IElementType {
  public SimpleElementType(@NotNull @NonNls String debugName) {
    super(debugName, SimpleLanguage.INSTANCE);
  }
}