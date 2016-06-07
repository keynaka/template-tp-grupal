package ar.fiuba.tdd.tp.games.rules;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.objects.GameObject;

/**
 * Created by swandelow on 5/31/16.
 */
public class IsInCurrentRoomRule extends Rule {

    private ConcreteGame game;
    private String itemName;

    public IsInCurrentRoomRule(ConcreteGame game, String itemName) {
        this.game = game;
        this.itemName = itemName;
    }

    @Override
    public boolean doVerify() {
        Item item = this.game.getGameObjectRepository().getItem(itemName);
        return new HasItemRule(this.game.getCurrentStage(), item).doVerify();
    }
}
