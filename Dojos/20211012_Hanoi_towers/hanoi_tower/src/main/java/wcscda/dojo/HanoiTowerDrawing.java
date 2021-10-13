package wcscda.dojo;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Stack;
import javax.swing.*;

public class HanoiTowerDrawing extends JPanel {
  private static final int WIDTH = 900;
  private static final int HEIGHT = 800;
  private static final Color BROWN = new Color(150, 75, 0);

  private final int disksHeight;
  private final int disksIncrement;

  private Rectangle2D[] columns;

  public void setHanoiTower(HanoiTower hanoiTower) {
    this.hanoiTower = hanoiTower;
  }

  private HanoiTower hanoiTower;

  public HanoiTowerDrawing(int nbDisks) {
    disksHeight = 600 / nbDisks - 10;
    disksIncrement = (260 - 70) / nbDisks;
    
    addKeyListener(new KeyboardController());
    setBackground(Color.BLACK);
    setFocusable(true);
    setPreferredSize(new Dimension(WIDTH, HEIGHT));

    initColumns();
  }

  private void initColumns() {
    columns = new Rectangle2D[3];

    for(int i = 0; i < 3; ++i) {
      columns[i] = new Rectangle2D.Double(
              125 + 300 * i,
              100,
              50,
              600
      );
    }
  }

  public void drawMessage(String s) {
    Graphics2D g = (Graphics2D) getGraphics();

    g.setFont(new Font("Verdana", Font.BOLD,30));

    drawHanoiTower(g);

    g.setColor(Color.RED);
    g.drawString(s, 100, 70);

    Toolkit.getDefaultToolkit().sync();
  }

  public void drawHighlight(Integer index) {
    Graphics2D g = (Graphics2D) getGraphics();

    drawHanoiTower(g);
    g.setColor(Color.BLUE);
    g.drawRect(300 * index + 10, 10, 280, 780);
  }

  public void drawHanoiTower() {
    drawHanoiTower((Graphics2D) getGraphics());
  }

  public void drawHanoiTower(Graphics2D g) {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);

    g.setColor(BROWN);
    Arrays.stream(columns).forEach(g::fill);

    for(int i = 0; i < 3; ++i) {
      Stack<Integer> stack = hanoiTower.getStacks()[i];
      int centralX = (int)columns[i].getCenterX();
      int y = 700;

      for(Integer disk: stack) {
        y = drawDisk(g, centralX, y, disk);
      };
    }
  }

  private int drawDisk(Graphics2D g, int centralX, int y, Integer disk) {
    int halfWidth = (70 + disksIncrement * disk) / 2;
    g.setColor(Color.DARK_GRAY);
    g.fillRect(
            centralX - halfWidth,
            y - disksHeight,
            2 * halfWidth,
            disksHeight);
    y -= disksHeight + 10;

    return y;
  }
}
