package org.wcscda.worms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import org.wcscda.worms.board.weapons.*;

public class Player {
  private final String name;
  private final Color color;
  private final ArrayList<Worm> worms = new ArrayList<Worm>();
  private AbstractWeapon currentWeapon;
  private int currentWormIndex = 0;
  private static boolean debutant = false;
  private static Iterator<AbstractWeapon> itr2;


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
    worm.setWarmsInventory();
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

    while (itr2.hasNext()) {
      if (currentWeapon == itr2) {
        currentWeapon = itr2.next();
      }
    }
  }

  public void setItr2() {
    Player.itr2 = Helper.getActiveWorm().getWarmsInventory().keySet().iterator();;
  }


  public void initWeapon() {
    currentWeapon = new Hadoken();
  }

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
  }

  public boolean hasWorms() {
    return !getWorms().isEmpty();
  }
}

