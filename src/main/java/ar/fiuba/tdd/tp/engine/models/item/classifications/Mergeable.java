package ar.fiuba.tdd.tp.engine.models.item.classifications;

/**
 * Created by Nico on 20/05/2016.
 */
public class Mergeable extends ItemClassificationAction implements IPairActionItem {
    protected int pairIdItem = -1;

    public Mergeable(boolean isMergeable) {
        itemType = ItemClassificationType.MERGEABLE;
        state = isMergeable;
    }

    public boolean doActionWith(int idItem) throws ItemClassificationTwiceActionException {
        // TODO: check that idItem is IPairActionItem
        if (state) {
            throw new ItemClassificationTwiceActionException();
        }
        state = true;
        return state;
    }

    public int getIdItemPair() {
        return pairIdItem;
    }
}
