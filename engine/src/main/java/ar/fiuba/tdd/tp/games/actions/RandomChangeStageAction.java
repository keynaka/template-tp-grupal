package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.Stage;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.random.GameRandom;
import ar.fiuba.tdd.tp.games.random.GameRandomImpl;

import java.util.ArrayList;
import java.util.List;

import static ar.fiuba.tdd.tp.games.escape.EscapeProperties.ALLOWED_IN_LIBRARY_STATUS;
import static ar.fiuba.tdd.tp.games.escape.EscapeProperties.NOT_ALLOWED;

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
        //Random random = new Random();
        Stage currentStage = this.game.getGameObjectRepository().getCurrentStageFor(this.itemName);

        //int randomInt = random.nextInt(currentStage.getConsecutiveStages().size());
        //String nextStageName = currentStage.getConsecutiveStages().get(randomInt);
        //Stage nextStage = this.game.getStage(nextStageName);

        GameRandom<Stage> gameRandom = new GameRandomImpl<>();
        Stage nextStage = gameRandom.getRandomFrom(this.getConsecutiveStages());

        System.out.println(String.format("%s is now in %s", itemName, nextStage.getName()));

        Item item = currentStage.removeItem(itemName);
        nextStage.addItem(item);

        boolean isPlayerAndLibrarianInSameRoom = this.game.getPlayer().getCurrentStage().equalsIgnoreCase(nextStage.getName());
        boolean notAllowedInLibrary = this.game.getPlayer().getState(ALLOWED_IN_LIBRARY_STATUS).equalsIgnoreCase(NOT_ALLOWED);

        if (isPlayerAndLibrarianInSameRoom && notAllowedInLibrary) {
            this.game.getPlayer().setHasLost();
            System.out.println(String.format("%s has lost. Librarian has checked invalid id card.", this.game.getPlayer().getName()));
        }
    }

    private List<Stage> getConsecutiveStages() {
        List<Stage> consecutiveStages = new ArrayList<>();
        Stage currentStage = this.game.getGameObjectRepository().getCurrentStageFor(this.itemName);
        for (String stageName : currentStage.getConsecutiveStages()) {
            consecutiveStages.add(this.game.getStage(stageName));
        }
        return consecutiveStages;
    }
}
