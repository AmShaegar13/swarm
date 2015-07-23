package de.amshaegar.swarm;

import java.awt.Color;

import de.amshaegar.swarm.util.Vector2D;

public enum Faction {

	Blue(Color.blue), Red(Color.red), Yellow(Color.yellow), Green(Color.green);

	private final Color color;
	private Vector2D location;

	private Faction(final Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public Vector2D getLocation() {
		return location;
	}

	public void setLocation(final Vector2D location) {
		this.location = location;
	}

}
