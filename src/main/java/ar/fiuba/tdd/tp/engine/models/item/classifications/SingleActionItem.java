package ar.fiuba.tdd.tp.engine.models.item.classifications;

/**
 * Created by Nico on 21/05/2016.
 */
public abstract class SingleActionItem extends ItemClassificationAction implements ISingleActionItem {
    public boolean doAction() throws ItemClassificationTwiceActionException {
        if (state) {
            throw new ItemClassificationTwiceActionException();
        }
        state = true;
        return state;
    }
}
