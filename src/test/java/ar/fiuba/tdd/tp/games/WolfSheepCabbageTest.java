package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.woolfsheepcabbage.WolfSheepCabbage;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Fede on 23/04/2016.
 */
public class WolfSheepCabbageTest {
    private WolfSheepCabbage target = new WolfSheepCabbage();

    private Action take = new Action("Take");
    private Action cross = new Action("Cross");
    private Action leave = new Action("Leave");
    private Action lookAround = new Action("Look Around");


    @Test
    public void testHappyPath() {

        this.target.start();

        assertEquals("Ok", this.target.play(new Command(take, "sheep")));

        assertEquals("You have crossed", this.target.play(new Command(cross, "north-shore")));

        assertEquals("Ok", this.target.play(new Command(leave, "sheep")));

        assertEquals("You have crossed", this.target.play(new Command(cross, "south-shore")));

        assertEquals("Ok", this.target.play(new Command(take, "cabbage")));

        assertEquals("You have crossed", this.target.play(new Command(cross, "north-shore")));

        assertEquals("Ok", this.target.play(new Command(leave, "cabbage")));

        assertEquals("Ok", this.target.play(new Command(take, "sheep")));

        assertEquals("You have crossed", this.target.play(new Command(cross, "south-shore")));

        assertEquals("Ok", this.target.play(new Command(leave, "sheep")));

        assertEquals("Ok", this.target.play(new Command(take, "wolf")));

        assertEquals("You have crossed", this.target.play(new Command(cross, "north-shore")));

        assertEquals("Ok", this.target.play(new Command(leave, "wolf")));

        assertEquals("You have crossed", this.target.play(new Command(cross, "south-shore")));

        assertEquals("Ok", this.target.play(new Command(take, "sheep")));

        assertEquals("You have crossed", this.target.play(new Command(cross, "north-shore")));

        assertEquals("You won the game!", this.target.play(new Command(leave, "sheep")));
        assertTrue(this.target.isFinished());
    }

    @Test
    public void testInvalidTake() {

        this.target.start();

        String response = this.target.play(new Command(take, ""));
        assertEquals("The shore doesn't have that object", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(take, "apple"));
        assertEquals("The shore doesn't have that object", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(take, "wolf"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(take, "wolf"));
        assertEquals("You can't do that. The boat is full", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(lookAround, ""));
        assertEquals("You are at: SouthShore and have Sheep Cabbage ", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void testInvalidCross() {

        this.target.start();

        String response = this.target.play(new Command(cross, "east-shore"));
        assertEquals("Unknown location", response);

        response = this.target.play(new Command(cross, "south-shore"));
        assertEquals("You are at south-shore!", response);

        response = this.target.play(new Command(cross, "north-shore"));
        assertEquals("You can't do that. The Wolf will eat the Sheep", response);

        response = this.target.play(new Command(take, "sheep"));
        response = this.target.play(new Command(cross, "north-shore"));
        assertEquals("You have crossed", response);

        response = this.target.play(new Command(leave, "sheep"));
        response = this.target.play(new Command(cross, "south-shore"));
        assertEquals("You have crossed", response);

        response = this.target.play(new Command(take, "cabbage"));
        response = this.target.play(new Command(cross, "north-shore"));
        assertEquals("You have crossed", response);

        response = this.target.play(new Command(leave, "cabbage"));
        response = this.target.play(new Command(cross, "south-shore"));
        assertEquals("You can't do that. The Sheep will eat the Cabbage", response);
    }

    @Test
    public void testInvalidLeave() {

        this.target.start();

        String response = this.target.play(new Command(leave, "wolf"));
        assertEquals("You can't do that. The boat is empty", response);

        response = this.target.play(new Command(leave, ""));
        assertEquals("You can't do that. The boat is empty", response);

        response = this.target.play(new Command(take, "sheep"));
        assertEquals("Ok", response);

        response = this.target.play(new Command(cross, "north-shore"));
        assertEquals("You have crossed", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(leave, "wolf"));
        assertEquals("The boat doesn't have that object", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(leave, "sheep"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(lookAround, ""));
        assertEquals("You are at: NorthShore and have Sheep ", response);
    }
}
