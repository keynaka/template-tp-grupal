package ar.fiuba.tdd.tp.games.escape;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.timer.GameTimerMock;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
/**
 * Created by sebass on 14/06/16.
 */
public class Escape2AceptationTest {

    private Game target = null;
    private GameTimerMock timer = null;
    private Player playerOne = null;
    private Player playerTwo = null;

    private void initializeGame() {
        AbstractGameBuilder gameBuilder = new EscapeBuilder2();
        timer = new GameTimerMock();
        gameBuilder.setTimer(timer);
        target = gameBuilder.build();
    }

    private Player addPlayerToGame(Integer numberPlayer) {
        PlayerManager playerManager = target.getPlayerManager();
        playerManager.addNewPlayer(numberPlayer);
        return playerManager.getPlayer(numberPlayer);
    }

    private void gotoBibliotecaAccesoWithLiquor() {
        this.target.play(new Command(ActionOld.GOTO, "salon1"));
        this.target.play(new Command(ActionOld.PICK, "botellaLicor"));
        this.target.play(new Command(ActionOld.GOTO, "pasillo"));
        this.target.play(new Command(ActionOld.GOTO, "bibliotecaAcceso"));
    }

    @Test
    public void testLostByShowingLiquorToLibrary() {

        this.initializeGame();
        playerOne = this.addPlayerToGame(1);

        this.gotoBibliotecaAccesoWithLiquor();
        this.target.play(new Command(ActionOld.SHOW, "botellaLicor"));
        timer.forceTimeInMinutes(2L);
        this.target.play(new Command(ActionOld.GOTO, "biblioteca"));
        this.target.play(new Command(ActionOld.GOTO, "bibliotecaAcceso"));

        assertTrue(playerOne.hasLost());
    }

    @Test
    public void testLostByShowingLiquorToLibraryWithTwoPlayers() {

        this.initializeGame();
        playerOne = this.addPlayerToGame(1);
        playerTwo = this.addPlayerToGame(2);

        this.target.setPlayer(playerOne);
        this.gotoBibliotecaAccesoWithLiquor();

        this.target.play(new Command(ActionOld.SHOW, "botellaLicor"));
        this.target.play(new Command(ActionOld.GOTO, "biblioteca"));

        this.target.setPlayer(playerTwo);
        this.target.play(new Command(ActionOld.GOTO, "bibliotecaAcceso"));
        this.target.play(new Command(ActionOld.GOTO, "biblioteca"));

        this.target.play(new Command(ActionOld.GOTO, "bibliotecaAcceso"));
        this.target.play(new Command(ActionOld.GOTO, "pasillo"));

        timer.forceTimeInMinutes(2L);
        System.out.println(playerTwo.getState("allowed-in-status"));
        //assertTrue((playerOne.hasLost() && !playerTwo.hasLost()) || (!playerOne.hasLost() && playerTwo.hasLost()));
        assertTrue(playerOne.hasLost() || playerTwo.hasLost());
    }
}
