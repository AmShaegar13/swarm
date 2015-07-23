package de.amshaegar.swarm.util;

import java.awt.geom.Point2D;

public class Vector2D extends Point2D.Double {

	public Vector2D(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return String.format("Vector<%.3f:%.3f>", x, y);
	}

	public Vector2D add(final Vector2D v) {
		return new Vector2D(x + v.getX(), y + v.getY());
	}

	public Vector2D subtract(final Vector2D v) {
		return new Vector2D(x - v.getX(), y - v.getY());
	}

	public Vector2D multiply(final double m) {
		return new Vector2D(x * m, y * m);
	}

	public Vector2D divide(final double m) {
		if (m == 0) {
			return new Vector2D(0, 0);
		}
		return new Vector2D(x / m, y / m);
	}

	public double lengthSq() {
		return Math.pow(x, 2) + Math.pow(y, 2);
	}

	public double length() {
		return Math.sqrt(lengthSq());
	}

	public Vector2D normalize() {
		return divide(length());
	}

}
