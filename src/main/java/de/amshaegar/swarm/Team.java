package de.amshaegar.swarm;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import de.amshaegar.swarm.ai.AI;
import de.amshaegar.swarm.ai.Repeat;
import de.amshaegar.swarm.ai.teams.SpawnDot;
import de.amshaegar.swarm.util.Vector2D;

public class Team {

	private final static Image CASTLE = Toolkit.getDefaultToolkit().getImage("castle.png");

	private final String name;
	private final Faction faction;
	private final Vector2D home;
	private final AI ai;

	public Team(final String name, final Faction faction, final Vector2D home) {
		this.name = name;
		this.faction = faction;
		this.home = home;

		this.ai = new Repeat(new SpawnDot());
		this.ai.start();
	}

	public String getName() {
		return name;
	}

	public Faction getFaction() {
		return faction;
	}

	public Vector2D getHome() {
		return home;
	}

	public void tick(final Board board) {
		ai.tick(this, board);
	}

	public void paint(final Graphics2D g2d) {
		g2d.drawImage(CASTLE, (int) Math.round(home.getX() - CASTLE.getWidth(null) / 2),
				(int) Math.round(home.getY() - CASTLE.getHeight(null)), null);
	}

}
