package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;


public class Bomb extends AbstractWeapon {

    private static final  String imagePath = "src/resources/weapons/timebomb.png";
    private static Image image = null;
    private static String name = "Bomb";
    public  String getName() {
        return name;
    }



    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 50, 0);
    }

    public void draw(Graphics2D g, ImageObserver io) {
        if (image == null) {
            initImages();
        }
        if (getAngle() > Math.PI / 2) {
            AffineTransform trans =
                    AffineTransform.getTranslateInstance(Helper.getWormX() - 40, Helper.getWormY());
            trans.scale(1, 1);

            g.drawImage(image, trans, io);
        } else {
            AffineTransform trans = AffineTransform.getTranslateInstance(Helper.getWormX() + 40, Helper.getWormY());
            trans.scale(-1, 1);

            g.drawImage(image, trans, io);
        }
    }
}
