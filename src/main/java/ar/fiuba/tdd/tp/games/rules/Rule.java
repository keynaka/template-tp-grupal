package ar.fiuba.tdd.tp.games.rules;


/**
 * Created by Nico on 21/05/2016.
 */
public abstract class Rule {

    public static final Rule TRUE = new TrueRule();
    public static final Rule FALSE = new FalseRule();
    protected boolean negateCondition = false; // Do opposite condition

    protected String failErrorMessage = "Rule not verifies";

    public String getErrorMessage() {
        return failErrorMessage;
    }

    public void setErrorMessage(String msg) {
        failErrorMessage = msg;
    }

    public boolean verify() {
        boolean result = this.doVerify();
        return (this.negateCondition) ? !result : result;
    }

    public Rule or(Rule otherRule) {
        return new OrComplexRule(this, otherRule);
    }

    public Rule and(Rule otherRule) {
        return new AndComplexRule(this, otherRule);
    }

    public abstract boolean doVerify();

    public boolean verifyWith(String parameter) {
        boolean result = this.doVerifyWith(parameter);
        return (this.negateCondition) ? !result : result;
    }

    // implementacion default
    protected boolean doVerifyWith(String parameter) {
        return this.doVerify();
    }

    public Rule negate() {
        this.negateCondition = true;
        return this;
    }
}
