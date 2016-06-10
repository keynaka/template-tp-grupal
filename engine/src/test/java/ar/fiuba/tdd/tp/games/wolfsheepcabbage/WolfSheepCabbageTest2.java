package ar.fiuba.tdd.tp.games.wolfsheepcabbage;

import ar.fiuba.tdd.tp.driver.ConcreteGameDriver;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.driver.GameState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Fede on 05/06/2016.
 */
//@Ignore
public class WolfSheepCabbageTest2 {

    @Test
    public void testHappyPath() {

        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("wolf2");

        assertEquals("Ok", driver.sendCommand("take sheep"));

        assertEquals("You have crossed", driver.sendCommand("cross north-shore"));

        assertEquals("Ok", driver.sendCommand("leave sheep"));

        assertEquals("You have crossed", driver.sendCommand("cross south-shore"));

        assertEquals("Ok", driver.sendCommand("take cabbage"));

        assertEquals("You have crossed", driver.sendCommand("cross north-shore"));

        assertEquals("Ok", driver.sendCommand("leave cabbage"));

        assertEquals("Ok", driver.sendCommand("take sheep"));

        assertEquals("You have crossed", driver.sendCommand("cross south-shore"));

        assertEquals("Ok", driver.sendCommand("leave sheep"));

        assertEquals("Ok", driver.sendCommand("take wolf"));

        assertEquals("You have crossed", driver.sendCommand("cross north-shore"));

        assertEquals("Ok", driver.sendCommand("leave wolf"));

        assertEquals("You have crossed", driver.sendCommand("cross south-shore"));

        assertEquals("Ok", driver.sendCommand("take sheep"));

        assertEquals("You have crossed", driver.sendCommand("cross north-shore"));

        assertEquals("You won the game!", driver.sendCommand("leave sheep"));
    }

    @Test
    public void testInvalidTake() {

        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("wolf2");


        String response = driver.sendCommand("take ");
        assertEquals("GameObject not found.", response);
        assertTrue(GameState.InProgress == driver.getCurrentState());

        response = driver.sendCommand("take apple");
        assertEquals("GameObject not found.", response);
        assertTrue(GameState.InProgress == driver.getCurrentState());

        response = driver.sendCommand("take wolf");
        assertEquals("Ok", response);
        assertTrue(GameState.InProgress == driver.getCurrentState());

        response = driver.sendCommand("take sheep");
        assertEquals("You can't do that. The boat is full.", response);
        assertTrue(GameState.InProgress == driver.getCurrentState());

        response = driver.sendCommand("take wolf");
        assertEquals("You can't do that. The boat is full.", response);
        assertTrue(GameState.InProgress == driver.getCurrentState());

        response = driver.sendCommand("look around");
        assertEquals("Items in south-shore: cabbage, sheep.", response);
    }

    @Test
    public void testInvalidCross() {

        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("wolf2");

        String response = driver.sendCommand("cross east-shore");
        assertEquals("GameObject not found.", response);

        response = driver.sendCommand("cross north-shore");
        assertEquals("You can't do that. The sheep will eat the cabbage or The wolf will eat the sheep", response);

        driver.sendCommand("take sheep");
        response = driver.sendCommand("cross north-shore");
        assertEquals("You have crossed", response);

        driver.sendCommand("leave sheep");
        response = driver.sendCommand("cross south-shore");
        assertEquals("You have crossed", response);

        driver.sendCommand("take cabbage");
        response = driver.sendCommand("cross north-shore");
        assertEquals("You have crossed", response);

        driver.sendCommand("leave cabbage");
        response = driver.sendCommand("cross south-shore");
        assertEquals("You can't do that. The sheep will eat the cabbage or The wolf will eat the sheep", response);

    }

    @Test
    public void testInvalidLeave() {

        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("wolf2");

        String response = driver.sendCommand("leave wolf");
        assertEquals("You can't do that. The object is not on the boat.", response);

        response = driver.sendCommand("leave ");
        assertEquals("GameObject not found.", response);

        response = driver.sendCommand("take sheep");
        assertEquals("Ok", response);

        response = driver.sendCommand("cross north-shore");
        assertEquals("You have crossed", response);
        assertTrue(GameState.InProgress == driver.getCurrentState());

        response = driver.sendCommand("leave wolf");
        assertEquals("You can't do that. The object is not on the boat.", response);
        assertTrue(GameState.InProgress == driver.getCurrentState());

        response = driver.sendCommand("leave sheep");
        assertEquals("Ok", response);
        assertTrue(GameState.InProgress == driver.getCurrentState());

        response = driver.sendCommand("look around");
        assertEquals("Items in north-shore: sheep.", response);
    }
}
