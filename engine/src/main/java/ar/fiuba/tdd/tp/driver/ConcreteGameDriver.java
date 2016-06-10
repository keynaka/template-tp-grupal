package ar.fiuba.tdd.tp.driver;

import ar.fiuba.tdd.tp.games.CommandInterpreter;
import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.games.GameBuilder;
import ar.fiuba.tdd.tp.games.escape.EscapeBuilder;
import ar.fiuba.tdd.tp.games.escape.EscapeBuilder2;
import ar.fiuba.tdd.tp.games.fetchquest.FetchQuestBuilder;
import ar.fiuba.tdd.tp.games.woolfsheepcabbage.WolfSheepCabbageBuilder2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fede on 19/05/2016.
 */
public class ConcreteGameDriver implements GameDriver {
    private Game target;
    private CommandInterpreter interpreter = new CommandInterpreter();
    private Map<String, GameBuilder> builders = new HashMap<>();

    {
        builders.put("FetchQuest", new FetchQuestBuilder());
        builders.put("escape", new EscapeBuilder());
        builders.put("escape2", new EscapeBuilder2());
        builders.put("wolf2", new WolfSheepCabbageBuilder2());
    }

    @Override
    public void initGame(String gameName) {
        //TODO cambiar cuando tengamos la separacion en jars
        this.target = this.getBuilder(gameName).build();
    }

    private GameBuilder getBuilder(String gameName) {
        return this.builders.get(gameName);
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
