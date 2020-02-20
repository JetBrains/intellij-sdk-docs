// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language;

import com.intellij.lang.Commenter;
import org.jetbrains.annotations.Nullable;

public class SimpleCommenter implements Commenter {
  @Nullable
  @Override
  public String getLineCommentPrefix() {
    return "#";
  }
  
  @Nullable
  @Override
  public String getBlockCommentPrefix() {
    return "";
  }
  
  @Nullable
  @Override
  public String getBlockCommentSuffix() {
    return null;
  }
  
  @Nullable
  @Override
  public String getCommentedBlockCommentPrefix() {
    return null;
  }
  
  @Nullable
  @Override
  public String getCommentedBlockCommentSuffix() {
    return null;
  }
}