package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.exceptions.InvalidMoveException;
import ar.fiuba.tdd.tp.games.hanoitowers.HanoiTowers;
import ar.fiuba.tdd.tp.games.items.Disk;
import ar.fiuba.tdd.tp.games.items.containers.Tower;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Patri on 24/04/2016.
 */
public class HanoiTowerTests {

    private Tower tower;
    private HanoiTowers target = new HanoiTowers();
/*
    @Test
    public void testHappyPath() {
        String response = this.target.start();
        assertEquals("Welcome to Hanoi Towers!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Set disks"), "2"));
        assertEquals("You are now playing with 2 disks !", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Examine"), "1"));
        assertEquals("You can check top/move top.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Top size"), "1"));
        assertEquals("Size of top from tower 1 is 1.", response);
        assertFalse(this.target.isFinished());

        moveDisks();

    }


    private void moveDisks() {
        String response = this.target.play(new Command(new Action("Move top"), "1", "2"));
        assertEquals("moved!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Move top"), "1", "3"));
        assertEquals("moved!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Move top"), "2", "3"));
        assertEquals("You won the game!", response);
        assertTrue(this.target.isFinished());
    }

    @Test
    public void setInvalidNumberOfDisks() {
        this.target.start();
        String response = this.target.play(new Command(new Action("Set disks"), "nothing"));
        assertEquals("Invalid command: you must specify a number of disks to play with.", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void invalidExamine() {
        this.target.start();
        this.target.play(new Command(new Action("Set disks"), "2"));
        String response = this.target.play(new Command(new Action("Examine"), "nothing"));
        assertEquals("Invalid command!", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void tryToOperateWithHanoiTowersWithoutIndicatingDiskNumber() {
        this.target.start();
        String response = this.target.play(new Command(new Action("Examine"), "1"));
        assertEquals("You have to tell me how many disks you want to use first!", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void invalidMoveOperation() {
        String response = this.target.start();
        this.target.play(new Command(new Action("Set disks"), "2"));
        response = this.target.play(new Command(new Action("Move top"), "anything else"));
        assertEquals("Invalid Command: you must specify origin tower and destiny tower (between numbers 1 and 3).", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void tryToMoveTopWhenOriginTowerIsEmpty() {
        String response = this.target.start();
        this.target.play(new Command(new Action("Set disks"), "2"));
        response = this.target.play(new Command(new Action("Move top"), "2", "3"));
        assertEquals("Origin tower is empty!", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void tryToMoveBiggerDiskOverSmallerDisk() {
        String response = this.target.start();
        this.target.play(new Command(new Action("Set disks"), "2"));
        response = this.target.play(new Command(new Action("Move top"), "1", "2"));
        response = this.target.play(new Command(new Action("Move top"), "1", "2"));
        assertEquals("Invalid Move: tower 1 top is smaller than tower 2 top!", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void invalidOriginTower() {
        String response = this.target.start();
        this.target.play(new Command(new Action("Set disks"), "2"));
        response = this.target.play(new Command(new Action("Move top"), "80", "2"));
        assertEquals("Invalid Command: you must specify origin tower and destiny tower (between numbers 1 and 3).", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void invalidDestinyTower() {
        String response = this.target.start();
        this.target.play(new Command(new Action("Set disks"), "2"));
        response = this.target.play(new Command(new Action("Move top"), "2", "80"));
        assertEquals("Invalid Command: you must specify origin tower and destiny tower (between numbers 1 and 3).", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void createDiskSizeTwo() {
        Disk disk = new Disk("disk", "2");
        assertEquals(disk.getSize(), 2);
    }


    @Test
    public void createTowerWithOneDisk() {
        tower = new Tower(1, "1");
        tower.getTop(); // gets the top
        assertEquals(tower.getTop(), null); // it's empty
    }

    @Test
    public void moveDisk() throws InvalidMoveException {
        Tower tower1 = new Tower(0, "1");
        Tower tower2 = new Tower(2, "2");
        Disk disk;

        try {
            disk = tower2.getTop();
            tower1.addDisk(disk);

        } catch (InvalidMoveException i) {
            i.getMessage();
        }

        assertEquals(tower1.getSize(), 1);
        assertEquals(tower2.getSize(), 1);
    }


    @Test(expected = InvalidMoveException.class)
    public void addInvalidDisk() throws InvalidMoveException {
        tower = new Tower(1, "1"); // creates tower with one disk, size = 1
        Disk disk = new Disk("disk", "2");
        tower.addDisk(disk); // throws exception, cannot add disk size = 2 over disk size = 1
    }

    @Test(expected = NumberFormatException.class)
    public void createDiskWithInvalidSize() throws NumberFormatException {
        Disk disk = new Disk("disk", "invalid size");
        disk.getSize();
    }

*/
    //Invalid Command: Error getting top's size
}

