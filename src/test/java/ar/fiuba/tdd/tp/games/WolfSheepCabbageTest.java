package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.fetchquest.FetchQuest2;
import ar.fiuba.tdd.tp.games.woolfsheepcabbage.WolfSheepCabbage;
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

        /*response = this.target.play(new Command(Action.PICK, "stick"));
        assertEquals("You won the game!", response);
        assertTrue(this.target.isFinished());*/
    }

    @Test
    public void testInvalidCommand() {

        this.target.start();

        String response = this.target.play(new Command(Action.UNKNOWN_ACTION, ""));
        assertEquals("Unknown command.", response);
        assertFalse(this.target.isFinished());

    }
}
