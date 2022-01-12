// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.facet;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetType;
import com.intellij.openapi.module.Module;

/**
 * Demo Facet class. Everything is handled by the super class.
 */
public class DemoFacet extends Facet<DemoFacetConfiguration> {

  public DemoFacet(FacetType facetType,
                   Module module,
                   String name,
                   DemoFacetConfiguration configuration,
                   Facet underlyingFacet) {
    super(facetType, module, name, configuration, underlyingFacet);
  }

}
