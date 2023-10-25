// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.intellij.sdk.toolWindow;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Objects;

final class CalendarToolWindowFactory implements ToolWindowFactory, DumbAware {

  @Override
  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    CalendarToolWindowContent toolWindowContent = new CalendarToolWindowContent(toolWindow);
    Content content = ContentFactory.getInstance().createContent(toolWindowContent.getContentPanel(), "", false);
    toolWindow.getContentManager().addContent(content);
  }

  private static class CalendarToolWindowContent {

    private static final String CALENDAR_ICON_PATH = "/toolWindow/Calendar-icon.png";
    private static final String TIME_ZONE_ICON_PATH = "/toolWindow/Time-zone-icon.png";
    private static final String TIME_ICON_PATH = "/toolWindow/Time-icon.png";

    private final JPanel contentPanel = new JPanel();
    private final JLabel currentDate = new JLabel();
    private final JLabel timeZone = new JLabel();
    private final JLabel currentTime = new JLabel();

    public CalendarToolWindowContent(ToolWindow toolWindow) {
      contentPanel.setLayout(new BorderLayout(0, 20));
      contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
      contentPanel.add(createCalendarPanel(), BorderLayout.PAGE_START);
      contentPanel.add(createControlsPanel(toolWindow), BorderLayout.CENTER);
      updateCurrentDateTime();
    }

    @NotNull
    private JPanel createCalendarPanel() {
      JPanel calendarPanel = new JPanel();
      setIconLabel(currentDate, CALENDAR_ICON_PATH);
      setIconLabel(timeZone, TIME_ZONE_ICON_PATH);
      setIconLabel(currentTime, TIME_ICON_PATH);
      calendarPanel.add(currentDate);
      calendarPanel.add(timeZone);
      calendarPanel.add(currentTime);
      return calendarPanel;
    }

    private void setIconLabel(JLabel label, String imagePath) {
      label.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))));
    }

    @NotNull
    private JPanel createControlsPanel(ToolWindow toolWindow) {
      JPanel controlsPanel = new JPanel();
      JButton refreshDateAndTimeButton = new JButton("Refresh");
      refreshDateAndTimeButton.addActionListener(e -> updateCurrentDateTime());
      controlsPanel.add(refreshDateAndTimeButton);
      JButton hideToolWindowButton = new JButton("Hide");
      hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));
      controlsPanel.add(hideToolWindowButton);
      return controlsPanel;
    }

    private void updateCurrentDateTime() {
      Calendar calendar = Calendar.getInstance();
      currentDate.setText(getCurrentDate(calendar));
      timeZone.setText(getTimeZone(calendar));
      currentTime.setText(getCurrentTime(calendar));
    }

    private String getCurrentDate(Calendar calendar) {
      return calendar.get(Calendar.DAY_OF_MONTH) + "/"
          + (calendar.get(Calendar.MONTH) + 1) + "/"
          + calendar.get(Calendar.YEAR);
    }

    private String getTimeZone(Calendar calendar) {
      long gmtOffset = calendar.get(Calendar.ZONE_OFFSET); // offset from GMT in milliseconds
      String gmtOffsetString = String.valueOf(gmtOffset / 3600000);
      return (gmtOffset > 0) ? "GMT + " + gmtOffsetString : "GMT - " + gmtOffsetString;
    }

    private String getCurrentTime(Calendar calendar) {
      return getFormattedValue(calendar, Calendar.HOUR_OF_DAY) + ":" + getFormattedValue(calendar, Calendar.MINUTE);
    }

    private String getFormattedValue(Calendar calendar, int calendarField) {
      int value = calendar.get(calendarField);
      return StringUtils.leftPad(Integer.toString(value), 2, "0");
    }

    public JPanel getContentPanel() {
      return contentPanel;
    }

  }

}
