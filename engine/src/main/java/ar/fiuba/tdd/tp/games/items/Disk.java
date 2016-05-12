package ar.fiuba.tdd.tp.games.items;

/**
 * Created by Patri on 24/04/2016.
 */
public class Disk extends Item {

    public Disk(String name, String description) {
        super(name, description);
    }

    /* Returns disk size, if size cannot be converted to int throws an exception. */
    public int getSize() throws NumberFormatException {
        return Integer.parseInt(this.description);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


}
