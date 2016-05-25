package ar.fiuba.tdd.tp.engine.models.item.classifications;

/**
 * Created by Nico on 20/05/2016.
 */
public class Usable extends SingleActionItem {
    public Usable(boolean isUsable) {
        itemType = ItemClassificationType.USABLE;
        state = isUsable;
    }
}
