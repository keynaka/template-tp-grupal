package ar.fiuba.tdd.tp.engine.models.item;

import ar.fiuba.tdd.tp.engine.models.item.classifications.ItemClassificationAction;
import ar.fiuba.tdd.tp.engine.models.item.classifications.ISingleActionItem;
import ar.fiuba.tdd.tp.engine.models.item.classifications.IPairActionItem;
import ar.fiuba.tdd.tp.engine.models.item.classifications.ItemClassificationTwiceActionException;

import java.util.Map;

/**
 * Created by Nico on 20/05/2016.
 */
public class Item {
    protected int id;
    protected Map<String, ItemClassificationAction> classifications; // ItemClassificationType => ItemClassificationAction

    public Item(int idItem){
        id = idItem;
    }

    /**
     * Used only to build the Item
     * @param type String
     * @param classification ItemClassificationAction
     */
    public void _addItemClassification(String type, ItemClassificationAction classification) {
        classifications.put(type, classification);
    }

    public boolean is(String itemClassificationType) {
        return this.classifications.containsKey(itemClassificationType);
    }

    public boolean doAction(String itemClassificationType) throws ItemClassificationTwiceActionException, ItemClassificationTypeNotSupportedException {
        if (!this.is(itemClassificationType)) {
            throw new ItemClassificationTypeNotSupportedException();
        }
        ItemClassificationAction itemAction = this.classifications.get(itemClassificationType);
        if (itemAction instanceof ISingleActionItem) {
            return ((ISingleActionItem) itemAction).doAction();
        }
        return false;
    }

    public boolean doActionWith(String itemClassificationType, int idItem) throws ItemClassificationTwiceActionException, ItemClassificationTypeNotSupportedException {
        if (!this.is(itemClassificationType)) {
            throw new ItemClassificationTypeNotSupportedException();
        }
        ItemClassificationAction itemAction = this.classifications.get(itemClassificationType);
        if (itemAction instanceof IPairActionItem) {
            return ((IPairActionItem) itemAction).doActionWith(idItem);
        }
        return false;
    }
}
