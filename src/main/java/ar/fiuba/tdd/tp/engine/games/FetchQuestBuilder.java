package ar.fiuba.tdd.tp.engine.games;

import ar.fiuba.tdd.tp.engine.core.Game;
import ar.fiuba.tdd.tp.engine.core.GameBuilder;
import ar.fiuba.tdd.tp.engine.core.rules.RuleHasItem;
import ar.fiuba.tdd.tp.engine.models.Stage;
import ar.fiuba.tdd.tp.engine.models.item.Item;
import ar.fiuba.tdd.tp.engine.models.item.ItemFactory;
import ar.fiuba.tdd.tp.engine.models.item.classifications.ItemClassificationType;

/**
 * Created by Nico on 21/05/2016.
 */
public class FetchQuestBuilder extends GameBuilder {
    protected void buildEnvironment() {
        game.setDescription("This is Fetch Quest. Pick the stick to win the game.");
        createStages();
        createItems();
        createPlayer();
        createRules();
    }

    protected void createStages() {
        Stage mainStage = new Stage(Game.MAIN_ROOM);
        mainStage.setOpen();

        stages.add(mainStage);
    }

    protected void createItems() {
        Item stick = ItemFactory.createItemByType(ItemClassificationType.PICKABLE);
        stick.setName("stick");

        getStageById(OpenDoor.MAIN_ROOM).getItemsBag().addItem(stick);
    }

    protected void createRules() {
        RuleHasItem roomHasStick = new RuleHasItem(getStageById(Game.MAIN_ROOM), FetchQuest.ID_STICK);
    }
}
