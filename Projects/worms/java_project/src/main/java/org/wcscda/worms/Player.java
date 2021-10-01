package org.wcscda.worms;

import java.awt.Color;
import java.util.ArrayList;

import org.wcscda.worms.board.WormField;
import org.wcscda.worms.board.weapons.AbstractWeapon;
import org.wcscda.worms.board.weapons.Grenade;
import org.wcscda.worms.board.weapons.Hadoken;
import org.wcscda.worms.board.weapons.Shotgun;

public class Player {
  private final String name;
  private final Color color;
  private final ArrayList<Worm> worms = new ArrayList<Worm>();
  private AbstractWeapon currentWeapon;
  private int currentWormIndex = 0;
  private static boolean debutant = false;

  public Player(String name, Color color, Boolean debutant) {
    this.name = name;
    this.color = color;
    Player.debutant = debutant;
  }


  public static boolean isDebutant() {
    return debutant;
  }

  public String getName() {
    return name;
  }

  public Worm createWorm(String nom) {
    Worm worm = new Worm(this, nom);
    worms.add(worm);

    return worm;
  }

  public Color getColor() {
    return color;
  }

  public AbstractWeapon getCurrentWeapon() {
    return currentWeapon;
  }

  public ArrayList<Worm> getWorms() {
    return worms;
  }

  public Worm getActiveWorm() {
    if (getWorms().isEmpty()) {
      return null;
    }
    return getWorms().get(currentWormIndex);
  }

  public void setNextWorm() {
    if (worms.isEmpty()) return;

    currentWormIndex += 1;
    currentWormIndex %= worms.size();
  }

  /* NRO 2021-09-30 : TODO-student make a better version of
   * this, this is just a temporary version :-)
   * This should call the inventory, and handle
   */
  public void changeWeapon() {
    if (currentWeapon.isChangingWeaponDisabled()) {
      return;
    }

    if (currentWeapon instanceof Hadoken) {
      currentWeapon = new Shotgun();
    } else {
      currentWeapon = new Grenade();
      //currentWeapon = new Hadoken();
    }
  }

  public void initWeapon() {
    currentWeapon = new Hadoken();
  }

<<<<<<< HEAD
  public int getPlayerLife() {
    int playerLife = 0;
    for ( Worm worm : this.getWorms()) {
      playerLife += worm.getLife();
    }
    return playerLife;
  }

  public static void isPlayerDie() {
    for (Player player : Helper.getTC().getPlayers()) {
      if (player.getPlayerLife() <= 0) {
        Helper.getTC().setCurrentNbPlayer(Helper.getTC().getCurrentNbPlayer() - 1);
      }
    }
=======
  public boolean hasWorms() {
    return !getWorms().isEmpty();
>>>>>>> 37a9922c24512b917633c34f84273bc54ed69716
  }
}

