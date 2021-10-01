package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class GrenadeAmmo extends AbstractAmmo{

    private static final int HADOKEN_AMMO_RADIUS = 15;
    private static final int HADOKEN_RECT_SIZE = 10;
    private static final int EXPLOSION_RADIUS = 100;
    private static final int EXPLOSION_DAMAGE = 30;
    private static final  String imagePath = "src/resources/weapons/grenade0.png";
    private static Image image = null;

    private final double initialX;
    private final double initialY;

    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 30, 0);
    }

    public GrenadeAmmo(Double angle) {
        super(HADOKEN_RECT_SIZE, HADOKEN_RECT_SIZE, EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
        setDirection(angle);
        setSpeed(3);

        initialX = Helper.getWormX();
        initialY = Helper.getWormY();
    }

    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {

        if (image == null) {
            initImages();
        }

        //g.setColor(Color.BLUE);
        //g.setStroke(new BasicStroke(0));

        g.drawImage(image, (int) initialX, (int) initialY, (int) getCenterX(), (int) getCenterY(), io);
        //g.drawLine((int) initialX, (int) initialY, (int) getCenterX(), (int) getCenterY());
    }
}
