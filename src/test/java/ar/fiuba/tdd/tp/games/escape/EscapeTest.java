package ar.fiuba.tdd.tp.games.escape;

import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by swandelow on 5/19/16.
 */
public class EscapeTest {
    Game target;

    @Test
    public void testPassFromHallToRoom1() {
        target = new EscapeBuilder().build();
        assertEquals("Items in the hall: door1.", target.play(new Command(Action.LOOK_AROUND)));
        assertEquals("Door1 opened.", target.play(new Command(Action.OPEN, "door1")));
        assertEquals("Items in the room1: boatPicture, chair1, chair2, door1, liquor, table, trainPicture.", target.play(new Command(Action.LOOK_AROUND)));
    }

    @Test
    public void testMoveBoatPicture() {
        target = new EscapeBuilder().build();
        assertEquals("Door1 opened.", target.play(new Command(Action.OPEN, "door1")));
        assertEquals("There you go!", target.play(new Command(Action.MOVE, "boatPicture")));
        assertEquals("Items in the room1: boatPicture, chair1, chair2, door1, liquor, securityBox, table, trainPicture.", target.play(new Command(Action.LOOK_AROUND)));
    }

    @Test
    public void testOpenSecurityBoxFail() {
        target = new EscapeBuilder().build();
        assertEquals("Door1 opened.", target.play(new Command(Action.OPEN, "door1")));
        assertEquals("There you go!", target.play(new Command(Action.MOVE, "boatPicture")));
        assertEquals("you can't open the box without key.", target.play(new Command(Action.OPEN, "securityBox")));
    }
}
