package org.wcscda.worms.board;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.HashSet;

public abstract class AbstractMovable extends AbstractBoardElement {
  // Speed is in pixel by clock iteration
  private double speed = 0.0;
  // In radian
  private double direction = 0.0;

  private static final HashSet<AbstractMovable> allMovables = new HashSet<AbstractMovable>();
  private static final HashSet<AbstractMovable> toBeRemoved = new HashSet<AbstractMovable>();

  public static HashSet<AbstractMovable> getAllMovable() {
    return allMovables;
  }

  public static HashSet<AbstractMovable> getToBeRemoved() {
    return toBeRemoved;
  }

  public static void removeAllToBeRemoved() {
    allMovables.removeAll(toBeRemoved);
    toBeRemoved.clear();
  }

  public AbstractMovable() {
    allMovables.add(this);
  }

  public double getSpeed() {
    return speed;
  }

  public double getSpeedX() {
    return speed * Math.cos(direction);
  }

  public double getSpeedY() {
    return speed * Math.sin(direction);
  }

  public void addSpeedXY(double speedXdelta, double speedYdelta) {
    double newSpeedX = speedXdelta + getSpeedX();
    double newSpeedY = speedYdelta + getSpeedY();

    setSpeedXY(newSpeedX, newSpeedY);
  }

  public void setSpeedXY(double speedX, double speedY) {
    double newSpeed = Math.sqrt(Math.pow(speedX, 2) + Math.pow(speedY, 2));

    if (newSpeed < 0.5) {
      setSpeed(0);
      return;
    }

    setSpeed(newSpeed);

    if (speedY >= 0) {
      direction = Math.acos(speedX / getSpeed());
    } else {
      direction = -Math.acos(speedX / getSpeed());
    }
  }

  public void setSpeedY(double speedY) {
    setSpeedXY(getSpeedX(), speedY);
  }

  public boolean isMoving() {
    return getSpeed() > 1e-6;
  }

  public double getDirection() {
    return direction;
  }

  public void setDirection(double direction) {
    this.direction = direction;
  }

  public void removeSelf() {
    getToBeRemoved().add(this);
  }

  // This one is public
  public abstract void rawMove(double x, double y);

  public void singleMove(IMovableVisitor visitor, double x, double y) {
    Point2D currentPosition = getCurrentPosition();
    rawMove(x, y);
    accept(currentPosition, visitor);
  }

  public void move(IMovableVisitor visitor) {
    singleMove(visitor, getSpeedX(), getSpeedY());
  }

  public Point2D getCurrentPosition() {
    return new Point2D.Double(getX(), getY());
  }

  public abstract int getX();

  public abstract int getY();

  public void setPosition(Point2D position) {
    rawMove(position.getX() - getX(), position.getY() - getY());
  }

  public void accept(Point2D prevPosition, IMovableVisitor visitor) {
    visitor.visit(this, prevPosition);
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public boolean isSubjectToGravity() {
    return false;
  }

  public abstract boolean isColidingWith(Shape s);

  public abstract void colideWith(AbstractBoardElement movable, Point2D prevPosition);

  public boolean isColidingWith(AbstractBoardElement abe) {
    return isColidingWith(abe.getShape());
  }

  // By default do nothing,might be overloaded
  public void takeDamage(int explosionDamage) {}
}
