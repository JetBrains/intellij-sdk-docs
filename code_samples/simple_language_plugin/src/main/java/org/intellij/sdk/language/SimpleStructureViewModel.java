// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language;

import com.intellij.ide.structureView.*;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.psi.PsiFile;
import org.intellij.sdk.language.psi.SimpleFile;
import org.jetbrains.annotations.NotNull;

public class SimpleStructureViewModel extends StructureViewModelBase implements
      StructureViewModel.ElementInfoProvider {
  public SimpleStructureViewModel(PsiFile psiFile) {
    super(psiFile, new SimpleStructureViewElement(psiFile));
  }
  
  @NotNull
  public Sorter[] getSorters() {
    return new Sorter[]{Sorter.ALPHA_SORTER};
  }
  
  
  @Override
  public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
    return false;
  }
  
  @Override
  public boolean isAlwaysLeaf(StructureViewTreeElement element) {
    return element instanceof SimpleFile;
  }
}