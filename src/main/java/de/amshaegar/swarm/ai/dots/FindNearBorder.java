package de.amshaegar.swarm.ai.dots;

import java.util.Iterator;

import de.amshaegar.swarm.Board;
import de.amshaegar.swarm.Dot;
import de.amshaegar.swarm.Team;
import de.amshaegar.swarm.ai.AIProcessor;
import de.amshaegar.swarm.util.Vector2D;

public class FindNearBorder extends AIProcessor {

	public FindNearBorder(final Team team, final Vector2D location) {
		this.ai = new MoveTo(findNearBorder(team, location));
	}

	@Override
	public void start() {
		super.start();
		ai.start();
	}

	@Override
	public void tick(final Object o, final Board board) {
		final Dot dot = (Dot) o;
		if (isActive()) {
			ai.tick(dot, board);
			adoptState(ai);
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
