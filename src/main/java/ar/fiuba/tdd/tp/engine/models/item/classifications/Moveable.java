package ar.fiuba.tdd.tp.engine.models.item.classifications;

/**
 * Created by Nico on 20/05/2016.
 */
public class Moveable extends SingleActionItem {
    public Moveable(boolean isMoveable) {
        itemType = ItemClassificationType.MOVEABLE;
        state = isMoveable;
    }
}
