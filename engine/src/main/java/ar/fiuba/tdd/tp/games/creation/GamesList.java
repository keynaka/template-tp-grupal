package ar.fiuba.tdd.tp.games.creation;

import ar.fiuba.tdd.tp.games.AbstractGame;
import ar.fiuba.tdd.tp.games.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gonzalo on 15/05/2016.
 */
public class GamesList {

    private static List<Game> gamesList = new ArrayList<>();

    public static void setGame(Game game) {
        gamesList.add(game);
    }

    public static Game getGameByName(String name) {

        for (Game game : gamesList) {
            if (game.getName().equals(name)) {
                return game;
            }
        }
        return null;
    }

    public static boolean existGame(String gameName) {
        return (getGameByName(gameName) != null);
    }

    public static ArrayList<AbstractGame> getAllGames() {
        ArrayList<AbstractGame> arrayListGame = new ArrayList<AbstractGame>();

        for (Game game : gamesList) {
            arrayListGame.add((AbstractGame) game);
        }

        return arrayListGame;
    }
}
