package de.amshaegar.swarm;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import de.amshaegar.swarm.ai.AI;
import de.amshaegar.swarm.util.Vector2D;

public class Dot {

	private final double r = 10;
	private Vector2D location;
	private final Board board;
	private final AI ai;
	private final Team team;

	public Dot(final Board board, final AI ai, final Team team) {
		this.board = board;
		this.ai = ai;
		this.team = team;
		this.location = team.getHome();
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

	public Team getTeam() {
		return team;
	}

	public void tick() {
		if (ai.getState() == null) {
			ai.start();
		}
		ai.tick(this, board);
	}

	public void paint(final Graphics2D g2d) {
		final Ellipse2D e = new Ellipse2D.Double(location.getX() - r, location.getY() - r, 2 * r, 2 * r);
		g2d.setColor(team.getFaction().getColor());
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
