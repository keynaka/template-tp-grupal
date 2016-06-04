package ar.fiuba.tdd.tp.games.wolfsheepcabbage;

import ar.fiuba.tdd.tp.games.ActionOld;
import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.games.woolfsheepcabbage.WolfSheepCabbageBuilder;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Fede on 17/05/2016.
 */
public class WolfSheepCabbageTest {
    private Game target = new WolfSheepCabbageBuilder().build();

    @Test
    public void testHappyPath() {

        this.target.start();

        assertEquals("Ok", this.target.play(new Command(ActionOld.TAKE, "sheep")));

        assertEquals("You have crossed", this.target.play(new Command(ActionOld.CROSS, "north-shore")));

        assertEquals("Ok", this.target.play(new Command(ActionOld.LEAVE, "sheep")));

        assertEquals("You have crossed", this.target.play(new Command(ActionOld.CROSS, "south-shore")));

        assertEquals("Ok", this.target.play(new Command(ActionOld.TAKE, "cabbage")));

        assertEquals("You have crossed", this.target.play(new Command(ActionOld.CROSS, "north-shore")));

        assertEquals("Ok", this.target.play(new Command(ActionOld.LEAVE, "cabbage")));

        assertEquals("Ok", this.target.play(new Command(ActionOld.TAKE, "sheep")));

        assertEquals("You have crossed", this.target.play(new Command(ActionOld.CROSS, "south-shore")));

        assertEquals("Ok", this.target.play(new Command(ActionOld.LEAVE, "sheep")));

        assertEquals("Ok", this.target.play(new Command(ActionOld.TAKE, "wolf")));

        assertEquals("You have crossed", this.target.play(new Command(ActionOld.CROSS, "north-shore")));

        assertEquals("Ok", this.target.play(new Command(ActionOld.LEAVE, "wolf")));

        assertEquals("You have crossed", this.target.play(new Command(ActionOld.CROSS, "south-shore")));

        assertEquals("Ok", this.target.play(new Command(ActionOld.TAKE, "sheep")));

        assertEquals("You have crossed", this.target.play(new Command(ActionOld.CROSS, "north-shore")));

        assertEquals("You won the game!", this.target.play(new Command(ActionOld.LEAVE, "sheep")));
        assertTrue(this.target.isFinished());
    }

    @Test
    public void testInvalidTake() {

        this.target.start();

        String response = this.target.play(new Command(ActionOld.TAKE, ""));
        assertEquals("The shore doesn't have that object", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.TAKE, "apple"));
        assertEquals("The shore doesn't have that object", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.TAKE, "wolf"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.TAKE, "wolf"));
        assertEquals("You can't do that. The boat is full", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.LOOK_AROUND, ""));
        assertEquals("Items in SouthShore: cabbage, sheep.", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void testInvalidCross() {

        this.target.start();

        String response = this.target.play(new Command(ActionOld.CROSS, "east-shore"));
        assertEquals("Unknown location", response);

        response = this.target.play(new Command(ActionOld.CROSS, "south-shore"));
        assertEquals("You are at south-shore!", response);

        response = this.target.play(new Command(ActionOld.CROSS, "north-shore"));
        assertEquals("You can't do that. The sheep will eat the cabbage", response);

        response = this.target.play(new Command(ActionOld.TAKE, "sheep"));
        response = this.target.play(new Command(ActionOld.CROSS, "north-shore"));
        assertEquals("You have crossed", response);

        response = this.target.play(new Command(ActionOld.LEAVE, "sheep"));
        response = this.target.play(new Command(ActionOld.CROSS, "south-shore"));
        assertEquals("You have crossed", response);

        response = this.target.play(new Command(ActionOld.TAKE, "cabbage"));
        response = this.target.play(new Command(ActionOld.CROSS, "north-shore"));
        assertEquals("You have crossed", response);

        response = this.target.play(new Command(ActionOld.LEAVE, "cabbage"));
        response = this.target.play(new Command(ActionOld.CROSS, "south-shore"));
        assertEquals("You can't do that. The sheep will eat the cabbage", response);
    }

    @Test
    public void testInvalidLeave() {

        this.target.start();

        String response = this.target.play(new Command(ActionOld.LEAVE, "wolf"));
        assertEquals("You can't do that. The boat is empty", response);

        response = this.target.play(new Command(ActionOld.LEAVE, ""));
        assertEquals("You can't do that. The boat is empty", response);

        response = this.target.play(new Command(ActionOld.TAKE, "sheep"));
        assertEquals("Ok", response);

        response = this.target.play(new Command(ActionOld.CROSS, "north-shore"));
        assertEquals("You have crossed", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.LEAVE, "wolf"));
        assertEquals("The boat doesn't have that object", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.LEAVE, "sheep"));
        assertEquals("Ok", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.LOOK_AROUND, ""));
        assertEquals("Items in NorthShore: sheep.", response);
    }
}

