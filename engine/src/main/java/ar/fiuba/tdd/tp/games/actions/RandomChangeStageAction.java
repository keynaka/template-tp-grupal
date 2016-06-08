package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.Stage;
import ar.fiuba.tdd.tp.games.items.Item;

import java.util.Random;

/**
 * Created by swandelow on 6/8/16.
 */
public class RandomChangeStageAction implements Action {

    private ConcreteGame game;
    private String itemName;

    public RandomChangeStageAction(ConcreteGame game, String itemName) {
        this.game = game;
        this.itemName = itemName;
    }

    @Override
    public void doAction() {
        Random random = new Random();
        Stage currentStage = this.game.getGameObjectRepository().getCurrentStageFor(this.itemName);

        int i = random.nextInt(currentStage.getConsecutiveStages().size());
        String nextStageName = currentStage.getConsecutiveStages().get(i);
        Stage nextStage = this.game.getStage(nextStageName);

        System.out.println(String.format("%s is now in %s", itemName, nextStageName));

        Item item = currentStage.removeItem(itemName);
        nextStage.addItem(item);
    }
}
