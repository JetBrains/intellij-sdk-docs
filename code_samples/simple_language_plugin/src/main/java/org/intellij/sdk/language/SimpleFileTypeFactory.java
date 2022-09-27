// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: This class is only used with the {@code com.intellij.fileTypeFactory} extension point
 * for versions of the IntelliJ Platform prior to v2019.2.
 *
 * @see <a href="https://plugins.jetbrains.com/docs/intellij/language-and-filetype.html#register-the-filetype">Custom Language Tutorial</a>
 */
@SuppressWarnings("deprecation")
public class SimpleFileTypeFactory extends FileTypeFactory {

  @Override
  public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
    fileTypeConsumer.consume(SimpleFileType.INSTANCE);
  }

}
