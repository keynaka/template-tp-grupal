package ar.fiuba.tdd.tp.games.rules;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.objects.GameObject;

/**
 * Created by swandelow on 6/8/16.
 */
public class DynamicVerifiesStateRule extends Rule {

    private ConcreteGame game;
    private String gameObjectName;
    private String stateName;
    private String stateValue;

    public DynamicVerifiesStateRule(ConcreteGame game, String gameObjectName, String stateName, String stateValue) {
        this.game = game;
        this.gameObjectName = gameObjectName;
        this.stateName = stateName;
        this.stateValue = stateValue;
    }

    @Override
    public boolean doVerify() {
        GameObject gameObject = this.game.getGameObjectRepository().getGameObject(gameObjectName);
        return new VerifiesStateRule(gameObject, stateName, stateValue).doVerify();
    }
}
