package ar.fiuba.tdd.tp.engine.games;

import ar.fiuba.tdd.tp.engine.models.item.Item;

/**
 * Created by Nico on 24/05/2016.
 */
public class CursedObject {
    // Game constants
    public static final String GAME_NAME = "CursedObject";
    public static final String GAME_DESCRIPTION = "This is Cursed Object. Get the room 3 to win the game.";
    public static final String PICK = "pick";
    public static final String OPEN = "open";
    public static final String TALK = "talk";
    public static final String STAGE2 = "Room 2";
    public static final String PICK_SUCCESS_MESSAGE = "There you go!";

    public static final int CURSED_OBJ_ID = Item.generateNewId();
    public static final String CURSED_OBJ_NAME = "gato";
    public static final String PICK_CURSED_OBJ = "Now you are cursed!";

    public static final int CURSED_DOOR_ID = Item.generateNewId();
    public static final String OPEN_CURSED_DOOR = "You enter room 2.";
    public static final String CURSED_DOOR_NAME = "door";

    public static final String THIEVE_MESSAGE = "Hi!\nThe thief has just stolen your object!";

    public static final String OPEN_DOOR_SUCCESS_MESSAGE = "You leaved the room!";
}
