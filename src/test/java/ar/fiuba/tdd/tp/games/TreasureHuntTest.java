package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.treasurehunt.TreasureHunt;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Fede on 26/04/2016.
 */
public class TreasureHuntTest {
    private TreasureHunt target = new TreasureHunt();
/*
    @Test
    public void testRoomOne() {

        this.target.start();

        String response = this.target.play(new Command(new Action("Look Around"), ""));
        assertEquals("Items in the room: door, Box1, Trunk1, Closet1.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Pick"), "Box1"));
        assertEquals("You cant pick up a container", response);

        response = this.target.play(new Command(new Action("Open"), "Box1"));
        assertEquals("The container is opened!", response);

        response = this.target.play(new Command(new Action("Look Around"), ""));
        assertEquals("Items in the room: door, Box1, Trunk1, Closet1.", response);

        response = this.target.play(new Command(new Action("Open"), "Trunk1"));
        assertEquals("The container is opened!", response);

        response = this.target.play(new Command(new Action("Open"), "Box2"));
        assertEquals("The container is opened!", response);

        response = this.target.play(new Command(new Action("Open"), "Closet1"));
        assertEquals("The container is opened!", response);

        response = this.target.play(new Command(new Action("Look Around"), ""));
        assertEquals("Items in the room: door, Key1, Poison1, Antidote1, Box2, Box1, Trunk1, Closet1.", response);

    }
    */
}