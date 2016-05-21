package ar.fiuba.tdd.tp.engine.models.item.classifications;

/**
 * Created by Nico on 20/05/2016.
 */
public class Openable extends ItemClassificationAction implements ISingleActionItem, IPairActionItem {
    protected int idKey = -1; // No key by default
    protected static final int NO_KEY = -1;

    public Openable(boolean isOpen, int idKey) {
        itemType = ItemClassificationType.OPENABLE;
        state = isOpen;
        this.idKey = idKey;
    }

    public Openable(boolean isOpen) {
        // There is no key needed to open it
        this(isOpen, Openable.NO_KEY);
    }

    public Openable() {
        // Is closed, and there is no key needed to open it
        this(false, Openable.NO_KEY);
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
