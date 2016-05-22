package ar.fiuba.tdd.tp.engine.models;

import ar.fiuba.tdd.tp.engine.models.item.IIdentificable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Nico on 20/05/2016.
 */
public class Stage implements IIdentificable<String>, IItemKeeper {
    protected String name;
    protected ArrayList<Stage> adjacentStages;
    protected boolean isOpened = false;
    protected ItemBag itemsBag;
    protected ArrayList<Character> characters;

    public Stage(String stageName) {
        setId(stageName);
        itemsBag = new ItemBag();
    }

    // Used only to build the Stage
    public void addAdjacentStage(Stage stage) {
        adjacentStages.add(stage);
    }

    // Used only to build the Stage
    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void setOpen() {
        isOpened = true;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return getName();
    }

    public void setId(String id) {
        name = id;
    }

    public ItemBag getItemsBag() {
        return itemsBag;
    }

    public boolean isAdjacentTo(String stageName) {
        Iterator<Stage> it = adjacentStages.iterator();
        while (it.hasNext()) {
            if (it.next().getName().equals(stageName)) {
                return true;
            }
        }
        return false;
    }
}
