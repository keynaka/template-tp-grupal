package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.treasurehunt.TreasureHunt;
import ar.fiuba.tdd.tp.games.treasurehunt.TreasureHuntBuilder;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TreasureHuntTest {
    private Game target = new TreasureHuntBuilder().build();

    @Test
    public void testHappyPath() {

        String response = this.target.play(new Command(Action.LOOK_AROUND));
        assertEquals("Items in the room1: door1, key1.", response);
        assertFalse(this.target.isFinished());

        this.target.play(new Command(Action.PICK, "key1"));
        response = this.target.play(new Command(Action.OPEN, "door1"));
        assertEquals("Door1 opened.", response);

        this.target.play(new Command(Action.PICK, "key2"));
        this.target.play(new Command(Action.OPEN, "door2"));
        this.target.play(new Command(Action.DROP, "key1"));

        response = this.target.play(new Command(Action.LOOK_AROUND));
        assertEquals("Items in the room3: box, door3.", response);

        response = this.target.play(new Command(Action.OPEN, "box"));
        assertEquals("Box opened.", response);
        response = this.target.play(new Command(Action.PICK, "key3"));
        this.target.play(new Command(Action.OPEN, "door3"));

        this.target.play(new Command(Action.DROP, "key2"));
        this.target.play(new Command(Action.DROP, "key3"));
        response = this.target.play(new Command(Action.PICK, "treasure"));
        assertEquals("You won the game!", response);

    }

    @Test
    public void testInvalidCommand() {

        this.target.start();

        String response = this.target.play(new Command(Action.UNKNOWN_ACTION, ""));
        assertEquals("Unknown command.", response);
        assertFalse(this.target.isFinished());

    }
}