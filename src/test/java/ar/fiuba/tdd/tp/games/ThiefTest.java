package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.items.Item;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by swandelow on 4/27/16.
 */
public class ThiefTest {

    private Thief target = new Thief();

    @Test
    public void testSteal() {
        Player player = new Player();
        Item item = new Item("item", "An item.");
        player.addToInventory(item);
        assertEquals("The thief has just stolen your object!", this.target.steal(player));
        assertFalse(player.hasItem(item.getName()));
    }

    @Test
    public void testTalk() {
        Player player = new Player();
        assertEquals("Hi!. The thief has just stolen your object!", this.target.talk(player, "Hello"));
        assertEquals("Bye.", this.target.talk(player, "Bye"));
    }
}
