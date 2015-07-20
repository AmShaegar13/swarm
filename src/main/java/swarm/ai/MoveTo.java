package swarm.ai;

import java.awt.Dimension;

import swarm.Board;
import swarm.Dot;
import swarm.util.Vector2D;

public class MoveTo extends AI {

	private static final double SPEED = 100; // in pixel per ~ 1 second

	private final Vector2D destination;

	public MoveTo(final Vector2D destination) {
		this.destination = destination;
	}

	@Override
	public void tick(final Dot dot, final Board board) {
		if (isActive()) {
			move(dot, board);
		}
	}

	public void move(final Dot dot, final Board board) {
		final Dimension d = board.getPreferredSize();
		d.setSize(d.getWidth() - 2 * dot.getR(), d.getHeight() - 2 * dot.getR());

		final Vector2D v = destination.subtract(dot.getPosition()).normalize().multiply(SPEED / 60);

		dot.setPosition(dot.getPosition().add(v));
		if (dot.getPosition().getX() < 0) {
			dot.getPosition().setX(0);
		} else if (dot.getPosition().getX() > d.getWidth()) {
			dot.getPosition().setX(d.getWidth());
		}

		if (dot.getPosition().getY() < 0) {
			dot.getPosition().setY(0);
		} else if (dot.getPosition().getY() > d.getHeight()) {
			dot.getPosition().setY(d.getHeight());
		}

		final double distanceSquared = destination.subtract(dot.getPosition()).lengthSquared();

		if (distanceSquared < SPEED / 60) {
			succeed();
		}
	}

}
