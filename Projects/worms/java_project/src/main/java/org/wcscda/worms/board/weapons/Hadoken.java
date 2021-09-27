package org.wcscda.worms.board.weapons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
// import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;

import org.wcscda.worms.board.Worm;

public class Hadoken extends AbstractWeapon {
  private static final int hadokenRadius = 50;

  @Override
  public void draw(Graphics2D g, ImageObserver io, double x, double y) {
    Ellipse2D circle =
        new Ellipse2D.Double(
            x - hadokenRadius, y - hadokenRadius, 2 * hadokenRadius, 2 * hadokenRadius);

    g.setColor(Color.BLUE);
    g.setStroke(new BasicStroke(10));
    g.draw(circle);
  }

  @Override
  public void fire(Worm firingWorm, double x, double y) {
    new HadokenAmmo(firingWorm, x, y, getAngle());
  }
}
