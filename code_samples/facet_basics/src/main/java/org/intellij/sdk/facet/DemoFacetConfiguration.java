// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.facet;

import com.intellij.facet.FacetConfiguration;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.facet.ui.FacetValidatorsManager;
import com.intellij.openapi.components.PersistentStateComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Provides a custom implementation of the Configuration class for {@link DemoFacet}.
 */
public class DemoFacetConfiguration implements FacetConfiguration, PersistentStateComponent<DemoFacetState> {

  // Manages the data stored with this facet.
  private DemoFacetState myFacetState = new DemoFacetState();

  /**
   * Called by the IntelliJ Platform when saving this facet's state persistently.
   *
   * @return a component state. All properties, public and annotated fields are serialized.
   * Only values which differ from default (i.e. the value of newly instantiated class) are serialized.
   * {@code null} value indicates that the returned state won't be stored, and
   * as a result previously stored state will be used.
   */
  @Nullable
  @Override
  public DemoFacetState getState() {
    return myFacetState;
  }

  /**
   * Called by the IntelliJ Platform when this facet's state is loaded.
   * The method can and will be called several times, if config files were externally changed while IDEA running.
   */
  @Override
  public void loadState(@NotNull DemoFacetState state) {
    myFacetState = state;
  }

  /**
   * Creates a set of editor tabs for this facet, potentially one per context.
   *
   * @param context The context in which a facet is being added/deleted, or modified.
   * @param manager The manager which can be used to access custom validators.
   * @return Array of {@link DemoFacetEditorTab}. In this case size is always 1.
   */
  @Override
  public FacetEditorTab[] createEditorTabs(FacetEditorContext context, FacetValidatorsManager manager) {
    return new FacetEditorTab[]{
        new DemoFacetEditorTab(myFacetState, context, manager)
    };
  }

}
