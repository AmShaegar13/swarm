package de.amshaegar.swarm;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JPanel;

import de.amshaegar.swarm.util.Vector2D;

public class Board extends JPanel {

	private final DotsContainer dots;

	public Board() {
		dots = new DotsContainer();

		for (final Faction faction : Faction.values()) {
			faction.setLocation(new Vector2D(Math.random() * 1180 + 50, Math.random() * 620 + 50));
		}
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

		for (final Faction faction : Faction.values()) {
			g2d.drawImage(castle, (int) Math.round(faction.getLocation().getX()),
					(int) Math.round(faction.getLocation().getY()), this);
		}

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