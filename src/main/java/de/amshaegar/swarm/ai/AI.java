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
		return State.Success.equals(state);
	}

	public boolean isFailure() {
		return State.Failure.equals(state);
	}

	public boolean isActive() {
		return State.Active.equals(state);
	}

	public State getState() {
		return state;
	}

}
