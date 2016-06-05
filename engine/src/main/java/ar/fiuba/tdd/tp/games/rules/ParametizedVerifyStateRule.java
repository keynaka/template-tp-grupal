package ar.fiuba.tdd.tp.games.rules;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.objects.GameObject;

/**
 * Created by swandelow on 6/1/16.
 */
public class ParametizedVerifyStateRule extends Rule {

    private ConcreteGame game;
    private String stateName;
    private String stateValue;

    public ParametizedVerifyStateRule(ConcreteGame game, String stateName, String stateValue) {
        this.game = game;
        this.stateName = stateName;
        this.stateValue = stateValue;
    }

    @Override
    protected boolean doVerifyWith(String parameter) {
        GameObject gameObject = this.game.getGameObjectRepository().getGameObject(parameter);
        return new VerifiesStateRule(gameObject, stateName, stateValue).doVerify();
    }

    @Override
    public boolean doVerify() {
        return false;
    }
}
