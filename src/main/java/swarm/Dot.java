package swarm;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import swarm.ai.AI;
import swarm.util.Vector2D;

public class Dot {

	private double r;
	private Vector2D position;
	private final Board board;
	private final AI ai;

	public Dot(final Board board, final AI ai) {
		this.board = board;
		this.ai = ai;

		final Dimension d = board.getPreferredSize();

		r = 10;
		position = new Vector2D(Math.random() * (d.getWidth() - 2 * r), Math.random() * (d.getHeight() - 2 * r));
	}

	public double getR() {
		return r;
	}

	public void setR(final double r) {
		this.r = r;
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(final Vector2D position) {
		this.position = position;
	}

	public void tick() {
		if (ai.getState() == null) {
			ai.start();
		}
		ai.tick(this, board);
	}

	public Shape draw() {
		return new Ellipse2D.Double(position.getX(), position.getY(), 2 * r, 2 * r);
	}

	public Dot nearDot() {
		Dot near = this;
		double distanceSquared = Double.MAX_VALUE;
		for (final Dot dot : board.getDots()) {
			if (dot == this) {
				continue;
			}
			final double distanceCurrent = dot.getPosition().distanceSquared(getPosition());

			if (distanceCurrent < distanceSquared) {
				distanceSquared = distanceCurrent;
				near = dot;
			}
		}

		return near;
	}

}
