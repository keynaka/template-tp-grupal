package ar.fiuba.tdd.tp.games.rules;

import ar.fiuba.tdd.tp.games.ItemKeeper;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by swandelow on 5/27/16.
 */
public class HasItemRule extends Rule {

    private ItemKeeper itemKeeper;
    private Item item;

    public HasItemRule(ItemKeeper itemKeeper, Item item) {
        this.itemKeeper = itemKeeper;
        this.item = item;
    }

    @Override
    public boolean verify() {
        boolean condition = itemKeeper.getItems().contains(item);
        return (negateCondition) ? !condition : condition;
    }
}
