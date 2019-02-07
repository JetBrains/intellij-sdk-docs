package com.intellij.tutorials.facet;

import com.intellij.facet.*;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.module.*;
import org.jetbrains.annotations.*;

import javax.swing.*;

/**
 * @author Anna Bulenkova
 */
public class DemoFacetType extends FacetType<DemoFacet, DemoFacetConfiguration> {
  public static final FacetTypeId<DemoFacet> DEMO_FACET_TYPE_ID = new FacetTypeId<DemoFacet>(DemoFacet.FACET_ID);

  public DemoFacetType() {
    super(DEMO_FACET_TYPE_ID, DemoFacet.FACET_ID, DemoFacet.FACET_NAME);
  }

  @Override
  public DemoFacetConfiguration createDefaultConfiguration() {
    return new DemoFacetConfiguration();
  }

  @Override
  public DemoFacet createFacet(@NotNull Module module,
                               String s,
                               @NotNull DemoFacetConfiguration configuration,
                               Facet facet) {
    return new DemoFacet(this, module, s, configuration, facet);
  }

  @Override
  public boolean isSuitableModuleType(final ModuleType type) {
    return true;
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return AllIcons.General.Information;
  }
}
