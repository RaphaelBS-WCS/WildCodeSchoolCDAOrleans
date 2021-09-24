package org.wcs_cda.worms.board;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import org.wcs_cda.worms.Config;

public abstract class AbstractDrawableElement {
	private int depth;

	public int getDepth() {
		return depth;
	}

	protected void setDepth(int depth) {
		this.depth = depth;
	}
	
	public void draw(Graphics g, ImageObserver io) {
		drawMain(g, io);
		if(Config.isDebug()) {
			drawDebug(g, io);
		}
	}

	// By default, no debug print
	protected void drawDebug(Graphics g, ImageObserver io) {}

	protected abstract void drawMain(Graphics g, ImageObserver io);
	
	
}
