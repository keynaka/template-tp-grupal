package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.exceptions.GameException;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.objects.GameObject;

import java.util.Collection;

/**
 * Created by swandelow on 4/21/16.
 */
@SuppressWarnings("CPD-START")
public class Player extends GameObject implements ItemKeeper {

    public static final String PLAYER_STATUS = "player-status";
    public static final String PLAYING = "playing";
    public static final String HAS_WON = "has won";
    public static final String HAS_LOST = "has won";


    private Inventory inventory;
    private String currentStage;
    private CharacterState state;

    public Player() {
        this.name = "player";
        this.inventory = new Inventory();
        this.state = CharacterState.HEALTHY;
        this.setPlaying();
    }

    public Player(String playerName) {
        super();
        this.name = playerName;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void addToInventory(Item item) {
        this.inventory.addItem(item);
    }

    public boolean hasItem(String itemName) {
        return this.inventory.contains(itemName);
    }

    public String getCurrentStage() {
        return this.currentStage;
    }

    public void setCurrentStage(String stageName) {
        this.currentStage = stageName;
    }

    public CharacterState getState() {
        return state;
    }

    public void modifyState(CharacterState state) {
        this.state = state;
    }

    public String pickFromStage(Stage stage, String itemName) {
        Item pickedItem = stage.pickItem(itemName);
        Collectible collectibleItem = null;
        try {
            collectibleItem = (Collectible) pickedItem;
        } catch (ClassCastException e) {
            throw new GameException("Unsupported action.");
        }
        String resultPick = collectibleItem.pick(this);
        String resultAdd = this.inventory.addItem(pickedItem);
        return resultAdd.concat(" ").concat(resultPick);
    }

    public String openFromStage(Stage stage, String itemName) {
        Item item = stage.getItem(itemName);
        Openable openable = null;
        try {
            openable = (Openable) item;
        } catch (ClassCastException e) {
            throw new GameException("Unsupported action.");
        }
        return openable.open(this);
    }

    public String talkTo(Stage currentRoom, String itemName, String message) {
        Item item = currentRoom.getItem(itemName);
        Talking talking = null;
        try {
            talking = (Talking) item;
        } catch (ClassCastException e) {
            throw new GameException("Unsupported action.");
        }
        return talking.talk(this, message);
    }

    public void setPlaying() {
        this.addState(PLAYER_STATUS, PLAYING);
    }

    public boolean isPlaying() {
        return this.getState(PLAYER_STATUS).equalsIgnoreCase(PLAYING);
    }

    public void setHasWon() {
        this.addState(PLAYER_STATUS, HAS_WON);
    }

    public boolean hasWon() {
        return this.getState(PLAYER_STATUS).equalsIgnoreCase(HAS_WON);
    }

    public void setHasLost() {
        this.addState(PLAYER_STATUS, HAS_LOST);
    }

    public boolean hasLost() {
        return this.getState(PLAYER_STATUS).equalsIgnoreCase(HAS_LOST);
    }


    @Override
    public Collection<Item> getItems() {
        return this.inventory.getAllItems();
    }

    @Override
    public Item removeItem(String itemName) {
        return this.inventory.dropItem(itemName);
    }

    @SuppressWarnings("CPD-END")
    @Override
    public void insertItem(Item item) {
        this.inventory.addItem(item);
    }
}
