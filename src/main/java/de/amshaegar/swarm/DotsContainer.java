package de.amshaegar.swarm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DotsContainer {

	private static List<Dot> dots = new LinkedList<Dot>();
	private static Map<Team, List<Dot>> dotsByFaction = new HashMap<Team, List<Dot>>();

	public DotsContainer(final List<Team> teams) {
		for (final Team team : teams) {
			final List<Dot> list = new LinkedList<Dot>();
			dotsByFaction.put(team, list);
		}
	}

	public List<Dot> getAll() {
		return new LinkedList<Dot>(dots);
	}

	public List<Dot> get(final Faction faction) {
		return new LinkedList<Dot>(dotsByFaction.get(faction));
	}

	public void add(final Dot dot, final Team team) {
		dots.add(dot);
		dotsByFaction.get(team).add(dot);
	}

}
