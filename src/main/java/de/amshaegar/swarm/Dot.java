package de.amshaegar.swarm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import de.amshaegar.swarm.ai.AI;
import de.amshaegar.swarm.util.Vector2D;

public class Dot {

	private final double r;
	private Vector2D location;
	private final Board board;
	private final AI ai;
	private final Faction faction;

	public Dot(final Board board, final AI ai, final Faction faction) {
		this.board = board;
		this.ai = ai;
		this.faction = faction;

		final Dimension d = board.getPreferredSize();

		r = 10;
		location = new Vector2D(Math.random() * (d.getWidth() - 2 * r), Math.random() * (d.getHeight() - 2 * r));
	}

	public double getR() {
		return r;
	}

	public Vector2D getLocation() {
		return location;
	}

	public void setLocation(final Vector2D location) {
		this.location = location;
	}

	public Faction getFaction() {
		return faction;
	}

	public void tick() {
		if (ai.getState() == null) {
			ai.start();
		}
		ai.tick(this, board);
	}

	public void paint(final Graphics2D g2d) {
		final Ellipse2D e = new Ellipse2D.Double(location.getX(), location.getY(), 2 * r, 2 * r);
		g2d.setColor(faction.getColor());
		g2d.fill(e);
		g2d.setColor(Color.black);
		g2d.draw(e);
	}

	public Dot nearDot() {
		Dot near = this;
		double distanceSquared = Double.MAX_VALUE;
		for (final Dot dot : board.getDots().getAll()) {
			if (dot == this) {
				continue;
			}
			final double distanceCurrent = dot.getLocation().distanceSq(getLocation());

			if (distanceCurrent < distanceSquared) {
				distanceSquared = distanceCurrent;
				near = dot;
			}
		}

		return near;
	}

}
