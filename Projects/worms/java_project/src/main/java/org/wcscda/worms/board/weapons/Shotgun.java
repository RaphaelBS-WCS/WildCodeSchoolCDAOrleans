package org.wcscda.worms.board.weapons;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

import org.wcscda.worms.board.Worm;

public class Shotgun extends AbstractWeapon {
  private static final String imagePath = "src/resources/weapons/Shotgun_small.png";
  private static Image image = null;

  private static void initImages() {
    image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 30, 0);
  }

  @Override
  public void draw(Graphics2D g, ImageObserver io, double x, double y) {
    if (image == null) {
      initImages();
    }

    // AffineTransform trans = AffineTransform.getTranslateInstance(x + 100, y);
    // trans.scale(-1, 1);

    // g.drawImage(image, trans, io);
    g.drawImage(image, (int) x, (int) y, io);
  }

  @Override
  public void fire(Worm firingWorm, double x, double y) {}
}
