package swarm;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import swarm.ai.FleeFromNear;

public class Board extends JPanel {

	private static final long DOTS = 200;

	private static List<Dot> dots = new ArrayList<Dot>();

	public Board() {
		setPreferredSize(new Dimension(1280, 720));

        for (int i = 0; i < DOTS; i++) {
            dots.add(new Dot(this, new FleeFromNear()));
        }
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);

		final Graphics2D g2d = (Graphics2D) g;

		final RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);

		for (final Dot dot : dots) {
			g2d.draw(dot.draw());
		}
	}

    public void tick() {
        for (final Dot dot : dots) {
            dot.tick();
        }

        repaint();
    }

	public List<Dot> getDots() {
		return dots;
	}

}