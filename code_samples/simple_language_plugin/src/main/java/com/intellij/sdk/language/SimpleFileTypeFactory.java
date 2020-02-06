package com.intellij.sdk.language;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Note: This class is only used with the fileTypeFactory extension point
 * for versions of the IntelliJ Platform prior to v2019.2
 */
public class SimpleFileTypeFactory extends FileTypeFactory {
  @Override
  public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
    fileTypeConsumer.consume(SimpleFileType.INSTANCE);
  }
}
