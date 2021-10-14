package wcscda.small_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class Board extends JPanel {
  private static final int WIDTH = 1200;
  private static final int HEIGHT = 800;
  private Drawable tempDrawable;
  private Set<Drawable> permanentDrawable = new HashSet<>();

  public Board(Drawable game) {
    permanentDrawable.add(game);

    addKeyListener(new KeyboardController(this));
    addMouseListener(new MouseController(this));
    setBackground(Color.BLACK);
    setFocusable(true);
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
  }

  public void redraw(Drawable drawable) {
    tempDrawable = drawable;
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    permanentDrawable.forEach(a -> a.draw(g2, this));

    if(tempDrawable != null) {
      tempDrawable.draw(g2, this);
      tempDrawable = null;
    }
  }

  public void drawMessage(String s) {
    Graphics2D g = (Graphics2D) getGraphics();

    g.setColor(Color.WHITE);
    g.drawString("You have pressed " + s, 200, 200);
  }
}
