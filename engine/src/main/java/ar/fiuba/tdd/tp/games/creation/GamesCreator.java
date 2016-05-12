package ar.fiuba.tdd.tp.games.creation;

import ar.fiuba.tdd.tp.games.AbstractGame;
import ar.fiuba.tdd.tp.games.Game;

import java.util.ArrayList;

public class GamesCreator {

    public static Game getGameByName(String name) {
        return GameEnum.getGameByName(name);
    }

    public static boolean existGame(String name) {
        return GameEnum.existGame(name);
    }

    public static ArrayList<AbstractGame> getAllGames() {
        return GameEnum.getAllGames();
    }

}
