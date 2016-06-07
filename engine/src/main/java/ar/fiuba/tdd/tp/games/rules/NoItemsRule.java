package ar.fiuba.tdd.tp.games.rules;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.ItemKeeper;

/**
 * Created by Fede on 05/06/2016.
 */
public class NoItemsRule extends Rule {

    private ConcreteGame game;

    public NoItemsRule(ConcreteGame game) {
        this.game = game;
    }

    @Override
    public boolean doVerify() {
        return game.getPlayer().getItems().size() == 0;
    }
}
