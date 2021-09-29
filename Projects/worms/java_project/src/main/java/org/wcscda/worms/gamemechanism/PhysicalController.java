package org.wcscda.worms.gamemechanism;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Optional;
import org.wcscda.worms.Worm;
import org.wcscda.worms.board.ARBEWithGravity;
import org.wcscda.worms.board.AbstractMovable;
import org.wcscda.worms.board.IMovableVisitor;

/** @author nicolas */
public class PhysicalController extends Board implements IMovableVisitor {

  private static final long serialVersionUID = 1L;
  private static final int MAX_PIXEL_DIFF_SLOPE = 10;
  private static final int SLOPE_STEP = 2;

  private static PhysicalController instance;

  public static PhysicalController getInstance() {
    return instance;
  }

  public PhysicalController() {
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
    for (int i = 0; i * SLOPE_STEP < MAX_PIXEL_DIFF_SLOPE; ++i) {
      if (arbe.isColidingWith(getWormField().getFrontier())) {
        arbe.rawMove(0, -SLOPE_STEP);
      } else {
        break;
      }
    }

    // Worms is still coliding, he must be standing against a wall
    // just revert its position
    if (arbe.isColidingWith(getWormField().getFrontier())) {
      return false;
    }

    for (int i = 0; i * SLOPE_STEP < MAX_PIXEL_DIFF_SLOPE; ++i) {
      if (!arbe.isStandingOn(getWormField().getFrontier())) {
        arbe.rawMove(0, SLOPE_STEP);
      }
    }

    return true;
  }

  @Override
  public void visit(AbstractMovable ab, Point2D prevPosition) {
    if (ab.isColidingWith(getWormField().getShape())) {
      ab.colideWith(getWormField(), prevPosition);
      return;
    }

    // NRO 2021-09-28 : For information this is a little bit of
    //  algorithmic addict overkill
    // You don't need to understand that for the moment.
    // I am looking for the first object that colide with ab
    Optional<AbstractMovable> oam =
        AbstractMovable.getAllMovable().filter(movable -> ab.isColidingWith(movable)).findFirst();

    if (oam.isPresent()) {
      ab.colideWith(oam.get(), prevPosition);
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
    AbstractMovable.getAllMovable()
        .forEach(
            movable -> {
              if (movable.getSpeed() < 0.5) {
                movable.setSpeed(0.0);
              }

              if (!movable.isMoving() && !movable.isSubjectToGravity()) {
                return;
              }

              movable.move(this);
            });
  }

  public void generateExplosion(
      double centerX, double centerY, int explosionRadius, int explosionDamage) {
    Ellipse2D circle =
        new Ellipse2D.Double(
            centerX - explosionRadius,
            centerY - explosionRadius,
            2 * explosionRadius,
            2 * explosionRadius);
    getWormField().doExplosionOnField(circle);

    AbstractMovable.getAllMovable()
        .forEach(
            movable -> {
              if (movable.isColidingWith(circle)) {
                movable.takeDamage(explosionDamage);
              }
            });
  }
}
