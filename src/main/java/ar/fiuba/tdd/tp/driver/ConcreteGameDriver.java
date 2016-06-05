package ar.fiuba.tdd.tp.driver;

import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.games.escape.EscapeBuilder;
import ar.fiuba.tdd.tp.games.escape.EscapeBuilder2;
import ar.fiuba.tdd.tp.games.fetchquest.FetchQuestBuilder;
import ar.fiuba.tdd.tp.games.woolfsheepcabbage.WolfSheepCabbageBuilder2;
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
        if (gameName.equalsIgnoreCase("escape")) {
            target = new EscapeBuilder().build();
        }

        if (gameName.equalsIgnoreCase("escape2")) {
            target = new EscapeBuilder2().build();
        }
        if (gameName.equalsIgnoreCase("wolf2")) {
            target = new WolfSheepCabbageBuilder2().build();
        }
    }

    @Override
    public String sendCommand(String cmd) {
        return this.target.play(interpreter.getCommandForDriver(cmd));
    }

    @Override
    public GameState getCurrentState() {
        return target.getGameState();
    }
}
