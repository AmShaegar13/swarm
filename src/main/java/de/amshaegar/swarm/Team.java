package de.amshaegar.swarm;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import de.amshaegar.swarm.ai.AI;
import de.amshaegar.swarm.ai.Repeat;
import de.amshaegar.swarm.ai.teams.SpawnDot;
import de.amshaegar.swarm.util.Vector2D;

public class Team {

	private final static Image CASTLE = Toolkit.getDefaultToolkit().getImage("castle.png");
	private final int NUMBER_OF_EDGES = 12;
	private final int RADIUS = 50;
	private final int BORDER_SIZE = 10;

	private final String name;
	private final Faction faction;
	private final Vector2D home;
	private final AI ai;
	private final List<Vector2D> territory = new ArrayList<Vector2D>();

	public Team(final String name, final Faction faction, final Vector2D home) {
		this.name = name;
		this.faction = faction;
		this.home = home;

		ai = new Repeat(new SpawnDot());
		ai.start();

		for (int i = 0; i < NUMBER_OF_EDGES; i++) {
			final double t = 2 * Math.PI * i / NUMBER_OF_EDGES;
			final int x = (int) Math.round(RADIUS * Math.cos(t));
			final int y = (int) Math.round(RADIUS * Math.sin(t));
			territory.add(home.add(x, y));
		}
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
		final int[] x = new int[territory.size()];
		final int[] y = new int[territory.size()];

		for (int i = 0; i < territory.size(); i++) {
			final Vector2D v = territory.get(i);
			x[i] = (int) Math.round(v.getX());
			y[i] = (int) Math.round(v.getY());
		}

		g2d.setColor(faction.getColor());
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g2d.fillPolygon(x, y, territory.size());

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		for (int i = 0; i < territory.size(); i++) {
			final Vector2D v = territory.get(i);
			g2d.setColor(faction.getColor());
			g2d.fillOval((int) Math.round(v.getX()) - BORDER_SIZE / 2, (int) Math.round(v.getY()) - BORDER_SIZE / 2,
					BORDER_SIZE, BORDER_SIZE);
			g2d.setColor(Color.black);
			g2d.drawOval((int) Math.round(v.getX()) - BORDER_SIZE / 2, (int) Math.round(v.getY()) - BORDER_SIZE / 2,
					BORDER_SIZE, BORDER_SIZE);
		}

		g2d.drawImage(CASTLE, (int) Math.round(home.getX() - CASTLE.getWidth(null) / 2),
				(int) Math.round(home.getY() - CASTLE.getHeight(null)), null);
	}

}
