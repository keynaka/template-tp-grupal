package ar.fiuba.tdd.tp.engine.games;

import ar.fiuba.tdd.tp.engine.core.CommandExecution;
import ar.fiuba.tdd.tp.engine.core.Game;
import ar.fiuba.tdd.tp.engine.core.GameBuilder;
import ar.fiuba.tdd.tp.engine.core.actions.Action;
import ar.fiuba.tdd.tp.engine.core.actions.ActionDoItemClassification;
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
 * Created by Nico on 23/05/2016.
 */
public class OpenDoor2Builder extends GameBuilder {
    private Item box;
    private Item key;
    private Item door;
    private Stage mainStage;
    private Rule boxIsClosed;
    private Rule roomHasKeyRule;
    private Rule doorIsOpen;
    private Rule doorIsClosed;
    private Action openBoxAction;
    private ActionSwitchItemOwner giveKeyToStageAction;
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
        try {
            box = ItemFactory.createItemByType(ItemClassificationType.OPENABLE);
            box.setName("box");

            key = ItemFactory.createKey(OpenDoor2.ID_KEY);
            key.setName("key");
            box.getItemsBag().addItem(key);

            door = ItemFactory.createLockedDoor(key.getId());
            door.setName("door");

            getStageById(Game.MAIN_ROOM).getItemsBag().addItem(box);
            getStageById(Game.MAIN_ROOM).getItemsBag().addItem(door);
        } catch (Exception e) {
            System.out.print("Error: " + e + "\n");
        }
    }

    private void createRules() {
        boxIsClosed = new RuleItemIsOpen(box);
        boxIsClosed.negateCondition = true;

        roomHasKeyRule = new RuleHasItem(mainStage, OpenDoor2.ID_KEY);
        doorIsOpen = new RuleItemIsOpen(door);

        doorIsClosed = new RuleItemIsOpen(door);
        doorIsClosed.negateCondition = true;

        game.setConditionToWin(doorIsOpen);
    }

    private void createActions() {
        openBoxAction = new ActionDoItemClassification(box, ItemClassificationType.OPENABLE);
        giveKeyToStageAction = new ActionSwitchItemOwner();
        giveKeyToStageAction.setOldOwner(box);
        giveKeyToStageAction.setNewOwner(mainStage);
        giveKeyToStageAction.setItem(OpenDoor2.ID_KEY);

        pickKeyAction = new ActionSwitchItemOwner();
        pickKeyAction.setOldOwner(mainStage);
        pickKeyAction.setNewOwner(player);
        pickKeyAction.setItem(OpenDoor2.ID_KEY);

        openDoorAction = new ActionDoWithItemClassification(door, key, ItemClassificationType.OPENABLE);
    }

    private void bindRulesAndActions() {
        CommandExecution openBoxCommand = new CommandExecution(OpenDoor2.OPEN, OpenDoor2.OPEN_BOX_SUCCESS_MESSAGE);
        openBoxCommand.setRule(boxIsClosed);
        openBoxCommand.addAction(openBoxAction);
        openBoxCommand.addAction(giveKeyToStageAction);

        CommandExecution pickKeyCommand = new CommandExecution(OpenDoor2.PICK, OpenDoor2.PICK_SUCCESS_MESSAGE);
        pickKeyCommand.setRule(roomHasKeyRule);
        pickKeyCommand.addAction(pickKeyAction);
        key.addCommand(pickKeyCommand);

        CommandExecution openDoor = new CommandExecution(OpenDoor2.OPEN, OpenDoor2.OPEN_DOOR_SUCCESS_MESSAGE);
        openDoor.setRule(doorIsClosed);
        openDoor.addAction(openDoorAction);
        door.addCommand(openDoor);
    }

    protected void setKnownCommands() {
        this.knownCommands.add(OpenDoor2.OPEN);
        this.knownCommands.add(OpenDoor2.PICK);
    }
}
