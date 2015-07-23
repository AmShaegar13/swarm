package de.amshaegar.swarm.ai.dots;

import de.amshaegar.swarm.Board;
import de.amshaegar.swarm.Dot;
import de.amshaegar.swarm.ai.AI;
import de.amshaegar.swarm.util.Vector2D;

public class FleeFromNear extends AI {

	private MoveTo moveTo;

	@Override
	public void tick(final Object o, final Board board) {
		final Dot dot = (Dot) o;
		if (isActive()) {
			moveTo = new MoveTo(fleeToPosition(dot));
			moveTo.start();
			moveTo.tick(dot, board);

			switch (moveTo.getState()) {
			case Success:
				succeed();
				break;
			case Failure:
				fail();
				break;

			default:
				break;
			}
		}
	}

	private Vector2D fleeToPosition(final Dot dot) {
		return dot.getLocation().multiply(2).subtract(dot.nearDot().getLocation());
	}

}
