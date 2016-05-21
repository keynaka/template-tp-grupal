package ar.fiuba.tdd.tp.engine.models.item;

import ar.fiuba.tdd.tp.engine.models.IItemKeeper;
import ar.fiuba.tdd.tp.engine.models.ItemBag;
import ar.fiuba.tdd.tp.engine.models.Stage;

/**
 * Created by Nico on 20/05/2016.
 */
public class Player implements IItemKeeper {
    protected String name;
    protected Stage currentStage;
    protected ItemBag itemsBag;

    public Player(String name, int maxItems) {
        this.name = name;
        this.itemsBag = new ItemBag(maxItems);
    }

    public Player(String name) {
        this.name = name;
        this.itemsBag = new ItemBag();
    }

    public Player() {
        this("UNKOWN_PLAYER");
    }

    public void setCurrentStage(Stage stage) {
        currentStage = stage;
    }

    public ItemBag getItemsBag() {
        return itemsBag;
    }
}
