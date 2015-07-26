package de.amshaegar.swarm.ai;

import de.amshaegar.swarm.Board;

public abstract class AIProcessor extends AI {

	protected AI ai;

	protected void adoptState(final AI ai) {
		switch (ai.getState()) {
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

	@Override
	public void tick(final Object o, final Board board) {
		if (isActive()) {
			ai.tick(o, board);
			adoptState(ai);
			if (ai.isSuccess()) {
				start();
			}
		}
	}
}
