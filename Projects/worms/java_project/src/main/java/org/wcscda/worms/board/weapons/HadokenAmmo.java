package org.wcscda.worms.board.weapons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import org.wcscda.worms.Helper;

public class HadokenAmmo extends AbstractAmmo {
  private static final int HADOKEN_AMMO_RADIUS = 15;
  private static final int HADOKEN_RECT_SIZE = 10;
  private static final int EXPLOSION_RADIUS = 100;
  private static final int EXPLOSION_DAMAGE = 30;

  private final double initialX;
  private final double initialY;

  public HadokenAmmo(Double angle) {
    super(HADOKEN_RECT_SIZE, HADOKEN_RECT_SIZE, EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
    setDirection(angle);
    setSpeed(3);

    initialX = Helper.getWormX();
    initialY = Helper.getWormY();
  }

  @Override
  protected void drawMain(Graphics2D g, ImageObserver io) {
    g.setColor(Color.BLUE);
    g.setStroke(new BasicStroke(10));
    g.drawLine((int) initialX, (int) initialY, (int) getCenterX(), (int) getCenterY());
  }
}
