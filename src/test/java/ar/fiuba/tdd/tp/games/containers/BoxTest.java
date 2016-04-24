package ar.fiuba.tdd.tp.games.containers;

import ar.fiuba.tdd.tp.games.exceptions.GameException;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.Box;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by swandelow on 4/24/16.
 */
public class BoxTest {

    @Test
    public void testCreateEmptyAndClosedBox() {
        Box box = Box.createEmptyAndClosed(1);
        assertTrue(box.isClosed());
        assertTrue(box.isEmpty());
    }

    @Test
    public void testCreateWithItemsAndClosedBox() {
        Box box = Box.createWithItemsAndClosed(1, new Item("item", "item test"));
        assertTrue(box.isClosed());
        assertTrue(box.isFull());
        assertFalse(box.isEmpty());
    }

    @Test
    public void testExtractClosedBoxThrowsGameException() {
        Box box = Box.createWithItemsAndClosed(1, new Item("item", "item test"));
        try {
            box.extract("item");
            fail("Should throw an exception.");
        } catch (GameException e) {
            assertEquals("box it's closed. You can't extract items.", e.getMessage());
        }
    }

    @Test
    public void testExtractClosedBoxOk() {
        Box box = Box.createWithItemsAndClosed(1, new Item("item", "item test"));
        box.open();
        Item item = box.extract("item");
        assertTrue(box.isEmpty());
        assertEquals("item", item.getName());
        assertEquals("item test", item.getDescription());
    }

    @Test
    public void testOpen() {
        Box box = Box.createEmptyAndClosed(2);
        String result = box.open();
        assertEquals("Open box.", result);
        result = box.open();
        assertEquals("box it's already open.", result);
    }

    @Test
    public void testClose() {
        Box box = new Box(3);
        String result = box.close();
        assertEquals("Closed box.", result);
        result = box.close();
        assertEquals("box it's already closed.", result);
    }
}
