package ar.fiuba.tdd.tp.games.rules;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.ItemKeeper;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by Fede on 05/06/2016.
 */
public class NotBothItemsRule extends Rule {

    private ConcreteGame game;
    private String item1;
    private String item2;

    public NotBothItemsRule(ConcreteGame game, String item1, String item2) {
        this.game = game;
        this.item1 = item1;
        this.item2 = item2;
    }

    @SuppressWarnings("CPD-END")
    @Override
    public boolean doVerify() {
        return !(game.getCurrentStage().getItemContainer().contains(item1) && game.getCurrentStage().getItemContainer().contains(item2));
    }
}