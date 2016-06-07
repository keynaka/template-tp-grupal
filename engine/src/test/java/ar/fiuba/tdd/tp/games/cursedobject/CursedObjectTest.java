package ar.fiuba.tdd.tp.games.cursedobject;

import ar.fiuba.tdd.tp.games.CharacterState;
import ar.fiuba.tdd.tp.games.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by swandelow on 4/27/16.
 */
public class CursedObjectTest {

    CursedObject target = new CursedObject();

    @Test
    public void testPick() {
        Player player = new Player();
        assertEquals(CharacterState.HEALTHY, player.getState());
        assertEquals("Now you are cursed!", this.target.pick(player));
        assertEquals(CharacterState.CURSED, player.getState());
    }
}
