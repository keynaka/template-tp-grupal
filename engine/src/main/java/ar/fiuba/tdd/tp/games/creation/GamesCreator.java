package ar.fiuba.tdd.tp.games.creation;

import ar.fiuba.tdd.tp.games.AbstractGame;
import ar.fiuba.tdd.tp.games.Game;

import java.util.ArrayList;

public class GamesCreator {

    public static Game getGameByName(String name) {
        return GamesList.getGameByName(name);
    }

    public static boolean existGame(String name) {
        return GamesList.existGame(name);
    }

    public static ArrayList<AbstractGame> getAllGames() {
        return GamesList.getAllGames();
    }
/*
    FETCH_QUEST("Fetch Quest", new FetchQuestBuilder().build()),
    //OPEN_DOOR("Open Door", new OpenDoor()),
    //OPEN_DOOR_2("Open Door 2", new OpenDoor2()),
    WOLF("Wolf", new WolfSheepCabbage()),
    HANOI_TOWERS("Hanoi Towers", new HanoiTowers()),
    CURSED_OBJECT("Cursed Object", new CursedObjectGameBuilder().build());
*/
}
