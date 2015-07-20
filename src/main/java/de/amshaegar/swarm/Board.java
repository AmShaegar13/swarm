package de.amshaegar.swarm;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Board extends JPanel {

	private final DotsContainer dots;

	public Board() {
		setPreferredSize(new Dimension(1280, 720));

		dots = new DotsContainer();
	}

	public DotsContainer getDots() {
		return dots;
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);

		final Graphics2D g2d = (Graphics2D) g;

		final RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);

		final Image castle = Toolkit.getDefaultToolkit().getImage("castle.png");
		g2d.drawImage(castle, 50, 50, this);

		for (final Dot dot : dots.getAll()) {
			dot.paint(g2d);
		}
	}

	public void tick() {
		for (final Dot dot : dots.getAll()) {
			dot.tick();
		}

		repaint();
	}

}