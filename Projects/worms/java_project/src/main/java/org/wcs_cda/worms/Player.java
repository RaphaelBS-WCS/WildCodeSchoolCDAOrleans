package org.wcs_cda.worms;

import java.awt.Color;
import java.util.ArrayList;

import org.wcs_cda.worms.board.AbstractDrawableElement;
import org.wcs_cda.worms.board.Worm;
import org.wcs_cda.worms.board.weapons.AbstractWeapon;
import org.wcs_cda.worms.board.weapons.Shotgun;

public class Player {
	private final String name;
	private final Color color;
	private ArrayList<Worm> worms = new ArrayList<Worm>();
	private AbstractWeapon currentWeapon;
	
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
		this.currentWeapon = new Shotgun();
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
		return new Shotgun();
	}
}
