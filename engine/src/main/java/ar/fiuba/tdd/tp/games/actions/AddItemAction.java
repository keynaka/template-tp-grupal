package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ItemKeeper;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by swandelow on 5/29/16.
 */
public class AddItemAction implements Action {

    private ItemKeeper itemKeeper;
    private Item item;

    public AddItemAction(ItemKeeper itemKeeper, Item item) {
        this.itemKeeper = itemKeeper;
        this.item = item;
    }

    @Override
    public void doAction() {
        this.itemKeeper.insertItem(item);
    }
}
