package ar.fiuba.tdd.tp.engine.models.item.classifications;

/**
 * Created by Nico on 20/05/2016.
 */
public class Pickable extends ItemClassificationAction implements ISingleActionItem {
    public Pickable(boolean isPickable) {
        itemType = ItemClassificationType.PICKABLE;
        state = isPickable;
    }

    public boolean doAction() throws ItemClassificationTwiceActionException {
        if (state) {
            throw new ItemClassificationTwiceActionException();
        }
        state = true;
        return state;
    }
}
