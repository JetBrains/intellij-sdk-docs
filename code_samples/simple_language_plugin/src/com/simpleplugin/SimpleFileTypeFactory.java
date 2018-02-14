package com.simpleplugin;

import com.intellij.openapi.fileTypes.*;
import org.jetbrains.annotations.NotNull;

public class SimpleFileTypeFactory extends FileTypeFactory {
  @Override
  public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
    fileTypeConsumer.consume(SimpleFileType.INSTANCE);
  }
}
