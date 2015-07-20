package de.amshaegar.swarm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DotsContainer {

	private static List<Dot> dots = new LinkedList<Dot>();
	private static Map<Faction, List<Dot>> dotsByFaction = new HashMap<Faction, List<Dot>>();

	public DotsContainer() {
		for (final Faction faction : Faction.values()) {
			final List<Dot> list = new LinkedList<Dot>();
			dotsByFaction.put(faction, list);
		}
	}

	public List<Dot> getAll() {
		return new LinkedList<Dot>(dots);
	}

	public List<Dot> get(final Faction faction) {
		return dotsByFaction.get(faction);
	}

	public void add(final Dot dot, final Faction faction) {
		dots.add(dot);
		dotsByFaction.get(faction).add(dot);
	}

}
