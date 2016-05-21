package ar.fiuba.tdd.tp.engine.models.item.classifications;

/**
 * Created by Nico on 20/05/2016.
 */
public interface ISingleActionItem {
    public abstract boolean doAction() throws ItemClassificationTwiceActionException;
}
