package swarm.util;

public class Vector2D {

	private double x, y;

	public Vector2D(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return String.format("Vector<%.3f:%.3f>", x, y);
	}

	public double getX() {
		return x;
	}

	public void setX(final double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(final double y) {
		this.y = y;
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
		return new Vector2D(x / m, y / m);
	}

	public double lengthSquared() {
		return Math.pow(x, 2) + Math.pow(y, 2);
	}

	public double length() {
		return Math.sqrt(lengthSquared());
	}

	public Vector2D normalize() {
		return divide(length());
	}

}
