package ar.fiuba.tdd.tp.engine.core;

import ar.fiuba.tdd.tp.engine.models.item.Player;

/**
 * Created by Nico on 20/05/2016.
 */
public class Game {

    protected String name;
    protected String description;
    protected Player player;

    public Game(String gameName) {
        name = gameName;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
