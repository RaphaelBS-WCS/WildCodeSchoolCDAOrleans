package wcscda.small_game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.List;

public class KeyboardController extends KeyAdapter {
  private final Board board;

  public KeyboardController(Board board) {
    this.board = board;
  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
      board.redraw(new Drawable() {
        @Override
        public void draw(Graphics2D g, ImageObserver io) {
          g.drawString(
                  "You have pressed " + KeyEvent.getKeyText(keyEvent.getKeyCode()),
                          200,
                          200
                  );

        }
      });
    }
}
