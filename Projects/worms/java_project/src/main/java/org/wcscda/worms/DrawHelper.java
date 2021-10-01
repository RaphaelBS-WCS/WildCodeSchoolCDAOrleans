package org.wcscda.worms;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;
import org.wcscda.worms.board.weapons.Shotgun;

public abstract class DrawHelper {
  public static Ellipse2D.Double getCircle(double centerX, double centerY, int radius) {
    return new Ellipse2D.Double(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
  }
}
