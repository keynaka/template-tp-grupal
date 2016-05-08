package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.woolfsheepcabbage.WolfSheepCabbage;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Fede on 07/05/2016.
 */
public class WolfSheepCabbageTest {
    private WolfSheepCabbage target = new WolfSheepCabbage();

    @Test
    public void testHappyPath() {

        this.target.start();

        assertEquals("Ok", this.target.play(new Command(Action.TAKE, "sheep")));

        assertEquals("You have crossed", this.target.play(new Command(Action.CROSS, "north-shore")));

        assertEquals("Ok", this.target.play(new Command(Action.LEAVE, "sheep")));

        assertEquals("You have crossed", this.target.play(new Command(Action.CROSS, "south-shore")));

        assertEquals("Ok", this.target.play(new Command(Action.TAKE, "cabbage")));

        assertEquals("You have crossed", this.target.play(new Command(Action.CROSS, "north-shore")));

        assertEquals("Ok", this.target.play(new Command(Action.LEAVE, "cabbage")));

        assertEquals("Ok", this.target.play(new Command(Action.TAKE, "sheep")));

        assertEquals("You have crossed", this.target.play(new Command(Action.CROSS, "south-shore")));

        assertEquals("Ok", this.target.play(new Command(Action.LEAVE, "sheep")));

        assertEquals("Ok", this.target.play(new Command(Action.TAKE, "wolf")));

        assertEquals("You have crossed", this.target.play(new Command(Action.CROSS, "north-shore")));

        assertEquals("Ok", this.target.play(new Command(Action.LEAVE, "wolf")));

        assertEquals("You have crossed", this.target.play(new Command(Action.CROSS, "south-shore")));

        assertEquals("Ok", this.target.play(new Command(Action.TAKE, "sheep")));

        assertEquals("You have crossed", this.target.play(new Command(Action.CROSS, "north-shore")));

        assertEquals("You won the game!", this.target.play(new Command(Action.LEAVE, "sheep")));
        assertTrue(this.target.isFinished());
    }

    @Test
    public void testInvalidTake() {

        this.target.start();

        String response = this.target.play(new Command(Action.TAKE, ""));
        assertEquals("The shore doesn't have that object", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.TAKE, "apple"));
        assertEquals("The shore doesn't have that object", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.TAKE, "wolf"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.TAKE, "wolf"));
        assertEquals("You can't do that. The boat is full", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.LOOK_AROUND, ""));
        assertEquals("Items in the SouthShore: Cabbage, Sheep.", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void testInvalidCross() {

        this.target.start();

        String response = this.target.play(new Command(Action.CROSS, "east-shore"));
        assertEquals("Unknown location", response);

        response = this.target.play(new Command(Action.CROSS, "south-shore"));
        assertEquals("You are at south-shore!", response);

        response = this.target.play(new Command(Action.CROSS, "north-shore"));
        assertEquals("You can't do that. The Sheep will eat the Cabbage", response);

        response = this.target.play(new Command(Action.TAKE, "sheep"));
        response = this.target.play(new Command(Action.CROSS, "north-shore"));
        assertEquals("You have crossed", response);

        response = this.target.play(new Command(Action.LEAVE, "sheep"));
        response = this.target.play(new Command(Action.CROSS, "south-shore"));
        assertEquals("You have crossed", response);

        response = this.target.play(new Command(Action.TAKE, "cabbage"));
        response = this.target.play(new Command(Action.CROSS, "north-shore"));
        assertEquals("You have crossed", response);

        response = this.target.play(new Command(Action.LEAVE, "cabbage"));
        response = this.target.play(new Command(Action.CROSS, "south-shore"));
        assertEquals("You can't do that. The Sheep will eat the Cabbage", response);
    }

    @Test
    public void testInvalidLeave() {

        this.target.start();

        String response = this.target.play(new Command(Action.LEAVE, "wolf"));
        assertEquals("You can't do that. The boat is empty", response);

        response = this.target.play(new Command(Action.LEAVE, ""));
        assertEquals("You can't do that. The boat is empty", response);

        response = this.target.play(new Command(Action.TAKE, "sheep"));
        assertEquals("Ok", response);

        response = this.target.play(new Command(Action.CROSS, "north-shore"));
        assertEquals("You have crossed", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.LEAVE, "wolf"));
        assertEquals("The boat doesn't have that object", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.LEAVE, "sheep"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.LOOK_AROUND, ""));
        assertEquals("Items in the NorthShore: Sheep.", response);
    }
}
