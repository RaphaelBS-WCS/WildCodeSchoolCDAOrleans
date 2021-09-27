package org.wcscda.worms.gamemechanism;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardController extends KeyAdapter {
  @Override
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    String keyAsString = KeyEvent.getKeyText(key);
    TimeController.getInstance().getCurrentPhase().forwardKeyPressed(keyAsString);
  }
}
