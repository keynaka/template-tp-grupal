package ar.fiuba.tdd.tp.engine.core.rules;

import ar.fiuba.tdd.tp.engine.models.item.Item;
import ar.fiuba.tdd.tp.engine.models.item.classifications.ItemClassificationType;

/**
 * Created by Nico on 23/05/2016.
 */
public class RuleItemIsOpen extends Rule {
    protected Item item;

    public RuleItemIsOpen(Item item) {
        setItem(item);
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean verify() {
        boolean condition;
        try {
            condition = item.getClassificationState(ItemClassificationType.OPENABLE);
        } catch (Exception e) {
            condition = false;
        }
        return (negateCondition) ? !condition : condition;
    }
}
