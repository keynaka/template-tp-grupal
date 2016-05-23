package ar.fiuba.tdd.tp.engine.core.actions;

import ar.fiuba.tdd.tp.engine.models.item.Item;

/**
 * Created by Nico on 23/05/2016.
 */
public class ActionDoItemClassification extends Action {

    Item item;
    String itemClassificationType;

    public ActionDoItemClassification(Item item, String itemClassificationType) {
        this.item = item;
        this.itemClassificationType = itemClassificationType;
    }

    public void doAction() {
        try {
            item.doAction(itemClassificationType);
        } catch (Exception e) {
            System.out.print("Error: " + e + "\n");
        }
    }
}
