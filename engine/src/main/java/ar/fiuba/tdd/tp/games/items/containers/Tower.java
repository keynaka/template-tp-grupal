package ar.fiuba.tdd.tp.games.items.containers;

import ar.fiuba.tdd.tp.games.exceptions.InvalidMoveException;
import ar.fiuba.tdd.tp.games.items.Disk;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by Patri on 23/04/2016.
 */

public class Tower {

    private Stack<Disk> disks;
    private String towerId;


    public Tower(int numberOfDisks, String towerId) {

        this.disks = new Stack<Disk>();

        this.towerId = towerId;

        int size = numberOfDisks;

        while (size > 0) {
            try {
                this.addDisk(new Disk("disk", Integer.toString(size)));
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

    public void addDisk(Disk disk) throws InvalidMoveException {

        if (!this.disks.isEmpty()) {

            Disk topDisk = this.disks.peek();

            if (disk.getSize() < topDisk.getSize()) {

                this.disks.push(disk);

            } else {

                throw new InvalidMoveException();

            }

        } else {

            this.disks.push(disk);

        }
    }


    /*
    * Gets the top disk without removing it from the tower
    * If the tower is empty, returns null
    */

    public Disk checkTop() {
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

    public Disk getTop() {
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
