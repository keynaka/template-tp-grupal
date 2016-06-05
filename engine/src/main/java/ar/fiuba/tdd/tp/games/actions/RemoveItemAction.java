package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ItemKeeper;
import ar.fiuba.tdd.tp.games.items.Item;

import java.util.Random;

/**
 * Created by Nico on 28/05/2016.
 */
public class RemoveItemAction implements Action {

    private ItemKeeper itemKeeper;
    private Item item;
    private static final int NO_ITEM = -1;

    public RemoveItemAction(ItemKeeper itemKeeper, Item item) {
        this.itemKeeper = itemKeeper;
        this.item = item;
    }

    public RemoveItemAction(ItemKeeper itemKeeper) {
        // If no item, will check for a random item in runtime
        this(itemKeeper, null);
    }

    @Override
    public void doAction() {
        String itemName;
        if (item == null) {
            // Removes a random  item
            Random randomGenerator = new Random();
            int index = randomGenerator.nextInt(itemKeeper.getItems().size());
            Item randomItem = (Item) itemKeeper.getItems().toArray()[index];
            itemName = randomItem.getName();
        } else {
            itemName = item.getName();
        }
        itemKeeper.removeItem(itemName);
    }
}
