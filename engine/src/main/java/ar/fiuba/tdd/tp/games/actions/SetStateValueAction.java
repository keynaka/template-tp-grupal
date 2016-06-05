package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ActionOld;
import ar.fiuba.tdd.tp.games.objects.GameObject;

/**
 * Created by swandelow on 5/30/16.
 */
public class SetStateValueAction implements Action {

    private GameObject gameObject;
    private String stateName;
    private String newStateValue;

    public SetStateValueAction(GameObject gameObject, String stateName, String newStateValue) {
        this.gameObject = gameObject;
        this.stateName = stateName;
        this.newStateValue = newStateValue;
    }

    @Override
    public void doAction() {
        this.gameObject.addState(stateName, newStateValue);
    }
}
