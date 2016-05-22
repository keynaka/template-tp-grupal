package ar.fiuba.tdd.tp.engine.models.item;

import ar.fiuba.tdd.tp.engine.models.item.classifications.ItemClassificationType;
import ar.fiuba.tdd.tp.engine.models.item.classifications.Openable;
import ar.fiuba.tdd.tp.engine.models.item.classifications.Pickable;

/**
 * Created by Nico on 21/05/2016.
 */
public class ItemFactory {

    public static Item createItemByType(String type) throws Exception {
        Item item = new Item();
        switch (type) {
            case ItemClassificationType.PICKABLE:
                item.addItemClassification(type, new Pickable(true));
                break;
            default:
                throw new Exception("Type " + type + " is not supported");
        }
        return item;
    }

    public static Item createKey(int idKey) {
        Item item;
        try {
            item = ItemFactory.createItemByType(ItemClassificationType.PICKABLE);
            item.setId(idKey);
            return item;
        } catch (Exception e) {
            System.out.print("Error: " + e + "\n");
        }
        return null;
    }

    public static Item createLockedDoor(int idKey) {
        Item door = new Item();
        door.addItemClassification(ItemClassificationType.OPENABLE, new Openable(false, idKey) );
        return door;
    }
}
