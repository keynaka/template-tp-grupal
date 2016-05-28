package ar.fiuba.tdd.tp.games.rules;

import ar.fiuba.tdd.tp.games.objects.GameObject;

/**
 * Created by swandelow on 5/28/16.
 */
public class IsOpenRule extends Rule {

    public static final String OPEN_STATUS_KEY = "open-status";
    public static final String OPENED = "opened";
    public static final String CLOSED = "closed";


    private GameObject gameObject;

    public IsOpenRule(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public boolean verify() {
        return gameObject.getState(OPEN_STATUS_KEY).equalsIgnoreCase(OPENED);
    }
}
