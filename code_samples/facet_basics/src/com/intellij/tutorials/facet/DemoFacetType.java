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
  private static final FacetTypeId<DemoFacet> TYPE_ID = new FacetTypeId<DemoFacet>(DemoFacet.ID);

  public DemoFacetType() {
    super(TYPE_ID, DemoFacet.ID, "Demo Facet");
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
  public boolean isSuitableModuleType(ModuleType type) {
    return true;
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return AllIcons.General.Information;
  }
}
