package swarm;

import java.awt.Dimension;

import swarm.ai.AI;

public class Dot {
	private double r, x, y, vx, vy;
	private Board board;
	private AI ai;
	
	public double getR() {
		return r;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Dot(Board board) {
		this.board = board;
		
		Dimension d = board.getPreferredSize();
		
		r = 10;
		x = Math.random() * (d.getWidth() - 2 * r);
		y = Math.random() * (d.getHeight() - 2 * r);
		vx = Math.random() * 10 - 5;
		vy = Math.random() * 10 - 5;
	}
	
	public void tick() {
		move();
	}

	public void move() {
		Dimension d = board.getPreferredSize();
		d.setSize(d.getWidth() - 2 * r, d.getHeight() - 2 * r);
		
		x = x + vx;
		y = y + vy;

		double dx = Math.min(d.getWidth() - x, x);
		double dy = Math.min(d.getHeight() - y, y);

		if(dx < 0) {
			vx = -vx;
		}

		if(dy < 0) {
			vy = -vy;
		}
	}
}
