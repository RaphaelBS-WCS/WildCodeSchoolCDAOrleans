package org.wcscda.worms.board;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.image.ImageObserver;

public class WormField extends AbstractBoardElement {
  private int[][] allPixel;
  private Area frontier;

  public WormField() {}

  public WormField(int width, int height) {
    initRandomSpline(width, height);
  }

  private void initRandomSpline(int width, int height) {
    int nbSegments = 10;
    int nbSplines = 2 * nbSegments + 1;

    int[] randomSplineHeight = new int[nbSplines];

    for (int i = 0; i < nbSplines; ++i) {
      randomSplineHeight[i] = (int) ((0.25 + 0.5 * Math.random()) * height);
    }

    Path2D p = new Path2D.Double();

    p.moveTo(0, randomSplineHeight[0]);

    for (int i = 0; i < nbSegments; ++i) {

      p.quadTo(
          (double) width * (2 * i + 1) / 20,
          randomSplineHeight[2 * i + 1],
          (double) width * (2 * i + 2) / 20,
          randomSplineHeight[2 * i + 2]);
    }

    p.lineTo(width, height);
    p.lineTo(0, height);
    p.lineTo(0, randomSplineHeight[0]);
    p.closePath();

    frontier = new Area(p);
  }

  @Override
  public void drawMain(Graphics2D g, ImageObserver io) {
    g.setColor(Color.green);
    g.fill(frontier);
  }

  public void doExplosionOnField(int x, int y, int radius) {
    Ellipse2D explosion = new Ellipse2D.Double(x, y, radius, radius);

    frontier.subtract(new Area(explosion));
  }

  public Area getFrontier() {
    return frontier;
  }

  public Shape getShape() {
    return frontier;
  }
}
