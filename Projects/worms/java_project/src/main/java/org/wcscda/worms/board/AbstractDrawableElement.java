package org.wcscda.worms.board;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import org.wcscda.worms.Config;

// NRO 2021-09-27 : Drawable elements are present visually
//  but might not have physical presence (ie timer)
public abstract class AbstractDrawableElement {
  public final void draw(Graphics2D g, ImageObserver io) {
    drawMain(g, io);
    if (Config.isDebug()) {
      drawDebug(g, io);
    }
  }

  // By default, no debug print
  protected void drawDebug(Graphics2D g, ImageObserver io) {}

  protected abstract void drawMain(Graphics2D g, ImageObserver io);
}
