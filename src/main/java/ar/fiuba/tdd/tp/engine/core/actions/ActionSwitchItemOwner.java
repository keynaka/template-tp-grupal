package ar.fiuba.tdd.tp.engine.core.actions;

import ar.fiuba.tdd.tp.engine.models.IItemKeeper;
import ar.fiuba.tdd.tp.engine.models.item.Item;

import java.util.NoSuchElementException;

/**
 * Created by Nico on 21/05/2016.
 */
public class ActionSwitchItemOwner extends Action {

    protected IItemKeeper oldOwner;
    protected IItemKeeper newOwner;
    protected int idItem;

    public void setOldOwner(IItemKeeper oldOwner) {
        this.oldOwner = oldOwner;
    }

    public void setNewOwner(IItemKeeper newOwner) {
        this.newOwner = newOwner;
    }

    public void setItem(int idItem) {
        this.idItem = idItem;
    }

    public void doAction() {
        try {
            Item item = oldOwner.getItemsBag().removeItem(idItem);
            newOwner.getItemsBag().addItem(item);
        } catch (NoSuchElementException e) {
            System.out.print("Error: " + e + "\n");
        }
    }
}
