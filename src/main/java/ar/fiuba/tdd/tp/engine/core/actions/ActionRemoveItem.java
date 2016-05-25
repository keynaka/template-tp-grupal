package ar.fiuba.tdd.tp.engine.core.actions;

import ar.fiuba.tdd.tp.engine.core.Utils;
import ar.fiuba.tdd.tp.engine.models.IItemKeeper;
import ar.fiuba.tdd.tp.engine.models.item.Item;

/**
 * Created by Nico on 25/05/2016.
 */
public class ActionRemoveItem extends Action {

    private IItemKeeper itemKeeper;
    private int idItem;
    private static final int NO_ITEM = -1;

    public ActionRemoveItem(IItemKeeper itemKeeper, int idItem) {
        this.itemKeeper = itemKeeper;
        this.idItem = idItem;
    }

    public ActionRemoveItem(IItemKeeper itemKeeper) {
        // If no item, will check for a random item in runtime
        this(itemKeeper, NO_ITEM);
    }

    public void doAction() {
        try {
            if (idItem == NO_ITEM) {
                Item randomItem = Utils.getRandomFromIterator(itemKeeper.getItemsBag().iterator(), itemKeeper.getItemsBag().getAmount());
                idItem = randomItem.getId();
            }
            this.itemKeeper.getItemsBag().removeItem(idItem);
        } catch (Exception e) {
            System.out.print("Error: " + e + "\n");
        }
    }
}
