package ar.fiuba.tdd.tp.games.opendoor;

import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by swandelow on 4/22/16.
 */
public class OpenDoor2Test {

    private OpenDoor2 target = new OpenDoor2();
/*
    @Test
    public void testHappyPath() {

        String response =  this.testStart();

        response = this.target.play(new Command(new Action("Open"), "box"));
        assertEquals("The box is opened!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Look Around"), ""));
        assertEquals("Items in the room: door, box, key.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Pick"), "key"));
        assertEquals("There you go!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Open"), "door"));
        assertEquals("You enter room 2. You won the game!", response);
        assertTrue(this.target.isFinished());
    }

    private String testStart() {
        String response = this.target.start();
        assertEquals("Welcome to OpenDoor2!", response);
        assertFalse(this.target.isFinished());
        return response;
    }

    @Test
    public void testLookAround() {

        String response =  this.testStart();

        response = this.target.play(new Command(new Action("Look Around"), ""));
        assertEquals("Items in the room: door, box.", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void testOpenBox() {
        String response =  this.testStart();

        response = this.target.play(new Command(new Action("Open"), "door"));
        assertEquals("Ey! Where do you go?! Room 2 is locked.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Examine"), "box"));
        assertEquals("You can open/close the box.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Open"), "box"));
        assertEquals("The box is opened!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Look Around"), ""));
        assertEquals("Items in the room: door, box, key.", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void testInvalidCommand() {

        this.target.start();

        String response = this.target.play(new Command(new Action("Unknow Action"), ""));
        assertEquals("Unknown command.", response);
        assertFalse(this.target.isFinished());

    }
    */
}
