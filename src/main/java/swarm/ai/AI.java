package swarm.ai;

import swarm.Board;
import swarm.Dot;

public abstract class AI {

	public enum State {
		Success, Failure, Active
	}

	protected State state;

	public void start() {
		this.state = State.Active;
	}

	public abstract void tick(Dot dot, Board board);

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

}