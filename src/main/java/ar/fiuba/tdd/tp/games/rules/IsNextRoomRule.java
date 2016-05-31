package ar.fiuba.tdd.tp.games.rules;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.Stage;

/**
 * Created by swandelow on 5/31/16.
 */
public class IsNextRoomRule extends Rule {

    private ConcreteGame game;
    private String nextStageName;

    public IsNextRoomRule(ConcreteGame game, String nextStageName) {
        this.game = game;
        this.nextStageName = nextStageName;
    }

    @Override
    public boolean doVerify() {
        return this.game.getCurrentStage().getConsecutiveStages().contains(nextStageName);
    }
}
