package org.wcscda.worms.gamemechanism;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import org.wcscda.worms.board.ARBEWithGravity;
import org.wcscda.worms.board.AbstractMovable;
import org.wcscda.worms.board.IMovableVisitor;
import org.wcscda.worms.board.Worm;

public class PhysicalController extends Board implements IMovableVisitor {
  private static PhysicalController instance;

  public static PhysicalController getInstance() {
    return instance;
  }

  public PhysicalController() {
    super();
    instance = this;
  }

  public void wormInitialPlacement(Worm worm) {
    while (worm.isColidingWith(getWormField().getFrontier())) {
      worm.rawMove(0, -2);
    }

    while (!worm.isStandingOn(getWormField().getFrontier())) {
      worm.rawMove(0, 2);
    }
  }

  private boolean doGravity(ARBEWithGravity arbe) {
    for (int i = 0; i < 5; ++i) {
      if (arbe.isColidingWith(getWormField().getFrontier())) {
        arbe.rawMove(0, -2);
      } else {
        break;
      }
    }

    // Worms is still coliding, he must be standing against a wall
    // just revert its position
    if (arbe.isColidingWith(getWormField().getFrontier())) {
      return false;
    }

    for (int i = 0; i < 3; ++i) {
      if (!arbe.isStandingOn(getWormField().getFrontier())) {
        arbe.rawMove(0, 2);
      }
    }

    /* if(!arbe.isStandingOn(getWormField().getFrontier())) {
    	arbe.addSpeedXY(0, 2);
    }
    else {
    	arbe.setSpeed(0);
    }*/

    return true;
  }

  @Override
  public void visit(AbstractMovable ab, Point2D prevPosition) {
    if (ab.isColidingWith(getWormField().getShape())) {
      ab.colideWith(getWormField(), prevPosition);
      return;
    }

    for (AbstractMovable movable : AbstractMovable.getAllMovable()) {
      if (ab == movable) continue;

      if (ab.isColidingWith(movable)) {
        ab.colideWith(movable, prevPosition);
        return;
      }
    }
  }

  @Override
  public void visit(ARBEWithGravity arbewg, Point2D prevPosition) {
    // Do gravity first
    boolean moveIsPossibleWithGravity = doGravity(arbewg);
    if (!moveIsPossibleWithGravity) {
      arbewg.setPosition(prevPosition);
      return;
    }

    visit((AbstractMovable) arbewg, prevPosition);
  }

  @Override
  protected void doMoves() {
    for (AbstractMovable movable : AbstractMovable.getAllMovable()) {
      if (movable.getSpeed() < 0.5) {
        movable.setSpeed(0.0);
      }

      if (!movable.isMoving() && !movable.isSubjectToGravity()) {
        continue;
      }

      Point2D currentPosition = movable.getCurrentPosition();
      movable.move(this);
    }
  }

  public void generateExplosion(
      double centerX, double centerY, int explosionRadius, int explosionDamage) {
    Ellipse2D circle =
        new Ellipse2D.Double(
            centerX - explosionRadius,
            centerY - explosionRadius,
            2 * explosionRadius,
            2 * explosionRadius);
    getWormField().getFrontier().subtract(new Area(circle));

    for (AbstractMovable movable : AbstractMovable.getAllMovable()) {
      if (movable.isColidingWith(circle)) {
        movable.takeDamage(explosionDamage);
      }
    }
  }
}
