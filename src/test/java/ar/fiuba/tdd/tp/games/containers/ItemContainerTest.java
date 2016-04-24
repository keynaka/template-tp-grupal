package ar.fiuba.tdd.tp.games.containers;

import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

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

    @Test
    public void testIsFull() {
        this.target.addItem(new Item("item1", "item1 test"));
        assertFalse(this.target.isFull());
        this.target.addItem(new Item("item2", "item2 test"));
        assertFalse(this.target.isFull());
        this.target.addItem(new Item("item3", "item3 test"));
        assertTrue(this.target.isFull());
    }

    @Test
    public void testAddItemOk() {
        assertEquals("item1 saved in container.", this.target.addItem(new Item("item1", "item1 test")));
        assertEquals("item2 saved in container.", this.target.addItem(new Item("item2", "item2 test")));
        assertEquals("item3 saved in container.", this.target.addItem(new Item("item3", "item3 test")));
        ;
    }

    @Test
    public void testAddItemWithFullContainer() {
        assertEquals("item1 saved in container.", this.target.addItem(new Item("item1", "item1 test")));
        assertEquals("item2 saved in container.", this.target.addItem(new Item("item2", "item2 test")));
        assertEquals("item3 saved in container.", this.target.addItem(new Item("item3", "item3 test")));
        assertEquals("It's not possible to add item4. Container is full.", this.target.addItem(new Item("item4", "item4 test")));
        ;


    }
}
