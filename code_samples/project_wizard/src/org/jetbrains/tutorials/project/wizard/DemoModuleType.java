package org.jetbrains.tutorials.project.wizard;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.module.ModuleType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Anna Bulenkova
 */
public class DemoModuleType extends ModuleType<DemoModuleBuilder> {
    public DemoModuleType() {
        super("DEMO_MODULE");
    }

    @NotNull
    @Override
    public DemoModuleBuilder createModuleBuilder() {
        return new DemoModuleBuilder();
    }

    @NotNull
    @Override
    public String getName() {
        return "Demo";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Demo module for educational purposes";
    }

    @Override
    public Icon getBigIcon() {
        return null;
    }

    @Override
    public Icon getNodeIcon(@Deprecated boolean isOpened) {
        return AllIcons.General.Information;
    }
}
