package org.wcscda.worms.gamemechanism;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;
import org.wcscda.worms.Config;
import org.wcscda.worms.Helper;
import org.wcscda.worms.Player;
import org.wcscda.worms.Worm;
import org.wcscda.worms.gamemechanism.phases.AbstractPhase;
import org.wcscda.worms.gamemechanism.phases.WormMovingPhase;
import org.wcscda.worms.gamemechanism.playerrecorder.KeyboardControllerPlayer;
import org.wcscda.worms.gamemechanism.playerrecorder.KeyboardControllerRecorder;

public class TimeController implements ActionListener {
  private static TimeController instance;

  public KeyboardController getKeyboardController() {
    return keyboardController;
  }

  private final KeyboardController keyboardController;
  private PhysicalController board;
  private Timer timer;
  private ArrayList<Player> players = new ArrayList<Player>();
  private int activePlayerIndex = 0;
  private AbstractPhase currentPhase;
  private int phaseCount = 0;
  private int currentNbPlayer = 0;
  private boolean debutant = false;
  public static Map<String, String[]> getTeams() {
    return teams;
  }
  private static Map<String, String[]> teams = new HashMap<>();
  private boolean delayedSetNextWorm;
  private ScriptPlayer scriptPlayer;
  private static Map<String, Boolean> warmsInvetory = new HashMap<>();
  private static Map<Object, Object> mainArray = new HashMap<>();

  public ScriptPlayer getScriptPlayer() {
    return scriptPlayer;
  }

  public TimeController() {
    instance = this;
    initGame();
    keyboardController = createController();
    board.addKeyListener(keyboardController);

    timer = new Timer(Config.getClockDelay(), this);
    timer.start();
  }

  private KeyboardController createController() {
    if (Config.getRecordGame()) {
      return new KeyboardControllerRecorder(this.board);
    } else if (Config.getPlayRecord()) {
      return new KeyboardControllerPlayer();
    } else {
      return new KeyboardController();
    }
  }

  private void initGame() {
    board = new PhysicalController();

    warmsInvetory.put("Grenade", true);

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

    Scanner scan = new Scanner(System.in);
    System.out.println("Veuillez saisir le nombre de joueur : ");
    int nbrPlayer = scan.nextInt();
    System.out.println("Veuillez saisir le nombre de worms par joueur : ");
    int nbrWorms = scan.nextInt();

    for (int iP = 0; iP < nbrPlayer; ++iP) {
      System.out.println("Veuillez saisir le nom du joueur " + (iP +1) + ": ");
      String playerName = scan.next();
      teams.put(playerName, new String[nbrWorms]);
      System.out.println((iP +1) + " est il debutant ? (y/n) : ");
      String isDebutant = scan.next();
      if (Objects.equals(isDebutant, "y")) {
        debutant = true;
      }
      for (int iW = 0; iW < nbrWorms; ++iW) {
        System.out.println("Veuillez saisir le nom du worm " + (iW +1) + ": ");
        teams.get(playerName)[iW] = scan.next();
        //private static Map<String, String[]> teams2 = new HashMap<>();
      }
    }
      for (Object team : teams.entrySet()) {
        System.out.println(team);
        mainArray.put(team, warmsInvetory);
      }
      //mainArray.put(teams, warmsInvetory);



    int i = 0;
    for (String playerName : teams.keySet()) {
        Player player = createPlayer(playerName, colors.get(i), debutant);
        i++;
        for (String wormName : teams.get(playerName)) {
          Worm worm = player.createWorm(wormName);
          board.wormInitialPlacement(worm);
          setNextWorm();
        }
    }

    setCurrentNbPlayer(Helper.getTC().getPlayers().size());
    /*
    if(Config.getScriptFilename() != null) {
      scriptPlayer = new ScriptPlayer(Config.getScriptFilename());
    }
    */

    doSetNextWorm();

  }

  public void setNextWorm() {
    delayedSetNextWorm = true;
  }

  protected void delayedActions() {
    if (delayedSetNextWorm) {
      delayedSetNextWorm = false;
      doSetNextWorm();
    }
  }

  protected void doSetNextWorm() {
    for (int i = 0; i < players.size(); ++i) {
      activePlayerIndex += 1;
      activePlayerIndex %= players.size();
      if (getActivePlayer().hasWorms()) break;
    }

    // No player have any worm, it is sad ...
    if (!getActivePlayer().hasWorms()) {
      return;
    }

    getActivePlayer().setNextWorm();
    getActivePlayer().initWeapon();

    AbstractPhase phase = new WormMovingPhase();
    this.setCurrentPhase(phase);
  }

  private Player createPlayer(String name, Color color, Boolean debutant) {
    Player player = new Player(name, color, debutant);
    players.add(player);

    return player;
  }

  public Component getBoard() {
    return board;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    phaseCount++;
    board.actionPerformed(e);
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

  public int getCurrentNbPlayer() {
    return currentNbPlayer;
  }

  public void setCurrentNbPlayer(int currentNbPlayer) {
    this.currentNbPlayer = currentNbPlayer;
  }
}
