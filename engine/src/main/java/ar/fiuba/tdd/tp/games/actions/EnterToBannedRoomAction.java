package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.rules.Rule;

import java.util.List;

/**
 * Created by Eze on 14/06/2016.
 */
public class EnterToBannedRoomAction implements Action {

    private Rule rule;
    private ConcreteGame game;

    public static final String NOT_ALLOWED = "No puede ingresar";
    public static final String ALLOWED_IN_STATUS = "allowed-in-status";



    public EnterToBannedRoomAction(Rule usedRule, ConcreteGame actualGame) {
        this.rule = usedRule;
        this.game = actualGame;
    }

    @Override
    public void doAction() {
        if (this.rule.doVerify()) {
            Action setStateValueAction = new SetStateValueAction(this.game.getPlayer(), ALLOWED_IN_STATUS, NOT_ALLOWED);
            setStateValueAction.doAction();
        }
    }

}
