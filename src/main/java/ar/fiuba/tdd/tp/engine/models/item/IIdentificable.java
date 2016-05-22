package ar.fiuba.tdd.tp.engine.models.item;

/**
 * Created by Nico on 21/05/2016.
 */
public interface IIdentificable<T> {
    T getId();

    void setId(T id);
}
