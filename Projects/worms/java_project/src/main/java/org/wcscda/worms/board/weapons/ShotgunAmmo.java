package org.wcscda.worms.board.weapons;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class ShotgunAmmo extends AbstractAmmo {
  private static final int EXPLOSION_RADIUS = 30;
  private static final int EXPLOSION_DAMAGE = 15;
  private static final int HADOKEN_RECT_SIZE = 10;

  public ShotgunAmmo(Double angle) {
    super(EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
    createMovableRect(HADOKEN_RECT_SIZE, HADOKEN_RECT_SIZE);
    getMovable().setDirection(angle);
    getMovable().setSpeed(5);
  }

  @Override
  public void drawMain(Graphics2D g, ImageObserver io) {
    // TODO Auto-generated method stub

  }
}
