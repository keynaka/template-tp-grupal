package ar.fiuba.tdd.tp.engine.models.item.classifications;

/**
 * Created by Nico on 20/05/2016.
 */
public class Openable extends ItemClassificationAction implements ISingleActionItem, IPairActionItem {
    protected int idKey = -1; // No key by default

    public Openable(boolean isOpenable) {
        itemType = ItemClassificationType.OPENABLE;
        state = isOpenable;
    }

    public void setKey(int idKey) {
        this.idKey = idKey;
    }

    public boolean doAction() {
        if (!state && idKey == -1) {
            state = true;
        }
        return state;
    }

    public boolean doActionWith(int idKey) throws ItemClassificationTwiceActionException {
        if (state) {
            throw new ItemClassificationTwiceActionException();
        }
        if (this.idKey == idKey) {
            state = true;
        }
        return state;
    }

    public int getIdItemPair() {
        return idKey;
    }
}
