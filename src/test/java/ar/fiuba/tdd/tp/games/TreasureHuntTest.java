package ar.fiuba.tdd.tp.games;

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
        this.room1();
        this.room2();
        this.room3();
        this.room4();
        this.room5();
    }

    public void room1() {
        String response = this.target.play(new Command(ActionOld.LOOK_AROUND));
        assertEquals("Items in the room1: door1, key1.", response);
        assertFalse(this.target.isFinished());

        this.target.play(new Command(ActionOld.PICK, "key1"));
        response = this.target.play(new Command(ActionOld.OPEN, "door1"));
    }

    public void room2() {
        this.target.play(new Command(ActionOld.PICK, "key2"));
        this.target.play(new Command(ActionOld.OPEN, "door2"));
    }

    public void room3() {
        this.target.play(new Command(ActionOld.DROP, "key1"));
        this.target.play(new Command(ActionOld.DROP, "key2"));

        String response = this.target.play(new Command(ActionOld.LOOK_AROUND));
        assertEquals("Items in the room3: baul, box, door3.", response);

        response = this.target.play(new Command(ActionOld.OPEN, "baul"));
        response = this.target.play(new Command(ActionOld.LOOK_AROUND));
        assertEquals("Items in the room3: baul, box, box2, door3, poison.", response);

        response = this.target.play(new Command(ActionOld.OPEN, "box2"));
        response = this.target.play(new Command(ActionOld.LOOK_AROUND));
        assertEquals("Items in the room3: antidoto, baul, box, box2, door3, poison.", response);

        response = this.target.play(new Command(ActionOld.OPEN, "box"));
        assertEquals("Box opened.", response);

        response = this.target.play(new Command(ActionOld.PICK, "key3"));
        this.target.play(new Command(ActionOld.OPEN, "door3"));
    }

    public void room4() {
        this.target.play(new Command(ActionOld.DROP, "key3"));

        String response = this.target.play(new Command(ActionOld.LOOK_AROUND));
        assertEquals("Items in the room4: armario, door4.", response);

        this.target.play(new Command(ActionOld.OPEN, "armario"));

        response = this.target.play(new Command(ActionOld.LOOK_AROUND));
        assertEquals("Items in the room4: antidoto, armario, door4, key4, poison.", response);

        this.target.play(new Command(ActionOld.PICK, "poison"));
        this.target.play(new Command(ActionOld.PICK, "antidoto"));

        this.target.play(new Command(ActionOld.PICK, "key4"));

        this.target.play(new Command(ActionOld.OPEN, "door4"));
    }

    public void room5() {
        String response = this.target.play(new Command(ActionOld.PICK, "treasure"));

        assertEquals("You won the game!", response);
    }

    @Test
    public void testInvalidCommand() {

        this.target.start();

        String response = this.target.play(new Command(ActionOld.UNKNOWN_ACTION, ""));
        assertEquals("Unknown command.", response);
        assertFalse(this.target.isFinished());

    }
}