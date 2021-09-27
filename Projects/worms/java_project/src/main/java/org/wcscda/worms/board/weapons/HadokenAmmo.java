package org.wcscda.worms.board.weapons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;

import org.wcscda.worms.board.Worm;
import org.wcscda.worms.gamemechanism.PhysicalController;

public class HadokenAmmo extends AbstractAmmo {
  private static final int HADOKEN_AMMO_RADIUS = 15;
  private static final int HADOKEN_RECT_SIZE = 10;
  private static final int EXPLOSION_RADIUS = 100;
  private static final int EXPLOSION_DAMAGE = 30;

  public HadokenAmmo(Worm firingWorm, double x, double y, double angle) {
    super(firingWorm, x, y, HADOKEN_RECT_SIZE, HADOKEN_RECT_SIZE);
    setDirection(angle);
    setSpeed(3);
  }

  @Override
  protected void drawMain(Graphics2D g, ImageObserver io) {
    Ellipse2D circle =
        new Ellipse2D.Double(
            getCenterX() - HADOKEN_AMMO_RADIUS,
            getCenterY() - HADOKEN_AMMO_RADIUS,
            2 * HADOKEN_AMMO_RADIUS,
            2 * HADOKEN_AMMO_RADIUS);

    g.setColor(Color.BLUE);
    g.setStroke(new BasicStroke(10));
    g.fill(circle);
  }

  @Override
  protected void explode() {
    PhysicalController.getInstance()
        .generateExplosion(getCenterX(), getCenterY(), EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
  }
}
