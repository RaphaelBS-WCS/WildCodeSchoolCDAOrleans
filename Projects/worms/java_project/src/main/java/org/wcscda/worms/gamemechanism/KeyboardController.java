package org.wcscda.worms.gamemechanism;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyboardController extends KeyAdapter {
  private static Map<Integer, String> localizationCorrectionMap;

  @Override
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    String keyAsString;

    // NRO 2021-09-27 : Not a very nice solution, but the awt documentation
    //   is very poor, so impossible to change the keyboard layout or
    //   locale.
    String correctedKey = getLocalizationCorrectionMap().get(key);
    if (correctedKey != null) {
      keyAsString = correctedKey;
    } else {
      keyAsString = KeyEvent.getKeyText(key);
    }

    TimeController.getInstance().getCurrentPhase().forwardKeyPressed(keyAsString);
  }

  private static Map<Integer, String> getLocalizationCorrectionMap() {
    if (localizationCorrectionMap == null) {
      localizationCorrectionMap = new HashMap<Integer, String>();
      localizationCorrectionMap.put(KeyEvent.VK_R, "Right");
      localizationCorrectionMap.put(KeyEvent.VK_L, "Left");
      localizationCorrectionMap.put(KeyEvent.VK_SPACE, "Space");
    }

    return localizationCorrectionMap;
  }
}
