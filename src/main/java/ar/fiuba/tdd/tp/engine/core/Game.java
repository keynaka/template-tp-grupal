package ar.fiuba.tdd.tp.engine.core;

import ar.fiuba.tdd.tp.engine.models.Stage;
import ar.fiuba.tdd.tp.engine.models.item.Player;

import java.util.ArrayList;

/**
 * Created by Nico on 20/05/2016.
 */
public class Game {

    protected String name;
    protected String description;
    protected ArrayList<Stage> stages;
    protected Player player;
    protected Parser parser;

    // Game constants
    public static final String MAIN_ROOM = "MAIN_ROOM";
    public static final String FINISH_ROOM = "FINISH_ROOM";

    public Game(String gameName, String gameDescription) {
        setGameName(gameName);
        setDescription(gameDescription);
    }

    public Game() {
        this("DEAFULT_NAME", "DEFAULT_DESCRIPTION");
    }

    public void setGameName(String gameName) {
        name = gameName;
    }

    public void setStages(ArrayList<Stage> stages) {
        this.stages = stages;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }
}
