package ar.fiuba.tdd.tp.engine.core.rules;

/**
 * Created by Nico on 21/05/2016.
 */
public abstract class Rule {
    public boolean negateCondition = false; // Do opposite condition
    protected String failErrorMessage = "Rule not verifies";

    public String getErrorMessage() {
        return failErrorMessage;
    }

    public void setErrorMessage(String msg) {
        failErrorMessage = msg;
    }

    public abstract boolean verify();
}
