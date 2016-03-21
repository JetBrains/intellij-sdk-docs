package com.intellij.tutorials.facet;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetType;
import com.intellij.openapi.module.Module;

/**
 * @author Anna Bulenkova
 */
public class DemoFacet extends Facet<DemoFacetConfiguration> {
  public static final String ID = "DEMO_FACET_ID";

  public DemoFacet(FacetType facetType,
                   Module module,
                   String name,
                   DemoFacetConfiguration configuration,
                   Facet underlyingFacet) {
    super(facetType, module, name, configuration, underlyingFacet);
  }
}
