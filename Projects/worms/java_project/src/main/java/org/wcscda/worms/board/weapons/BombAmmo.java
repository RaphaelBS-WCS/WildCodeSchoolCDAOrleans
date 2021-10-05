package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;
import org.wcscda.worms.board.ARBEWithGravityWithTiming;
import org.wcscda.worms.board.AbstractBoardElement;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;

public class BombAmmo extends AbstractAmmo{

    private static final int GRENADE_RECT_SIZE = 10;
    private static final int EXPLOSION_RADIUS = 100;
    private static final int EXPLOSION_DAMAGE = 30;
    private static final  String imagePath = "src/resources/weapons/timebomb.png";
    private static Image image = null;
    private int initTime = 0;

    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 30, 0);
    }

    public BombAmmo(Double angle) {
        super(EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
        createMovableRect(GRENADE_RECT_SIZE, GRENADE_RECT_SIZE);
        getMovable().setDirection(angle);
        getMovable().setSpeed(7);

        setInitialPosition();
    }

    @Override
    public void drawMain(Graphics2D g, ImageObserver io) {
        if (image == null) {
            initImages();
        }
        g.drawImage(image, (int) (getMovable().getCenterX() - 20), (int) (getMovable().getCenterY() -20), io);

        if (initTime + 100 < Helper.getClock()) {
            explode();

            Helper.getCurrentWeapon().triggerAmmoExplosion();
        }
    }

    // Override this method if you want to have another movement
    // behaviour
    @Override
    protected void createMovableRect(int rectWidth, int rectHeight) {
        setMovable(new ARBEWithGravityWithTiming(
                Helper.getWormX() - rectWidth / 2,
                Helper.getWormY() - rectHeight / 2,
                rectWidth,
                rectHeight,
                this));
        setInitTime(Helper.getClock());
    }

    public void setInitTime(int initTime) {
        this.initTime = initTime;
    }

    @Override
    public void colideWith(AbstractBoardElement movable, Point2D prevPosition) {
        getMovable().setPosition(prevPosition);
    }
}
