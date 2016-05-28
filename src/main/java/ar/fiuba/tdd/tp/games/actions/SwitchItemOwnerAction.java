package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ItemKeeper;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by Nico on 21/05/2016.
 */
public class SwitchItemOwnerAction implements Action {

    private ItemKeeper oldOwner;
    private ItemKeeper newOwner;
    private String itemName;


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