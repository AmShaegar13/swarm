package de.amshaegar.swarm.ai;

public class Repeat extends AIProcessor {

	public Repeat(final AI ai) {
		this.ai = ai;
	}

	@Override
	public void start() {
		super.start();
		ai.start();
	}

}
