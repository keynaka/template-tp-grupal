package ar.fiuba.tdd.tp.engine.games;

import ar.fiuba.tdd.tp.engine.core.Game;
import ar.fiuba.tdd.tp.engine.core.GameBuilder;
import ar.fiuba.tdd.tp.engine.models.Stage;
import ar.fiuba.tdd.tp.engine.models.item.Item;
import ar.fiuba.tdd.tp.engine.models.item.ItemFactory;

/**
 * Created by Nico on 21/05/2016.
 */
public class OpenDoorBuilder extends GameBuilder {

    protected void buildEnvironment() {
        game.setDescription("Welcome to Open Door. Open the door to win the game!");
        createStages();
        createItems();
        createPlayer();
    }

    protected void createStages() {
        Stage mainStage = new Stage(Game.MAIN_ROOM);
        mainStage.setOpen();

        // If the player gets here, wins the game
        Stage finishStage = new Stage(Game.FINISH_ROOM);

        // The two rooms are connected
        mainStage.addAdjacentStage(finishStage);

        stages.add(mainStage);
        stages.add(finishStage);
    }

    protected void createItems() {
        Item key = ItemFactory.createKey(OpenDoor.ID_KEY);
        Item door = ItemFactory.createLockedDoor(key.getId());

        getStageById(Game.MAIN_ROOM).getItemsBag().addItem(door);
        getStageById(Game.MAIN_ROOM).getItemsBag().addItem(key);
    }

    protected void setKnownCommands() {
        this.knownCommands.add(OpenDoor.OPEN);
        this.knownCommands.add(OpenDoor.PICK);
    }
}
