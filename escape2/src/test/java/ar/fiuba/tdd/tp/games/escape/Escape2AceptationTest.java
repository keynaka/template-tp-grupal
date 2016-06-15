package ar.fiuba.tdd.tp.games.escape;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.random.GameRandomMock;
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
        gameBuilder.setGameRandom(new GameRandomMock<>("Biblioteca"));
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
        this.target.setPlayer(playerOne);

        this.gotoBibliotecaAccesoWithLiquor();
        this.target.play(new Command(ActionOld.SHOW, "botellaLicor"));

        this.target.play(new Command(ActionOld.GOTO, "biblioteca"));
        this.target.play(new Command(ActionOld.GOTO, "bibliotecaAcceso"));

        timer.forceTimeInMinutes(2L);
        timer.forceTimeInMinutes(4L);

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

        this.target.setPlayer(playerOne);
        timer.forceTimeInMinutes(2L);
        timer.forceTimeInMinutes(4L);
        Boolean player1Lost = playerOne.hasLost();
        Boolean player2Lost = playerTwo.hasLost();

        assertTrue(player1Lost && !player2Lost);
    }
}
