package swarm;

import java.awt.Dimension;

import swarm.ai.AI;
import swarm.util.Vector2D;

public class MoveTo extends AI {

	private static final double SPEED = 100; // in pixel per ~ 1 second

	private final Vector2D destination;

	public MoveTo(final double x, final double y) {
		destination = new Vector2D(x, y);
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
	}

}
