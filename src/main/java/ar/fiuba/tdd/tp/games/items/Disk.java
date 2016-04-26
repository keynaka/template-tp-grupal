package ar.fiuba.tdd.tp.games.items;

/**
 * Created by Patri on 24/04/2016.
 */
public class Disk extends Item {

    Disk disk;

    /*
    *  Creates disk with the size received in 'description'
    *  If description cannot be converted to int, disk is null.
    */
    public Disk(String name, String description) {

        super(name, description);
        try {
            int size = Integer.parseInt(description);
            disk = new Disk(size);
        } catch (NumberFormatException e) {
            disk = null;
        }
    }

    /* Returns disk size, if disk is null it throws a NumberFormatException */
    public int getSize() throws NumberFormatException {
        if (disk != null) {
            return this.disk.getSize();
        } else {
            throw new NumberFormatException();
        }
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
