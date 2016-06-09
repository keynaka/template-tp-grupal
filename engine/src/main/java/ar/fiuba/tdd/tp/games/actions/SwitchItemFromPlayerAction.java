package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.ItemKeeper;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by Patri on 09/06/2016.
 */
public class SwitchItemFromPlayerAction implements Action {

    @SuppressWarnings("CPD-START")
    private ConcreteGame game;
    private String itemName;
    private ItemKeeper newOwner;

    public SwitchItemFromPlayerAction(ConcreteGame game, ItemKeeper newOwner, String itemName) {
        this.game = game;
        this.newOwner = newOwner;
        this.itemName = itemName;
    }
    @SuppressWarnings("CPD-END")

    @Override
    public void doAction() {
        Action switchItemOwnerAction = new SwitchItemOwnerAction(this.game.getPlayer(), newOwner, itemName);
        switchItemOwnerAction.doAction();
   }


}
