package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ConcreteGame;

/**
 * Created by Patri on 09/06/2016.
 */
public class SetPlayerStateValueAction implements Action{

    @SuppressWarnings("CPD-START")

    private ConcreteGame game;
    private String stateName;
    private String newStateValue;

    public SetPlayerStateValueAction(ConcreteGame game, String stateName, String newStateValue) {
        this.game = game;
        this.stateName = stateName;
        this.newStateValue = newStateValue;
    }

    @SuppressWarnings("CPD-END")

    @Override
    public void doAction() {
        Action setStateValueAction = new SetStateValueAction(this.game.getPlayer(), stateName, newStateValue);
        setStateValueAction.doAction();
    }

}
