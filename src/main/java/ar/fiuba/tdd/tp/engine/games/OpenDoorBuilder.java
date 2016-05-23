package ar.fiuba.tdd.tp.engine.games;

import ar.fiuba.tdd.tp.engine.core.CommandExecution;
import ar.fiuba.tdd.tp.engine.core.Game;
import ar.fiuba.tdd.tp.engine.core.GameBuilder;
import ar.fiuba.tdd.tp.engine.core.actions.Action;
import ar.fiuba.tdd.tp.engine.core.actions.ActionDoWithItemClassification;
import ar.fiuba.tdd.tp.engine.core.actions.ActionSwitchItemOwner;
import ar.fiuba.tdd.tp.engine.core.rules.Rule;
import ar.fiuba.tdd.tp.engine.core.rules.RuleHasItem;
import ar.fiuba.tdd.tp.engine.core.rules.RuleItemIsOpen;
import ar.fiuba.tdd.tp.engine.models.Stage;
import ar.fiuba.tdd.tp.engine.models.item.Item;
import ar.fiuba.tdd.tp.engine.models.item.ItemFactory;
import ar.fiuba.tdd.tp.engine.models.item.classifications.ItemClassificationType;

/**
 * Created by Nico on 21/05/2016.
 */
public class OpenDoorBuilder extends GameBuilder {
    private Item key;
    private Item door;
    private Stage mainStage;
    private Rule roomHasKeyRule;
    private Rule doorIsOpen;
    private Rule doorIsClosed;
    private ActionSwitchItemOwner pickKeyAction;
    private Action openDoorAction;

    protected void buildEnvironment() {
        game.setDescription("Welcome to Open Door. Open the door to win the game!");
        createStages();
        createItems();
        createPlayer();
        createRules();
        createActions();
        bindRulesAndActions();
    }

    protected void createStages() {
        mainStage = new Stage(Game.MAIN_ROOM);
        mainStage.setOpen();

        stages.add(mainStage);
    }

    protected void createItems() {
        key = ItemFactory.createKey(OpenDoor.ID_KEY);
        key.setName("key");

        door = ItemFactory.createLockedDoor(key.getId());
        door.setName("door");

        getStageById(Game.MAIN_ROOM).getItemsBag().addItem(door);
        getStageById(Game.MAIN_ROOM).getItemsBag().addItem(key);
    }

    private void createRules() {
        roomHasKeyRule = new RuleHasItem(mainStage, OpenDoor.ID_KEY);
        doorIsOpen = new RuleItemIsOpen(door);

        doorIsClosed = new RuleItemIsOpen(door);
        doorIsClosed.negateCondition = true;

        game.setConditionToWin(doorIsOpen);
    }

    private void createActions() {
        pickKeyAction = new ActionSwitchItemOwner();
        pickKeyAction.setOldOwner(mainStage);
        pickKeyAction.setNewOwner(player);
        pickKeyAction.setItem(OpenDoor.ID_KEY);

        openDoorAction = new ActionDoWithItemClassification(door, key, ItemClassificationType.OPENABLE);
    }

    private void bindRulesAndActions() {
        CommandExecution pickKeyCommand = new CommandExecution(OpenDoor.PICK, OpenDoor.PICK_SUCCESS_MESSAGE);
        pickKeyCommand.setRule(roomHasKeyRule);
        pickKeyCommand.setAction(pickKeyAction);
        key.addCommand(pickKeyCommand);


        CommandExecution openDoor = new CommandExecution(OpenDoor.OPEN, OpenDoor.OPEN_SUCCESS_MESSAGE);
        openDoor.setRule(doorIsClosed);
        openDoor.setAction(openDoorAction);
        door.addCommand(openDoor);
    }

    protected void setKnownCommands() {
        this.knownCommands.add(OpenDoor.OPEN);
        this.knownCommands.add(OpenDoor.PICK);
    }
}
