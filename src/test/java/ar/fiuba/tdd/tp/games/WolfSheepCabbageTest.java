package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.woolfsheepcabbage.WolfSheepCabbage;
import ar.fiuba.tdd.tp.red.Command;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Fede on 23/04/2016.
 */
public class WolfSheepCabbageTest {
    private WolfSheepCabbage target = new WolfSheepCabbage();

    @Test
    public void testHappyPath() {

        this.target.start();

        String response = this.target.play(new Command(Action.LOOK_AROUND, ""));
        assertEquals("You are at: SouthShore and have Wolf Sheep Cabbage ", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.TAKE, "sheep"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.CROSS, "north-shore"));
        assertEquals("You have crossed", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.LEAVE, "sheep"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.CROSS, "south-shore"));
        assertEquals("You have crossed", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.TAKE, "cabbage"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.CROSS, "north-shore"));
        assertEquals("You have crossed", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.LEAVE, "cabbage"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.TAKE, "sheep"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.CROSS, "south-shore"));
        assertEquals("You have crossed", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.LEAVE, "sheep"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.TAKE, "wolf"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.CROSS, "north-shore"));
        assertEquals("You have crossed", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.LEAVE, "wolf"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.CROSS, "south-shore"));
        assertEquals("You have crossed", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.TAKE, "sheep"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.CROSS, "north-shore"));
        assertEquals("You have crossed", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.LEAVE, "sheep"));
        assertEquals("You won the game!", response);
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
        assertEquals("You are at: SouthShore and have Sheep Cabbage ", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void testInvalidCross() {

        this.target.start();

        String response = this.target.play(new Command(Action.CROSS, "east-shore"));
        assertEquals("Unknown location", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.CROSS, ""));
        assertEquals("Unknown location", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.CROSS, "south-shore"));
        assertEquals("You are at south-shore!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.CROSS, "north-shore"));
        assertEquals("You can't do that. The Wolf will eat the Sheep", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.TAKE, "sheep"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.CROSS, "north-shore"));
        assertEquals("You have crossed", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.LEAVE, "sheep"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.CROSS, "south-shore"));
        assertEquals("You have crossed", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.TAKE, "cabbage"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.CROSS, "north-shore"));
        assertEquals("You have crossed", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.LEAVE, "cabbage"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.CROSS, "south-shore"));
        assertEquals("You can't do that. The Sheep will eat the Cabbage", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void testInvalidLeave() {

        this.target.start();

        String response = this.target.play(new Command(Action.LEAVE, "wolf"));
        assertEquals("You can't do that. The boat is empty", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.LEAVE, ""));
        assertEquals("You can't do that. The boat is empty", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.TAKE, "sheep"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

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
        assertEquals("You are at: NorthShore and have Sheep ", response);
        assertFalse(this.target.isFinished());
    }
}
