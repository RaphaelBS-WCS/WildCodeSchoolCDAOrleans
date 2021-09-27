package org.wcscda.worms.gamemechanism;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;

import org.wcscda.worms.Config;
import org.wcscda.worms.Player;
import org.wcscda.worms.board.Worm;
import org.wcscda.worms.gamemechanism.phases.AbstractPhase;
import org.wcscda.worms.gamemechanism.phases.WormMovingPhase;

public class TimeController implements ActionListener {
  private static TimeController instance;
  private PhysicalController board;
  private Timer timer;
  private ArrayList<Player> players = new ArrayList<Player>();
  private int activePlayerIndex = 0;
  private AbstractPhase currentPhase;
  private int phaseCount = 0;

  public TimeController() {
    initGame();

    board.addKeyListener(new KeyboardController());

    timer = new Timer(Config.getClockDelay(), this);
    timer.start();
  }

  private void initGame() {
    board = new PhysicalController();
    // Lucky luke because for the moment he is a poor lonesome
    // player
    Player luckyLuke = createPlayer("Lucky Luke", Color.RED);

    for (String name : new String[] {"Joly jumper", "rantanplan"}) {
      Worm worm = luckyLuke.createWorm(name);
      board.wormInitialPlacement(worm);
    }

    setNextWorm();
  }

  public void setNextWorm() {
    activePlayerIndex += 1;
    activePlayerIndex %= players.size();

    AbstractPhase phase = new WormMovingPhase(getActivePlayer().getNextWorm());
    this.setCurrentPhase(phase);
  }

  private Player createPlayer(String name, Color color) {
    Player player = new Player(name, color);
    players.add(player);

    return player;
  }

  public Component getBoard() {
    return board;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    phaseCount++;
    boolean inGame = board.actionPerformed(e);

    if (!inGame) {
      timer.stop();
    }
  }

  public static TimeController getInstance() {
    if (instance == null) {
      instance = new TimeController();
    }
    return instance;
  }

  public AbstractPhase getCurrentPhase() {
    return currentPhase;
  }

  public void setCurrentPhase(AbstractPhase currentPhase) {
    this.currentPhase = currentPhase;
  }

  public ArrayList<Player> getPlayers() {
    return players;
  }

  public int getPhaseCount() {
    return phaseCount;
  }

  public void setPhaseCount(int phaseCount) {
    this.phaseCount = phaseCount;
  }

  public Player getActivePlayer() {
    return players.get(activePlayerIndex);
  }
}
