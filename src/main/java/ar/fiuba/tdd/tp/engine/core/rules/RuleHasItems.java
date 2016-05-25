package ar.fiuba.tdd.tp.engine.core.rules;

import ar.fiuba.tdd.tp.engine.models.IItemKeeper;

/**
 * Created by Nico on 25/05/2016.
 */
public class RuleHasItems extends Rule {
    protected IItemKeeper itemKeeper;

    public RuleHasItems(IItemKeeper itemKeeper) {
        this.itemKeeper = itemKeeper;
    }

    public boolean verify() {
        return this.itemKeeper.getItemsBag().getAmount() > 0;
    }
}
