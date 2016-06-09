package ar.fiuba.tdd.tp.games.rules;

import ar.fiuba.tdd.tp.games.ConcreteGame;

/**
 * Created by swandelow on 6/8/16.
 */
@SuppressWarnings("CPD-START")
public class VerifyPlayerStateRule extends Rule {

    private ConcreteGame game;
    private String stateName;
    private String stateValue;

    public VerifyPlayerStateRule(ConcreteGame game, String stateName, String stateValue) {
        this.game = game;
        this.stateName = stateName;
        this.stateValue = stateValue;
    }

    @SuppressWarnings("CPD-END")
    @Override
    public boolean doVerify() {
        return new VerifiesStateRule(this.game.getPlayer(), stateName, stateValue).doVerify();
    }
}
