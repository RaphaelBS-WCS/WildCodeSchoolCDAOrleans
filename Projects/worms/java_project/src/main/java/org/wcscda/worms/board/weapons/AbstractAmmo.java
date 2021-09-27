package org.wcscda.worms.board.weapons;

import java.awt.geom.Point2D;

import org.wcscda.worms.board.AbstractBoardElement;
import org.wcscda.worms.board.AbstractRectangularBoardElement;
import org.wcscda.worms.board.Worm;
import org.wcscda.worms.gamemechanism.TimeController;

public abstract class AbstractAmmo extends AbstractRectangularBoardElement {
  private static final int FIRING_WORM_ANTICOLLISION = 20;

  private final int firedPhase;
  private final Worm firingWorm;

  public AbstractAmmo(Worm worm, double x, double y, int rectWidth, int rectHeight) {
    super(x, y, rectWidth, rectHeight);
    firedPhase = TimeController.getInstance().getPhaseCount();
    this.firingWorm = worm;
  }

  protected int getFiredPhase() {
    return firedPhase;
  }

  @Override
  public void colideWith(AbstractBoardElement movable, Point2D prevPosition) {
    if ((movable == firingWorm)
        && TimeController.getInstance().getPhaseCount() <= firedPhase + FIRING_WORM_ANTICOLLISION) {
      return;
    }

    // System.out.println("Ammo colided with " + movable);

    removeSelf();
    explode();

    TimeController.getInstance().setNextWorm();
  }

  protected abstract void explode();
}
