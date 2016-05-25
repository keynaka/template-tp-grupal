package ar.fiuba.tdd.tp.engine.core;

import ar.fiuba.tdd.tp.engine.core.rules.Rule;
import ar.fiuba.tdd.tp.engine.models.Stage;
import ar.fiuba.tdd.tp.engine.models.item.Item;
import ar.fiuba.tdd.tp.engine.models.item.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Nico on 20/05/2016.
 */
public class Game {

    private String name;
    private String description;
    private ArrayList<Stage> stages;
    private Player player;
    private Parser parser;
    private Rule conditionToWin;  // Cuando se gana el juego
    private Rule conditionToLose; // Cuando se pierde el juego

    // Game constants
    public static final String MAIN_ROOM = "MAIN_ROOM";
    public static final String FINISH_ROOM = "FINISH_ROOM";

    public Game(String gameName, String gameDescription) {
        setGameName(gameName);
        setDescription(gameDescription);

        conditionToWin = null;
        conditionToLose = null;
    }

    public Game() {
        this("DEAFULT_NAME", "DEFAULT_DESCRIPTION");
    }

    public void setGameName(String gameName) {
        name = gameName;
    }

    public String getName() {
        return name;
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

    public void setConditionToWin(Rule condition) {
        conditionToWin = condition;
    }

    // Checks if player win the game
    public boolean isWon() {
        return conditionToWin.verify();
    }

    // Checks if player lost the game
    public boolean isLost() {
        if (conditionToLose != null) {
            return conditionToLose.verify();
        }
        return false;
    }

    // TODO
    public String executeCommand(String command) {
        ArrayList<String> words = new ArrayList<>(Arrays.asList(command.split("\\s+")));
        String commandName = words.get(0);
        String itemName = words.get(1);
        Iterator<Item> it = player.getCurrentStage().getItemsBag().iterator();

        String commandResult = "Unknown command";
        while (it.hasNext()) {
            Item item = it.next();
            if (item.getName().equals(itemName)) {
                commandResult = item.runCommand(commandName);
                break;
            }
        }
        return commandResult;
    }
}
