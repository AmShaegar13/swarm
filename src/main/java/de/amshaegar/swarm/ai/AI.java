package de.amshaegar.swarm.ai;

import de.amshaegar.swarm.Board;

public abstract class AI {

	public enum State {
		Success, Failure, Active
	}

	protected State state;

	public void start() {
		this.state = State.Active;
	}

	public abstract void tick(Object o, Board board);

	protected void succeed() {
		this.state = State.Success;
	}

	protected void fail() {
		this.state = State.Failure;
	}

	public boolean isSuccess() {
		return state.equals(State.Success);
	}

	public boolean isFailure() {
		return state.equals(State.Failure);
	}

	public boolean isActive() {
		return state.equals(State.Active);
	}

	public State getState() {
		return state;
	}

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

}
