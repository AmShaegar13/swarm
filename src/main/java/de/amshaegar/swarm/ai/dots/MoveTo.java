package de.amshaegar.swarm.ai.dots;

import java.awt.Dimension;

import de.amshaegar.swarm.Board;
import de.amshaegar.swarm.Dot;
import de.amshaegar.swarm.ai.AI;
import de.amshaegar.swarm.util.Vector2D;

public class MoveTo extends AI {

	private static final double SPEED = 100 / 60; // in pixel per frame

	private final Vector2D destination;

	public MoveTo(final Vector2D destination) {
		this.destination = destination;
	}

	@Override
	public void tick(final Object o, final Board board) {
		final Dot dot = (Dot) o;
		if (isActive()) {
			move(dot, board);
		}
	}

	public void move(final Dot dot, final Board board) {
		final Dimension d = board.getSize();
		d.setSize(d.getWidth() - 2 * dot.getR(), d.getHeight() - 2 * dot.getR());

		final Vector2D v = destination.subtract(dot.getLocation()).normalize().multiply(SPEED);
		final Vector2D newLocation = dot.getLocation().add(v);
		double x = newLocation.getX();
		double y = newLocation.getY();

		if (x < 0) {
			x = 0;
		} else if (x > d.getWidth()) {
			x = d.getWidth();
		}

		if (y < 0) {
			y = 0;
		} else if (y > d.getHeight()) {
			y = d.getHeight();
		}

		newLocation.setLocation(x, y);
		dot.setLocation(newLocation);

		if (destination.distanceSq(newLocation) < SPEED) {
			succeed();
		}
	}

}
