package org.wcscda.worms.gamemechanism;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.wcscda.worms.board.*;

public abstract class Board extends JPanel {

  /** */
  private static final long serialVersionUID = 1L;

  private static final int BOARD_WIDTH = 1200;
  private static final int BOARD_HEIGHT = 800;

  private WormField wormField;

  public Board() {
    initBoard();
  }

  private void initBoard() {
    setBackground(Color.BLACK);
    setFocusable(true);

    setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));

    wormField = new WormField(BOARD_WIDTH, BOARD_HEIGHT);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    doDrawing((Graphics2D) g);
  }

  private void doDrawing(Graphics2D g) {
    wormField.draw(g, this);

    for (AbstractMovable movable : AbstractMovable.getAllMovable()) {
      movable.draw(g, this);
    }

    TimeController.getInstance().getCurrentPhase().draw(g, this);

    Toolkit.getDefaultToolkit().sync();
  }

  public boolean actionPerformed(ActionEvent e) {
    repaint();
    doMoves();
    AbstractMovable.removeAllToBeRemoved();

    return true;
  }

  protected abstract void doMoves();

  public static int getB_WIDTH() {
    return BOARD_WIDTH;
  }

  public static int getB_HEIGHT() {
    return BOARD_HEIGHT;
  }

  protected WormField getWormField() {
    return this.wormField;
  }
}
