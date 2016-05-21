package ar.fiuba.tdd.tp.engine.models.item.classifications;

/**
 * Created by Nico on 20/05/2016.
 */
public interface IPairActionItem {
    public boolean doActionWith(int idItem) throws ItemClassificationTwiceActionException;
    public int getIdItemPair();
}
