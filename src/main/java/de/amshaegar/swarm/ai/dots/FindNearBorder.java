package de.amshaegar.swarm.ai.dots;

import java.util.Iterator;

import de.amshaegar.swarm.Board;
import de.amshaegar.swarm.Dot;
import de.amshaegar.swarm.Team;
import de.amshaegar.swarm.ai.AI;
import de.amshaegar.swarm.util.Vector2D;

public class FindNearBorder extends AI {

	private final MoveTo moveTo;

	public FindNearBorder(final Team team, final Vector2D location) {
		moveTo = new MoveTo(findNearBorder(team, location));
	}

	@Override
	public void start() {
		super.start();
		moveTo.start();
	}

	@Override
	public void tick(final Object o, final Board board) {
		final Dot dot = (Dot) o;
		if (isActive()) {
			moveTo.tick(dot, board);
			adoptState(moveTo);
		}
	}

	private Vector2D findNearBorder(final Team team, final Vector2D location) {
		final Iterator<Vector2D> territory = team.getTerritory().iterator();
		Vector2D targetStone = null;
		double targetDistance = Integer.MAX_VALUE;
		Vector2D previousStone = territory.next();
		do {
			final Vector2D currentStone = territory.next();
			final Vector2D edge = previousStone.add(previousStone.subtract(currentStone).multiply(0.5));
			final double edgeDistance = edge.distanceSq(location);
			previousStone = currentStone;

			if (targetStone == null || edgeDistance < targetDistance) {
				targetStone = edge;
				targetDistance = edgeDistance;
			}
		} while (territory.hasNext());
		return targetStone;
	}

}
