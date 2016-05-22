package ar.fiuba.tdd.tp.engine.core.rules;

import ar.fiuba.tdd.tp.engine.models.IItemKeeper;
import ar.fiuba.tdd.tp.engine.models.item.Item;

/**
 * Created by Nico on 21/05/2016.
 */
public class RuleHasItem extends Rule {
    protected IItemKeeper itemKeeper;
    protected int idItem;

    public RuleHasItem(IItemKeeper itemKeeper, Item item) {
        setItemKeeper(itemKeeper);
        setItem(item);
    }

    public RuleHasItem(IItemKeeper itemKeeper, int idItem) {
        setItemKeeper(itemKeeper);
        setIdItem(idItem);
    }

    public void setItemKeeper(IItemKeeper subject) {
        itemKeeper = subject;
    }

    public void setItem(Item item) {
        setIdItem(item.getId());
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public boolean verify() {
        boolean condition = itemKeeper.getItemsBag().hasItem(idItem);
        return (negateCondition) ? !condition : condition;
    }
}
