package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.exceptions.GameException;
import ar.fiuba.tdd.tp.games.hanoitowers.HanoiTowersBuilder;
import ar.fiuba.tdd.tp.games.hanoitowers.Stacker;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Patri on 24/04/2016.
 */
public class HanoiTowerTests {

    private Stacker tower;
    private Game target = new HanoiTowersBuilder().build();

    @Test
    public void testHappyPath() {
        //assertEquals("Welcome to Hanoi Towers!", response);
        String response = this.target.play(new Command(Action.SET_DISKS, "2"));
        assertEquals("You can start playing!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.EXAMINE, "tower1"));
        assertEquals("You can examine: You can check top/move top.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.TOP_SIZE, "tower1"));
        assertEquals("Top size is 1.", response);
        assertFalse(this.target.isFinished());

        moveDisks();

    }


    private void moveDisks() {
        String response = this.target.play(new Command(Action.MOVE_TOP, "tower1", "tower2"));
        assertEquals("Moved!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.MOVE_TOP, "tower1", "tower3"));
        assertEquals("Moved!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.MOVE_TOP, "tower2", "tower3"));
        assertEquals("You won the game!", response);
        assertTrue(this.target.isFinished());
    }

    @Test
    public void setInvalidNumberOfDisks() {
        String response = this.target.play(new Command(Action.SET_DISKS, "nothing"));
        assertEquals("ERROR! Invalid command.", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void invalidExamine() {
        this.target.play(new Command(Action.SET_DISKS, "2"));
        String response = this.target.play(new Command(Action.EXAMINE, "nothing"));
        assertEquals("ERROR! Invalid command.", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void tryToOperateWithHanoiTowersWithoutIndicatingDiskNumber() {
        String response = this.target.play(new Command(Action.EXAMINE, "tower1"));
        assertEquals("ERROR! Game is not initialized!", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void invalidMoveOperation() {
        String response = this.target.start();
        this.target.play(new Command(Action.SET_DISKS, "2"));
        response = this.target.play(new Command(Action.MOVE_TOP, "anything else"));
        assertEquals("ERROR! Invalid command.", response);
        assertFalse(this.target.isFinished());
    }

/*    @Test(expected = GameException.class)
    public void tryToMoveTopWhenOriginTowerIsEmpty() {
        String response = this.target.start();
        this.target.play(new Command(Action.SET_DISKS, "2"));
        this.target.play(new Command(Action.MOVE_TOP, "tower2", "tower3"));
    }*/

/*    @Test(expected = GameException.class)
    public void tryToMoveBiggerDiskOverSmallerDisk() {
        String response = this.target.start();
        this.target.play(new Command(Action.SET_DISKS, "2"));
        response = this.target.play(new Command(Action.MOVE_TOP, "tower1", "tower2"));
        response = this.target.play(new Command(Action.MOVE_TOP, "tower1", "tower2"));
    }*/


    @Test
    public void invalidOriginTower() {
        String response = this.target.start();
        this.target.play(new Command(Action.SET_DISKS, "2"));
        response = this.target.play(new Command(Action.MOVE_TOP, "tower80", "tower2"));
        assertEquals("ERROR! Invalid command.", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void invalidDestinyTower() {
        String response = this.target.start();
        this.target.play(new Command(Action.SET_DISKS, "2"));
        response = this.target.play(new Command(Action.MOVE_TOP, "tower2", "tower80"));
        assertEquals("ERROR! Invalid command.", response);
        assertFalse(this.target.isFinished());
    }

}

