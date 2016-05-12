package ar.fiuba.tdd.tp.games.items;

import ar.fiuba.tdd.tp.games.Action;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by swandelow on 4/27/16.
 */
public class ItemTest {

    private Item target;

    @Test
    public void testExamineNotSupportedAction() {
        this.target = new Item("item", "it's a test item.");
        assertEquals("it's a test item.", this.target.examine());
    }

    @Test
    public void testExamineWithSupportedAction() {
        this.target = new Item("item1", "it's a test item.");
        this.target.getSupportedActions().put(Action.PICK, "pick item1");
        assertEquals("You can pick: pick item1.", this.target.examine());
    }
}
