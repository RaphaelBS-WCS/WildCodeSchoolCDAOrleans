package org.wcs_cda.worms.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.wcs_cda.worms.Player;

public class Worm extends AbstractDrawableElement {
	private static final String leftFacingResource = "src/resources/WormLF.png";
	private static final String rightFacingResource = "src/resources/WormRF.png";
	
	private static final int imageWidth = 70;
	private static final int imageHeigth = 60;
	
	private static final List<Worm> allWorms = new ArrayList<Worm>();
	
	private static Image wormLF = null;
	private static Image wormRF = null;
	
	private boolean isRightFacing = true;
	
	// The position of the worm
	private final Player player;
	private final String name;
	
	private int x = 100;
	private int y = 100;
	private int life = 100;
	private boolean isMoving = false;	
	
	private static void initImages() {
		wormLF = new ImageIcon(leftFacingResource).getImage().getScaledInstance(imageWidth, imageHeigth, 0);
		wormRF = new ImageIcon(rightFacingResource).getImage().getScaledInstance(imageWidth, imageHeigth, 0);
	}
	
	public Worm(Player player, String name) {
		this.getAllWorms().add(this);
		this.player = player;
		this.name = name;
	}
	
	@Override
	protected void drawMain(Graphics g, ImageObserver io) {
		if( wormLF == null ) initImages();
		Image worm = isRightFacing ? wormRF : wormLF;
		
		g.drawImage(worm, x, y, io);
		// Drawing the life
		g.setColor(player.getColor());
		g.drawString("" + life, x + (isRightFacing ? 30 : 10), y - 20);
		if(!isMoving) {
			drawWeapon(g, io);
		}
		isMoving = false;
	}
	
	private void drawWeapon(Graphics g, ImageObserver io) {
		player.getCurrentWeapon().draw(g, io, x, y);
	}

	@Override
	protected void drawDebug(Graphics g, ImageObserver io) {
		g.setColor(Color.red);
		g.drawRect(x, y, imageWidth, imageHeigth);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle2D getRect() {
		Rectangle2D rect = new Rectangle2D.Double();
		rect.setRect(x + imageWidth/4, y + imageHeigth/4, imageWidth/2, imageHeigth/2);
		
		return rect;
	}
	
	public void move(String direction) {
		if(direction.equals("Left")) {
			isRightFacing = false;
			this.x -= 1;
		}
		else if (direction.equals("Right")) {
			isRightFacing = true; 
			this.x += 1;
		}
		isMoving = true;
	}
	
	public Rectangle2D getOuterRect() {
		Rectangle2D rect = new Rectangle2D.Double();
		rect.setRect(x + imageWidth/4 - 5, y + imageHeigth/4 - 5, imageWidth/2 + 10, imageHeigth/2 + 10);
		
		return rect;
	}

	public Player getPlayer() {
		return player;
	}

	public static List<Worm> getAllWorms() {
		return allWorms;
	}
}
