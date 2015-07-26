package de.amshaegar.swarm.ai;

import java.util.Iterator;
import java.util.List;

import de.amshaegar.swarm.Board;

public class Sequence extends AIProcessor {

	private final List<AI> sequence;

	public Sequence(final List<AI> sequence) {
		this.sequence = sequence;
	}

	@Override
	public void start() {
		super.start();
		final Iterator<AI> sequence = this.sequence.iterator();
		do {
			ai = sequence.next();
		} while (ai.isSuccess() && sequence.hasNext());
		if (ai.isSuccess()) {
			succeed();
		} else {
			ai.start();
		}
	}

	@Override
	public void tick(final Object o, final Board board) {

	}

}
