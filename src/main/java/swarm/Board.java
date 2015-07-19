package swarm;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class Board extends JPanel implements Runnable {
	private static double r = -1, x, y, vx, vy;
	
	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);
		
		drawDot(g2d);
	}

	private void moveDot() {
		Dimension d = getSize();
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

	private void drawDot(final Graphics2D g) {
		Ellipse2D e = new Ellipse2D.Double(x, y, 2*r, 2*r);
		g.draw(e);
	}

	public void run() {
		while(true) {
			moveDot();
			repaint();
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
                System.out.println("Interrupted: " + e.getMessage());
			}
		}
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		
		Dimension d = new Dimension(200, 200);

		r = Math.random() * Math.min(d.getWidth(), d.getHeight()) / 4;
		x = Math.random() * d.getWidth();
		y = Math.random() * d.getHeight();
		vx = Math.random() * 10 - 5;
		vy = Math.random() * 10 - 5;
		
		new Thread(this).start();
	}
}