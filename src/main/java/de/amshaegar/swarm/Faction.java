package de.amshaegar.swarm;

import java.awt.*;

public enum Faction {

    Blue(Color.blue),
    Red(Color.red),
    Yellow(Color.yellow),
    Green(Color.green);

    private final Color color;

    private Faction(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
