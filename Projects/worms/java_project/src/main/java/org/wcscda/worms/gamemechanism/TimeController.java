package org.wcscda.worms.gamemechanism;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;
import org.wcscda.worms.Config;
import org.wcscda.worms.Player;
import org.wcscda.worms.Worm;
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
    instance = this;
    initGame();

    board.addKeyListener(new KeyboardController());

    timer = new Timer(Config.getClockDelay(), this);
    timer.start();
  }

  private void initGame() {
    board = new PhysicalController();
    // Lucky luke because for the moment he is a poor lonesome
    // player
    //int nbrPlayer = 3;
    //int nbrWorms = 3;
    ArrayList<Color> colors = new ArrayList<>();
    colors.add(Color.RED);
    colors.add(Color.ORANGE);
    colors.add(Color.BLUE);
    colors.add(Color.MAGENTA);
    colors.add(Color.PINK);
    colors.add(Color.CYAN);
    colors.add(Color.GREEN);
    colors.add(Color.YELLOW);
    Collections.shuffle(colors);

    Map<String, String[]> teams = new HashMap<>();

    Scanner scan = new Scanner(System.in);
    System.out.println("Veuillez saisir le nombre de joueur : ");
    int nbrPlayer = scan.nextInt();
    System.out.println("Veuillez saisir le nombre de worms : ");
    int nbrWorms = scan.nextInt();

    for (int iP = 0; iP < nbrPlayer; ++iP) {
      System.out.println("Veuillez saisir le nom du joueur " + (iP +1) + ": ");
      String playerName = scan.next();
      teams.put(playerName, new String[nbrWorms]);
      for (int iW = 0; iW < nbrWorms; ++iW) {
        System.out.println("Veuillez saisir le nom du worm " + (iW +1) + ": ");
        teams.get(playerName)[iW] = scan.next();
      }
    }

    int i = 0;
    for (String name : teams.keySet()) {
        Player player = createPlayer(name, colors.get(i));
        i++;
        for (String toto : teams.get(name)) {
          Worm worm = player.createWorm(toto);
          board.wormInitialPlacement(worm);
          setNextWorm();
        }
    }
  }

  public void setNextWorm() {
    activePlayerIndex += 1;
    activePlayerIndex %= players.size();
    getActivePlayer().setNextWorm();
    getActivePlayer().initWeapon();

    AbstractPhase phase = new WormMovingPhase();
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
    if ((this.currentPhase != null) && this.currentPhase != currentPhase) {
      this.currentPhase.removeSelf();
    }
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
