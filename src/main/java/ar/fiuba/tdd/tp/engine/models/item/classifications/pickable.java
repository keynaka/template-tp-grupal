package ar.fiuba.tdd.tp.engine.models.item.classifications;

/**
 * Created by Nico on 20/05/2016.
 */
public class Pickable extends SingleActionItem {
    public Pickable(boolean isPickable) {
        itemType = ItemClassificationType.PICKABLE;
        state = isPickable;
    }

    public Pickable() {
        this(false);
    }
}
