package com.intellij.tutorials.facet;

import com.intellij.facet.FacetConfiguration;
import com.intellij.facet.ui.*;
import com.intellij.openapi.util.*;
import org.jdom.Element;
import org.jetbrains.annotations.*;

import javax.swing.*;
import java.awt.*;

/**
 * @author Anna Bulenkova
 */
public class DemoFacetConfiguration implements FacetConfiguration {
  public static final String DEMO_FACET_TAG_NAME = "DemoFacet";
  public static final String PATH_TO_SDK_ATTR_NAME = "pathToSdk";
  private String myPathToSdk = "";
  JTextField myPath = new JTextField(myPathToSdk);

  @Override
  public FacetEditorTab[] createEditorTabs(FacetEditorContext context, FacetValidatorsManager manager) {
    return new FacetEditorTab[]{
        new FacetEditorTab() {

          @NotNull
          @Override
          public JComponent createComponent() {
            JPanel top = new JPanel(new BorderLayout());
            top.add(new JLabel("Path to SDK: "), BorderLayout.WEST);
            top.add(myPath);
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(top, BorderLayout.NORTH);
            return panel;
          }

          @Nls
          @Override
          public String getDisplayName() {
            return "Demo Framework";
          }

          @Override
          public boolean isModified() {
            return myPath.getText().equalsIgnoreCase(myPathToSdk);
//                        return !StringUtil.equalsIgnoreWhitespaces(myPath.getText(), myPathToSdk);
          }

          @Override
          public void reset() {
            myPath.setText(myPathToSdk);
          }

          @Override
          public void disposeUIResources() {
          }
        }
    };
  }

  @Override
  public void readExternal(Element element) throws InvalidDataException {
    Element facet = element.getChild(DEMO_FACET_TAG_NAME);
    if (facet != null) {
      myPathToSdk = facet.getAttributeValue(PATH_TO_SDK_ATTR_NAME, "");
      myPath.setText(myPathToSdk);
    }
  }

  @Override
  public void writeExternal(Element element) throws WriteExternalException {
    Element facet = new Element(DEMO_FACET_TAG_NAME);
    facet.setAttribute(PATH_TO_SDK_ATTR_NAME, myPathToSdk);
    element.addContent(facet);
  }
}
