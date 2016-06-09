package ar.fiuba.tdd.tp.games.rules;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by Patri on 09/06/2016.
 */
public class PlayerHasItemRule extends Rule {

    private ConcreteGame game;
    private Item item;

    public PlayerHasItemRule(ConcreteGame game, Item item) {
        this.game = game;
        this.item = item;
    }

    @Override
    public boolean doVerify() {
        return game.getPlayer().getItems().contains(item);
    }
}
