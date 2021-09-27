package org.wcscda.worms.gamemechanism.phases;

import org.wcscda.worms.Player;

public class MovingPhase extends AbstractPhase {
  public MovingPhase(Player activePlayer) {
    super(activePlayer);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected int getMaxDurationSeconds() {
    return 15;
  }

  @Override
  public void forwardKeyPressed(String key) {}
}
