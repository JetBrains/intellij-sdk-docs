package org.jetbrains.tutorials.sample;

import org.jetbrains.annotations.NotNull;

/**
 * @author Anna Bulenkova
 */
public class DummyApplicationComponentImpl implements DummyApplicationComponent {
  @Override
  public void initComponent() {

  }

  @Override
  public void disposeComponent() {

  }

  @NotNull
  @Override
  public String getComponentName() {
    return "DummyApplicationComponent";
  }
}
