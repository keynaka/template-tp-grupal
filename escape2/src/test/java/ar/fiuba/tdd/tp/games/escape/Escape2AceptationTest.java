package ar.fiuba.tdd.tp.games.escape;

import ar.fiuba.tdd.tp.driver.GameState;
import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.timer.GameTimerInterface;
import ar.fiuba.tdd.tp.games.timer.GameTimerMock;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by sebass on 14/06/16.
 */
public class Escape2AceptationTest {

    private Game target = null;
    private GameTimerMock timer = new GameTimerMock();
    private Player playerOne = null;
    private Player playerTwo = null;

    private void initializeGame() {
        AbstractGameBuilder gameBuilder = new EscapeBuilder2();
        gameBuilder.setTimer(timer);
        target = gameBuilder.build();
    }

    private void addPlayerToGame(Integer numberPlayer) {
        PlayerManager playerManager = target.getPlayerManager();
        playerManager.addNewPlayer(numberPlayer);
        Player player = playerManager.getPlayer(numberPlayer);
        target.setPlayer(player);
    }

    @Test
    public void testLostByShowingLiquorToLibrary() {

        this.initializeGame();
        this.addPlayerToGame(1);

        this.target.play(new Command(ActionOld.GOTO, "salon1"));
        this.target.play(new Command(ActionOld.PICK, "botellaLicor"));
        this.target.play(new Command(ActionOld.GOTO, "pasillo"));
        this.target.play(new Command(ActionOld.GOTO, "bibliotecaAcceso"));
        this.target.play(new Command(ActionOld.SHOW, "botellaLicor"));
        timer.forceTimeInMinutes(2L);
        this.target.play(new Command(ActionOld.GOTO, "biblioteca"));
        this.target.play(new Command(ActionOld.GOTO, "bibliotecaAcceso"));

        playerOne = target.getPlayerManager().getPlayer(1);
        //assertTrue(playerOne.hasLost());
    }
}
