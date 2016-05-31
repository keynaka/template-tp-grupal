package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.ItemKeeper;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by Nico on 21/05/2016.
 */
public class SwitchItemOwnerAction implements Action {

//    private ConcreteGame game;
//
//    private String oldOwnerName;
//    private String newOwnerName;
    private ItemKeeper oldOwner;

    private ItemKeeper newOwner;
    private String itemName;

//    public SwitchItemOwnerAction(ConcreteGame game, String oldOwnerName, String newOwnerName, String itemName) {
//        this.game = game;
//        this.oldOwnerName = oldOwnerName;
//        this.newOwnerName = newOwnerName;
//        this.itemName = itemName;
//    }

    public SwitchItemOwnerAction(ItemKeeper oldOwner, ItemKeeper newOwner, String itemName) {
        this.oldOwner = oldOwner;
        this.newOwner = newOwner;
        this.itemName = itemName;
    }


    public void doAction() {
        Item item = this.oldOwner.removeItem(itemName);
        this.newOwner.insertItem(item);
    }
}