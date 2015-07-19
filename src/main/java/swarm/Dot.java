package swarm;

import java.awt.Dimension;

public class Dot {
	private double r, x, y, vx, vy;
	
	public double getR() {
		return r;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Dot(Dimension d) {
		r = 10;
		x = Math.random() * d.getWidth();
		y = Math.random() * d.getHeight();
		vx = Math.random() * 10 - 5;
		vy = Math.random() * 10 - 5;
	}

	public void move(Dimension d) {
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
