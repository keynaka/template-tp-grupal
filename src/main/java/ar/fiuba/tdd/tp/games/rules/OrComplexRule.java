package ar.fiuba.tdd.tp.games.rules;

/**
 * Created by swandelow on 5/28/16.
 */
public class OrComplexRule extends Rule {

    private Rule rule1;
    private Rule rule2;

    public OrComplexRule(Rule rule1, Rule rule2) {
        this.rule1 = rule1;
        this.rule2 = rule2;
    }

    @Override
    public boolean doVerify() {
        return rule1.verify() && rule2.verify();
    }
}
