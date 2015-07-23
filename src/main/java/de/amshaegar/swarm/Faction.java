package de.amshaegar.swarm;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import de.amshaegar.swarm.ai.AI;
import de.amshaegar.swarm.ai.Repeat;
import de.amshaegar.swarm.ai.factions.SpawnDot;
import de.amshaegar.swarm.util.Vector2D;

public enum Faction {

	Blue(Color.blue), Red(Color.red), Yellow(Color.yellow), Green(Color.green);

	private final AI ai;

	private final Color color;
	private Vector2D location;

	private Faction(final Color color) {
		this.color = color;

		this.ai = new Repeat(new SpawnDot());
		this.ai.start();
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

	public void tick(final Board board) {
		ai.tick(this, board);
	}

	public void paint(final Graphics2D g2d) {
		final Image castle = Toolkit.getDefaultToolkit().getImage("castle.png");

		g2d.drawImage(castle, (int) Math.round(getLocation().getX() - castle.getWidth(null) / 2),
				(int) Math.round(getLocation().getY() - castle.getHeight(null)), null);
	}

}
