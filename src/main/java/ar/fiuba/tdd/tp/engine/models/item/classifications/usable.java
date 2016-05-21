package ar.fiuba.tdd.tp.engine.models.item.classifications;

/**
 * Created by Nico on 20/05/2016.
 */
public class Usable extends ItemClassificationAction implements ISingleActionItem {
    public Usable(boolean isUsable) {
        itemType = ItemClassificationType.USABLE;
        state = isUsable;
    }

    public boolean doAction() throws ItemClassificationTwiceActionException {
        if (state) {
            throw new ItemClassificationTwiceActionException();
        }
        state = true;
        return state;
    }
}
