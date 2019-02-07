package com.intellij.tutorials.facet;

import com.intellij.facet.FacetConfiguration;
import com.intellij.facet.ui.*;
import com.intellij.openapi.components.PersistentStateComponent;
import org.jdom.Element;
import org.jetbrains.annotations.*;
import javax.swing.*;
import java.awt.*;

/**
 * @author Anna Bulenkova
 */
public class DemoFacetConfiguration implements FacetConfiguration, PersistentStateComponent<DemoFacetConfiguration.DemoFacetState> {
  private DemoFacetState myState = new DemoFacetState();
  private JTextField myPath = new JTextField(myState.myPathToSdk);
  static final String PATH_TO_SDK_ATTR_NAME = "pathToSdk";
  
  
  public class DemoFacetState extends Element {
    static final String DEMO_FACET_TAG_NAME = "DemoFacet";
    
    public String myPathToSdk;
    
    public DemoFacetState() {
      super(DEMO_FACET_TAG_NAME);
      myPathToSdk = "";
      this.setAttribute(PATH_TO_SDK_ATTR_NAME, myPathToSdk);
    }
  }
  
  /**
   * @return a component state. All properties, public and annotated fields are serialized. Only values, which differ
   */
  @Nullable
  @Override
  public DemoFacetState getState() {
    return myState;
  }
  
  /**
   * This method is called when new component state is loaded. The method can and will be called several times, if
   */
  @Override
  public void loadState(@NotNull DemoFacetState state) { myState = state; }
  
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
                return myPath.getText().equalsIgnoreCase(myState.myPathToSdk);
//                        return !StringUtil.equalsIgnoreWhitespaces(myPath.getText(), myPathToSdk);
              }
              
              @Override
              public void reset() {
                myPath.setText(myState.myPathToSdk);
              }
              
              @Override
              public void disposeUIResources() {
              }
            }
    };
  }
}
