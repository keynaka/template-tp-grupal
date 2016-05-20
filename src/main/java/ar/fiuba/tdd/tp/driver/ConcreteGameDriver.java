package ar.fiuba.tdd.tp.driver;

import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.games.fetchquest.FetchQuestBuilder;
import ar.fiuba.tdd.tp.red.server.CommandInterpreter;

/**
 * Created by Fede on 19/05/2016.
 */
public class ConcreteGameDriver implements GameDriver {
    private Game target;
    private CommandInterpreter interpreter = new CommandInterpreter();

    @Override
    public void initGame(String gameName) {
        //TODO cambiar cuando tengamos la separacion en jars
        if (gameName.equals("FetchQuest")) {
            target = new FetchQuestBuilder().build();
        }
        /*if (gameName.equals("escape")){
            target = new EscapeBuilder().build();
        }*/
    }

    @Override
    public String sendCommand(String cmd) {
        return this.target.play(interpreter.getCommandForDriver(cmd));
    }

    @Override
    public GameState getCurrentState() {
        //TODO agregar caso Lost
        if (this.target.isFinished()) {
            return GameState.Won;
        } else {
            return GameState.InProgress;
        }
    }
}
