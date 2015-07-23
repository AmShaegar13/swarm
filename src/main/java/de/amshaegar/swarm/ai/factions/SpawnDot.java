package de.amshaegar.swarm.ai.factions;

import de.amshaegar.swarm.Board;
import de.amshaegar.swarm.Dot;
import de.amshaegar.swarm.Faction;
import de.amshaegar.swarm.ai.AI;
import de.amshaegar.swarm.ai.Repeat;
import de.amshaegar.swarm.ai.dots.FleeFromNear;

public class SpawnDot extends AI {

	int waitFrames;

	@Override
	public void start() {
		super.start();
		waitFrames = 60;
	}

	@Override
	public void tick(final Object o, final Board board) {
		if (isActive()) {
			if (--waitFrames == 0) {
				final AI ai = new Repeat(new FleeFromNear());
				ai.start();
				final Faction faction = (Faction) o;
				board.getDots().add(new Dot(board, ai, faction), faction);
				succeed();
			}
		}
	}

}
