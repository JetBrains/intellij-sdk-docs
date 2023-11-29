// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.action;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import icons.SdkIcons;
import org.jetbrains.annotations.NotNull;

/**
 * Demonstrates adding an action group to a menu statically in plugin.xml, and then creating a menu item within
 * the group at runtime. See plugin.xml for the declaration of {@link DynamicActionGroup}, and note the group
 * declaration does not contain an action. {@link DynamicActionGroup} is based on {@link ActionGroup} because menu
 * children are determined on rules other than just positional constraints.
 */
public class DynamicActionGroup extends ActionGroup {

  /**
   * Returns an array of menu actions for the group.
   *
   * @param e Event received when the associated group-id menu is chosen.
   * @return AnAction[] An instance of {@link AnAction}, in this case containing a single instance of the
   * {@link PopupDialogAction} class.
   */
  @Override
  public AnAction @NotNull [] getChildren(AnActionEvent e) {
    return new AnAction[]{
            new PopupDialogAction("Action Added at Runtime", "Dynamic Action Demo", SdkIcons.Sdk_default_icon)
    };
  }

}
