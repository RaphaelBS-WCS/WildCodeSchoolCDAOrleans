package org.wcscda.worms;

import java.awt.*;

import java.awt.geom.Ellipse2D;
import org.apache.commons.lang3.StringUtils;

public abstract class DrawHelper {
  public static Ellipse2D.Double getCircle(double centerX, double centerY, int radius) {
    return new Ellipse2D.Double(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
  }

  // Take values between 0 and 1 as input
  public static Color getColorRGB(double red, double green, double blue) {
    return getColorRGB((int) (255 * red), (int) (255 * green), (int) (255 * blue));
  }

  public static Color getColorRGB(int red, int green, int blue) {
    return Color.decode("#" + getColorHex(red) + getColorHex(green) + getColorHex(blue));
  }

  public static String getColorHex(Integer value) {
    assert (value <= 255) && (value >= 0);
    return StringUtils.leftPad(Integer.toHexString(value), 2, '0');
  }
}
