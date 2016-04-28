package ar.fiuba.tdd.tp.games.creation;

import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.games.cursedobject.CursedObjectGame;
import ar.fiuba.tdd.tp.games.fetchquest.FetchQuest;
import ar.fiuba.tdd.tp.games.hanoitowers.HanoiTowers;
import ar.fiuba.tdd.tp.games.opendoor.OpenDoor;
import ar.fiuba.tdd.tp.games.opendoor.OpenDoor2;
import ar.fiuba.tdd.tp.games.woolfsheepcabbage.WolfSheepCabbage;

public enum GameEnum {

    FETCH_QUEST("Fetch Quest", new FetchQuest()),
    OPEN_DOOR("Open Door", new OpenDoor()),
    OPEN_DOOR_2("Open Door 2", new OpenDoor2()),
    WOLF("Wolf", new WolfSheepCabbage()),
    HANOI_TOWERS("Hanoi Towers", new HanoiTowers()),
    CURSED_OBJECT("Cursed Object", new CursedObjectGame());

    GameEnum(String name, Game game) {
        this.gameName = name;
        this.game = game;
    }

    private String gameName;
    private Game game;

    public static Game getGameByName(String name) {
        for (GameEnum gameEnum : GameEnum.values()) {
            if (gameEnum.getGameName().equalsIgnoreCase(name)) {
                return gameEnum.getGame();
            }
        }
        return null;
    }

    public static boolean existGame(String gameName) {
        for (GameEnum gameEnum : GameEnum.values()) {
            if (gameEnum.getGameName().equalsIgnoreCase(gameName)) {
                return true;
            }
        }
        return false;
    }

    public String getGameName() {
        return gameName;
    }

    public Game getGame() {
        return game;
    }

}
