package ar.fiuba.tdd.tp.driver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Fede on 19/05/2016.
 */
public class FetchQuestDriverTest {

    @Test
    public void testHappyPath() {

        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("FetchQuest");

        String response = driver.sendCommand("look around");
        assertEquals("Items in the room: stick.", response);
        assertTrue(GameState.InProgress == driver.getCurrentState());

        response = driver.sendCommand("pick stick");
        assertEquals("You won the game!", response);
        //assertTrue(GameState.Won == driver.getCurrentState());
    }

    @Test
    public void testInvalidCommand() {

        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("FetchQuest");

        String response = driver.sendCommand("");
        assertEquals("Unknown command.", response);
        assertTrue(GameState.InProgress == driver.getCurrentState());

    }
}
