package de.amshaegar.swarm.ai.teams;

import de.amshaegar.swarm.Board;
import de.amshaegar.swarm.Dot;
import de.amshaegar.swarm.Team;
import de.amshaegar.swarm.ai.AI;
import de.amshaegar.swarm.ai.Repeat;
import de.amshaegar.swarm.ai.dots.FindNearBorder;

public class SpawnDot extends AI {

	private int waitFrames;

	@Override
	public void start() {
		super.start();
		waitFrames = 60;
	}

	@Override
	public void tick(final Object o, final Board board) {
		if (isActive()) {
			if (--waitFrames == 0) {
				final Team team = (Team) o;
				final AI ai = new Repeat(new FindNearBorder(team, team.getHome()));
				ai.start();
				board.getDots().add(new Dot(board, ai, team), team);
				succeed();
			}
		}
	}

}
