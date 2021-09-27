package org.wcscda.worms.board;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

import org.wcscda.worms.Player;
import org.wcscda.worms.RandomGenerator;
import org.wcscda.worms.gamemechanism.Board;

public class Worm extends ARBEWithGravity {
  private static final String leftFacingResource = "src/resources/WormLF.png";
  private static final String rightFacingResource = "src/resources/WormRF.png";

  private static final int imageWidth = 54;
  private static final int imageHeight = 60;
  private static final int rectPadding = 15;

  private static final int outerMargin = 3;

  private static Image wormLF = null;
  private static Image wormRF = null;

  // The position of the worm
  private final Player player;
  private final String name;

  private int life = 100;

  private boolean isUserMoving;

  private static void initImages() {
    wormLF =
        new ImageIcon(leftFacingResource).getImage().getScaledInstance(imageWidth, imageHeight, 0);
    wormRF =
        new ImageIcon(rightFacingResource).getImage().getScaledInstance(imageWidth, imageHeight, 0);
  }

  public Worm(Player player, String name) {
    this(player, name, getRandomStartingX(), getRandomStartingY());
  }

  public Worm(Player player, String name, int x, int y) {
    super(x, y, imageWidth - 2 * rectPadding, imageHeight - 2 * rectPadding);

    this.player = player;
    this.name = name;
  }

  private static int getRandomStartingX() {
    return RandomGenerator.getInstance().nextInt(Board.getB_WIDTH() - imageWidth);
  }

  private static int getRandomStartingY() {
    return RandomGenerator.getInstance().nextInt(Board.getB_HEIGHT() - imageHeight);
  }

  @Override
  protected void drawMain(Graphics2D g, ImageObserver io) {
    if (wormLF == null) initImages();
    Image worm = isRightFacing() ? wormRF : wormLF;

    g.drawImage(worm, getX() - rectPadding, getY() - rectPadding, io);

    // Drawing the life
    g.setColor(player.getColor());
    g.drawString("" + life, (int) getX(), (int) getY() - 15);
  }

  private boolean isRightFacing() {
    return Math.abs(getDirection()) < 1e-6;
  }

  public Player getPlayer() {
    return player;
  }

  public boolean isUserMoving() {
    return isUserMoving;
  }

  public void setUserMoving(boolean isUserMoving) {
    this.isUserMoving = isUserMoving;
  }

  @Override
  public void colideWith(AbstractBoardElement movable, Point2D prevPosition) {
    setPosition(prevPosition);
  }

  @Override
  public String toString() {
    return "Worm " + this.getName() + " / player : " + this.getPlayer();
  }

  public String getName() {
    return name;
  }

  @Override
  public void takeDamage(int damage) {
    life -= damage;
    if (life <= 0) {
      die();
    }
  }

  public void die() {
    player.getWorms().remove(this);
    removeSelf();
  }
}
