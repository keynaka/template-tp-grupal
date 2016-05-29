package ar.fiuba.tdd.tp.games.rules;

import ar.fiuba.tdd.tp.games.objects.GameObject;

/**
 * Created by swandelow on 5/28/16.
 */
public class VerifiesStateRule extends Rule {

    private GameObject object;
    private String stateName;
    private String stateValue;

    public VerifiesStateRule(GameObject object, String stateName, String stateValue) {
        this.object = object;
        this.stateName = stateName;
        this.stateValue = stateValue;
    }

    @Override
    public boolean doVerify() {
        return this.object.getState(stateName).equalsIgnoreCase(stateValue);
    }
}
