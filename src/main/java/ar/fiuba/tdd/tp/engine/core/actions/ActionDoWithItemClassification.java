package ar.fiuba.tdd.tp.engine.core.actions;

import ar.fiuba.tdd.tp.engine.models.item.Item;

/**
 * Created by Nico on 23/05/2016.
 */
public class ActionDoWithItemClassification extends Action {
    Item item1;
    Item item2;
    String itemClassificationType;

    public ActionDoWithItemClassification(Item item1, Item item2, String itemClassificationType) {
        this.item1 = item1;
        this.item2 = item2;
        this.itemClassificationType = itemClassificationType;
    }

    public void doAction() {
        try {
            item1.doActionWith(itemClassificationType, item2.getId());
        } catch (Exception e) {
            System.out.print("Error: " + e + "\n");
        }
    }
}
