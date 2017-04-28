package org.eclipse.scout.healthcare.client.device;

import org.eclipse.scout.healthcare.client.Icons;

public final class DeviceFormattingUtility {

  private DeviceFormattingUtility() {

  }

  public static String getFillLevelIconId(Long fillLevel) {
    String icon = null;
    if (null != fillLevel) {
      if (fillLevel >= 0L && fillLevel < 13L) {
        icon = Icons.BatteryEmpty;
      }
      else if (fillLevel >= 13L && fillLevel < 38L) {
        icon = Icons.BatteryQuater;
      }
      else if (fillLevel >= 38L && fillLevel < 63L) {
        icon = Icons.BatteryHalf;
      }
      else if (fillLevel >= 63L && fillLevel < 88L) {
        icon = Icons.BatteryThreeQuater;
      }
      else if (fillLevel >= 88L) {
        icon = Icons.BatteryFull;
      }
    }
    return icon;
  }

  public static String getFillLevelForegroundColor(Long fillLevel) {
    String fontColor = null;
    if (null != fillLevel) {
      if (fillLevel >= 0L && fillLevel < 13L) {
        fontColor = "FF6060";
      }
      else if (fillLevel >= 13L && fillLevel < 38L) {
        fontColor = "FDAD4F";
      }
      else if (fillLevel >= 38L && fillLevel < 63L) {
        fontColor = "0DAF66";
      }
      else if (fillLevel >= 63L && fillLevel < 88L) {
        fontColor = "0DAF66";
      }
      else if (fillLevel >= 88L) {
        fontColor = "0DAF66";
      }
    }
    return fontColor;
  }

}
