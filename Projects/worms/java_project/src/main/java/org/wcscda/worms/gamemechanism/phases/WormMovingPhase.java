package org.wcscda.worms.gamemechanism.phases;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import org.wcscda.worms.Config;
import org.wcscda.worms.board.Worm;
import org.wcscda.worms.gamemechanism.PhysicalController;
import org.wcscda.worms.gamemechanism.TimeController;

public class WormMovingPhase extends AbstractPhase {
  private static final double WORM_STEP_SPEED = 3.0;
  private Worm activeWorm;

  public WormMovingPhase(Worm worm) {
    super(worm.getPlayer());
    this.activeWorm = worm;
  }

  @Override
  protected int getMaxDurationSeconds() {
    return Config.getMaxWormTurnDuration();
  }

  public Worm getActiveWorm() {
    return activeWorm;
  }

  @Override
  public void forwardKeyPressed(String key) {
    if (key.equals("Left")) {
      activeWorm.getPlayer().getCurrentWeapon().setAngle(Math.PI);
      activeWorm.setDirection(Math.PI);
      activeWorm.setUserMoving(true);
      activeWorm.singleMove(PhysicalController.getInstance(), -WORM_STEP_SPEED, 0.0);
    }

    if (key.equals("Right")) {
      activeWorm.getPlayer().getCurrentWeapon().setAngle(0);
      activeWorm.setDirection(0);
      activeWorm.setUserMoving(true);
      activeWorm.singleMove(PhysicalController.getInstance(), WORM_STEP_SPEED, 0.0);
    }

    if (key.equals("Space")) {
      activeWorm
          .getPlayer()
          .getCurrentWeapon()
          .fire(activeWorm, activeWorm.getCenterX(), activeWorm.getCenterY());

      TimeController.getInstance().setCurrentPhase(new MovingPhase(getActivePlayer()));
    }
  }

  @Override
  protected void drawMain(Graphics2D g, ImageObserver io) {
    if (!activeWorm.isUserMoving()) {
      double x = activeWorm.getCenterX();
      double y = activeWorm.getCenterY();

      activeWorm.getPlayer().getCurrentWeapon().draw(g, io, x, y);
    }
    activeWorm.setUserMoving(false);
  }
}
