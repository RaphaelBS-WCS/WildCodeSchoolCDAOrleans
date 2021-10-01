package org.wcscda.worms;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import org.wcscda.worms.board.ARBEWithGravity;
import org.wcscda.worms.board.AbstractBoardElement;
import org.wcscda.worms.board.IMovableVisitor;
import org.wcscda.worms.board.IVisitable;
import org.wcscda.worms.gamemechanism.Board;

public class Worm extends ARBEWithGravity implements IVisitable {
  private static final String leftFacingResource = "src/resources/WormLF.png";
  private static final String rightFacingResource = "src/resources/WormRF.png";

  private static final int imageHeight = 60;
  private static final int imageWidth = 54;
  private static final int rectPadding = 15;

  private static Image wormLF = null;
  private static Image wormRF = null;
  private int shownLife = 100;
  private int life = 100;
  private final String name;
  private final Player player;
  private boolean isUserMoving;

  private static void initImages() {
    wormLF =
        new ImageIcon(leftFacingResource).getImage().getScaledInstance(imageWidth, imageHeight, 0);
    wormRF =
        new ImageIcon(rightFacingResource).getImage().getScaledInstance(imageWidth, imageHeight, 0);
  }

  // NRO 2021-09-28 : Player is the Worm factory
  protected Worm(Player player, String name) {
    this(player, name, getRandomStartingX(), getRandomStartingY());
  }

  // Idem
  protected Worm(Player player, String name, int x, int y) {
    super(x, y, imageWidth - 2 * rectPadding, imageHeight - 2 * rectPadding);

    this.player = player;
    this.name = name;
  }

  private static int getRandomStartingX() {
    return RandomGenerator.getInstance().nextInt(Board.getBWIDTH() - imageWidth);
  }

  private static int getRandomStartingY() {
    return RandomGenerator.getInstance().nextInt(Board.getBHEIGHT() - imageHeight);
  }

  @Override
  protected void drawMain(Graphics2D g, ImageObserver io) {
    if (wormLF == null) initImages();
    Image worm = isRightFacing() ? wormRF : wormLF;

    g.drawImage(worm, getX() - rectPadding, getY() - rectPadding, io);

    g.setColor(player.getColor());
    g.drawString("" + getShownLife(), (int) getX(), (int) getY() - 15);
    //Drawing the worm name
    g.drawString("" + this.getName(), (int) getX() + 5, (int) getY() - 35);
    // Drawing the life
    g.drawString("" + life, (int) getX() + 10, (int) getY() - 20);

    g.drawString(Helper.getActivePlayer().getName(), 55, 55);
  }

  private int getShownLife() {

    if (life < shownLife) {
      shownLife--;
    } else if (life > shownLife) {
      shownLife++;
    }

    return this.shownLife;

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
  public void collideWith(AbstractBoardElement movable, Point2D prevPosition) {
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

    Helper.getActivePlayer();
    if (Player.isDebutant()) {
      life -= damage * 1.25;
    } else {
      life -= damage;
    }

    if (life <= 0) {
      Player.isPlayerDie();
      die();
    }
  }

  public void die() {
    removeSelf();
  }

  public int getLife() {
    return life;
  }

  @Override
  protected void onRemoval() {
    player.getWorms().remove(this);
  }

  @Override
  public void accept(Point2D prevPosition, IMovableVisitor visitor) {
    visitor.visit(this, prevPosition);
  }
}
