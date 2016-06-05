package ar.fiuba.tdd.tp.games.rules;

import ar.fiuba.tdd.tp.games.ItemKeeper;

/**
 * Created by Nico on 28/05/2016.
 */
public class HasItemsRule extends Rule {

    private ItemKeeper itemKeeper;

    public HasItemsRule(ItemKeeper itemKeeper) {
        this.itemKeeper = itemKeeper;
    }

    @Override
    public boolean doVerify() {
        return itemKeeper.getItems().size() > 0;
    }
}
