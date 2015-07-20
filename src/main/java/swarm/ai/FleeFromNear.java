package swarm.ai;

import swarm.Board;
import swarm.Dot;
import swarm.util.Vector2D;

public class FleeFromNear extends AI {

	private MoveTo moveTo;

	@Override
	public void tick(final Dot dot, final Board board) {
		moveTo = new MoveTo(fleeToPosition(dot));
		moveTo.start();
		moveTo.tick(dot, board);
	}

	private Vector2D fleeToPosition(final Dot dot) {
		return dot.getPosition().multiply(2).subtract(dot.nearDot().getPosition());
	}

}
