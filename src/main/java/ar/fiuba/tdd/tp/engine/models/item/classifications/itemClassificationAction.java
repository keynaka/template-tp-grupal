package ar.fiuba.tdd.tp.engine.models.item.classifications;

/**
 * Created by Nico on 20/05/2016.
 */
public abstract class ItemClassificationAction {
    protected boolean state;
    protected String itemType;

    public boolean getState() {
        return state;
    }

    public String getItemType() {
        return itemType;
    }
}
