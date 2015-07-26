package de.amshaegar.swarm;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import de.amshaegar.swarm.util.Vector2D;

public class Board extends JPanel {

	private final DotsContainer dots;
	private final List<Team> teams;

	public Board() {
		teams = new LinkedList<Team>();

		for (final Faction faction : Faction.values()) {
			teams.add(new Team("Team " + faction.toString(), faction,
					new Vector2D(Math.random() * 1180 + 50, Math.random() * 620 + 50)));
		}

		dots = new DotsContainer(teams);
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

		for (final Team team : teams) {
			team.paint(g2d);
		}

		for (final Dot dot : dots.getAll()) {
			dot.paint(g2d);
		}
	}

	public void tick() {
		for (final Team team : teams) {
			team.tick(this);
		}
		for (final Dot dot : dots.getAll()) {
			dot.tick();
		}

		repaint();
	}

}