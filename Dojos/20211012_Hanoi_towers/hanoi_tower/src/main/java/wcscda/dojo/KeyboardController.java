package wcscda.dojo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class KeyboardController extends KeyAdapter {
  private static final Character[] usedKeys = new Character[] {'1', '2', '3'};
  private static final List<Character> usedKeysList = List.of(usedKeys);

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    Character key = keyEvent.getKeyChar();
    int index = usedKeysList.indexOf(key);
    if (index != -1) {
      HanoiTower.getInstance().processKey(index);
    }
  }
}
