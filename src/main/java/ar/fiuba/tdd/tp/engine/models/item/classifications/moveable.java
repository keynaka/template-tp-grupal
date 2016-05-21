package ar.fiuba.tdd.tp.engine.models.item.classifications;

/**
 * Created by Nico on 20/05/2016.
 */
public class Moveable extends ItemClassificationAction implements ISingleActionItem {

    public Moveable(boolean isMoveable) {
        itemType = ItemClassificationType.MOVEABLE;
        state = isMoveable;
    }

    public boolean doAction() throws ItemClassificationTwiceActionException {
        if (state) {
            throw new ItemClassificationTwiceActionException();
        }
        state = true;
        return state;
    }
}
