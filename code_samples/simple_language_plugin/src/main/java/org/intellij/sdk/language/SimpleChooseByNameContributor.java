// Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import org.intellij.sdk.language.psi.SimpleProperty;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SimpleChooseByNameContributor implements ChooseByNameContributor {

  @Override
  public String @NotNull [] getNames(Project project, boolean includeNonProjectItems) {
    List<SimpleProperty> properties = SimpleUtil.findProperties(project);
    List<String> names = new ArrayList<>(properties.size());
    for (SimpleProperty property : properties) {
      if (property.getKey() != null && property.getKey().length() > 0) {
        names.add(property.getKey());
      }
    }
    return names.toArray(new String[names.size()]);
  }

  @Override
  public NavigationItem @NotNull [] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
    // TODO: include non project items
    List<SimpleProperty> properties = SimpleUtil.findProperties(project, name);
    return properties.toArray(new NavigationItem[properties.size()]);
  }

}
