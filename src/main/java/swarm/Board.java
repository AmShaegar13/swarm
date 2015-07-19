package swarm;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class Board extends JPanel implements Runnable {
	private static Dot dot;
	
	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);
		
		drawDot(g2d, dot);
	}

	private void drawDot(final Graphics2D g, Dot dot) {
		Ellipse2D e = new Ellipse2D.Double(dot.getX(), dot.getY(), 2*dot.getR(), 2*dot.getR());
		g.draw(e);
	}

	public void run() {
		while(true) {
			dot.move(getSize());
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
		
		dot = new Dot(new Dimension(200, 200));
		
		new Thread(this).start();
	}
}