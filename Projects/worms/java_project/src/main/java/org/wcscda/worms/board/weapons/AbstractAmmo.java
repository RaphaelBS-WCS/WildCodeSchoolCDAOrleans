package org.wcscda.worms.board.weapons;

import java.awt.geom.Point2D;
import org.wcscda.worms.Helper;
import org.wcscda.worms.board.AbstractBoardElement;
import org.wcscda.worms.board.AbstractRectangularBoardElement;

public abstract class AbstractAmmo extends AbstractRectangularBoardElement {
  private static final int FIRING_WORM_ANTICOLLISION = 20;

  private final int firedPhase;
  private final int explosionRadius;
  private final int explosionDamage;

  public AbstractAmmo(int rectWidth, int rectHeight, int explosionRadius, int explosionDamage) {
    super(
        Helper.getWormX() - rectWidth / 2,
        Helper.getWormY() - rectHeight / 2,
        rectWidth,
        rectHeight);
    firedPhase = Helper.getClock();

    this.explosionDamage = explosionDamage;
    this.explosionRadius = explosionRadius;
  }

  protected int getFiredPhase() {
    return firedPhase;
  }

  @Override
  public void colideWith(AbstractBoardElement movable, Point2D prevPosition) {
    if ((movable == Helper.getActiveWorm())
        && Helper.getClock() <= firedPhase + FIRING_WORM_ANTICOLLISION) {
      return;
    }

    explode();

    Helper.getCurrentWeapon().triggerAmmoExplosion();
  }

  protected void explode() {
    removeSelf();
    Helper.getPC().generateExplosion(getCenterX(), getCenterY(), explosionRadius, explosionDamage);
  }
}
