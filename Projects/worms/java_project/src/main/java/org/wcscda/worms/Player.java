package org.wcscda.worms;

import java.awt.Color;
import java.util.ArrayList;
import org.wcscda.worms.board.weapons.AbstractWeapon;
import org.wcscda.worms.board.weapons.Hadoken;
import org.wcscda.worms.board.weapons.Shotgun;

public class Player {
  private final String name;
  private final Color color;
  private final ArrayList<Worm> worms = new ArrayList<Worm>();
  private AbstractWeapon currentWeapon;
  private int currentWormIndex = 0;

  public Player(String name, Color color) {
    this.name = name;
    this.color = color;
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
    return getWorms().get(currentWormIndex);
  }

  public void setNextWorm() {
    currentWormIndex += 1;
    currentWormIndex %= worms.size();
  }

  public void changeWeapon() {
    if (currentWeapon.isChangingWeaponDisabled()) {
      return;
    }

    if (currentWeapon instanceof Hadoken) {
      currentWeapon = new Shotgun();
    } else {
      currentWeapon = new Hadoken();
    }
  }

  public void initWeapon() {
    currentWeapon = new Hadoken();
  }
}
