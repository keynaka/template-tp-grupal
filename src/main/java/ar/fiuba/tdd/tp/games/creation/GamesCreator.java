package ar.fiuba.tdd.tp.games.creation;

import ar.fiuba.tdd.tp.games.Game;

public class GamesCreator {

    public static Game getGameByName(String name) {
        return GameEnum.getGameByName(name);
    }

    public static boolean existGame(String name) {
        return GameEnum.existGame(name);
    }

}
