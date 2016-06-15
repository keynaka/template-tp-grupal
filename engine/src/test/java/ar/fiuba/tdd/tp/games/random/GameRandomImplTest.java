package ar.fiuba.tdd.tp.games.random;

import ar.fiuba.tdd.tp.games.Stage;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by sebass on 14/06/16.
 */
public class GameRandomImplTest {

    @Test
    public void getRandomFrom() {
        GameRandom<Stage> random = new GameRandomImpl();

        Stage stage1 = new Stage("room1");
        Stage stage2 = new Stage("room2");
        Stage stage3 = new Stage("room3");
        Stage stage4 = new Stage("room4");

        List<Stage> stages = Arrays.asList(stage1, stage2, stage3, stage4);

        Stage randomStage = random.getRandomFrom(stages);
        assertTrue(stages.contains(randomStage));
    }
}
