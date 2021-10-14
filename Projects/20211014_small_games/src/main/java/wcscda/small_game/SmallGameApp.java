package wcscda.small_game;

import javax.swing.*;
import java.awt.*;

/** Hello world! */
public class SmallGameApp extends JFrame {
  public static void main(String[] args) {
    EventQueue.invokeLater(
            () -> {
              SmallGameApp sga = new SmallGameApp();
              sga.setVisible(true);
            });
  }

  private Board board;

  public SmallGameApp() {
    board = new Board(new Morpion());
    add(board);
    setResizable(false);
    pack();
    setTitle("WCS-CDA - Small game");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public Board getBoard() {
    return board;
  }
}
