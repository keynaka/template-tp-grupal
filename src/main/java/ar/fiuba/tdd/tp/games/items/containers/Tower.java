package ar.fiuba.tdd.tp.games.items.containers;

import ar.fiuba.tdd.tp.games.exceptions.InvalidMoveException;
import ar.fiuba.tdd.tp.games.items.DiskAdapter;
import ar.fiuba.tdd.tp.games.items.Item;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by Patri on 23/04/2016.
 */

public class Tower {

    private Stack<DiskAdapter> disks;
    private String towerId;


    public Tower(int numberOfDisks, String towerId) {

        this.disks = new Stack<DiskAdapter>();

        this.towerId = towerId;

        int size = numberOfDisks;

        while (size > 0) {
            try {
                this.addDisk(new DiskAdapter("disk", Integer.toString(size)));
            } catch (InvalidMoveException e) {
                e.getMessage();
            }
            size--;
        }

    }

    /*
    *  If the tower is not empty, it adds the disk to
    *  the top (if it's smaller than the current top).
    *  If the tower is empty, it adds the disk.
    *  If it's bigger than the current top, throws an InvalidMoveException.
    */

    public void addDisk(Item received) throws InvalidMoveException {

        if (received instanceof DiskAdapter) {

            DiskAdapter disk = DiskAdapter.class.cast(received);

            if (!this.disks.isEmpty()) {

                DiskAdapter topDisk = this.disks.peek();

                if (disk.getSize() < topDisk.getSize()) {

                    this.disks.push(disk);

                } else {

                    throw new InvalidMoveException();

                }

            } else {

                this.disks.push(disk);

            }
        }
    }

    /*
    * Gets the top disk without removing it from the tower
    * If the tower is empty, returns null
    */

    public Item checkTop() {
        try {
            return this.disks.peek();
        } catch (EmptyStackException e) {
            return null;
        }
    }

    /*
    * Gets the top disk and removes it from the tower
    * If the tower is empty, returns null
    */

    public Item getTop() {
        try {
            return this.disks.pop();
        } catch (EmptyStackException e) {
            return null;
        }
    }

    /* Returns towers' number of disks */
    public int getSize() {
        return this.disks.size();
    }

    public String getTowerId() {
        return towerId;
    }

}
