package de.amshaegar.swarm.ai;

import de.amshaegar.swarm.Board;

public class Repeat extends AI {

	private final AI ai;

	public Repeat(final AI ai) {
		this.ai = ai;
	}

	@Override
	public void start() {
		super.start();
		ai.start();
	}

	@Override
	public void tick(final Object o, final Board board) {
		if (isActive()) {
			ai.tick(o, board);

			switch (ai.getState()) {
			case Success:
				ai.start();
				break;
			case Failure:
				fail();
				break;

			default:
				break;
			}
		}
	}

}
