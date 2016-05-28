package ar.fiuba.tdd.tp.games.rules;

import ar.fiuba.tdd.tp.games.objects.GameObject;

/**
 * Created by Nico on 21/05/2016.
 */
public abstract class Rule {

    protected boolean negateCondition = false; // Do opposite condition

    protected String failErrorMessage = "Rule not verifies";

    public String getErrorMessage() {
        return failErrorMessage;
    }

    public void setErrorMessage(String msg) {
        failErrorMessage = msg;
    }

    public abstract boolean verify();
}
