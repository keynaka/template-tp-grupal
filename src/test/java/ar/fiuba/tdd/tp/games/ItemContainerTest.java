package ar.fiuba.tdd.tp.games;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by swandelow on 4/23/16.
 */
public class ItemContainerTest {

    private ItemContainer target;

    @Before
    public void setUp() {
        this.target = new ItemContainer("container", "it's a container of test.", 3);
    }

    @Test
    public void testExtractOk() {
        this.target.addItem(new Item("item1", "item1 test"));
        Item item = this.target.extract("item1");
        assertEquals("item1", item.getName());
        assertEquals("item1 test", item.getDescription());
        assertTrue(this.target.isEmpty());
    }

    @Test
    public void testExtractAll() {
        this.target.addItem(new Item("item1", "item1 test"));
        this.target.addItem(new Item("item2", "item2 test"));

        assertFalse(this.target.isEmpty());
        Collection<Item> items = this.target.extractAll();

        assertEquals(2, items.size());
        assertTrue(this.target.isEmpty());

    }
}
