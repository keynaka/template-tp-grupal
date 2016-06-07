package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.items.Item;

import java.util.Collection;

/**
 * Created by swandelow on 5/27/16.
 */
public interface ItemKeeper {

    Collection<Item> getItems();

    Item removeItem(String itemName);

    void insertItem(Item item);
}
