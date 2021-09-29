package org.wcscda.worms.board.weapons;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class ShotgunAmmo extends AbstractAmmo {
  private static final int EXPLOSION_RADIUS = 30;
  private static final int EXPLOSION_DAMAGE = 15;
  private static final int HADOKEN_RECT_SIZE = 10;

  public ShotgunAmmo(Double angle) {
    super(HADOKEN_RECT_SIZE, HADOKEN_RECT_SIZE, EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
    setDirection(angle);
    setSpeed(5);
  }

  @Override
  protected void drawMain(Graphics2D g, ImageObserver io) {
    // TODO Auto-generated method stub

  }
}
