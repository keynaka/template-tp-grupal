package ar.fiuba.tdd.tp.engine.games;


import ar.fiuba.tdd.tp.engine.core.CommandExecution;
import ar.fiuba.tdd.tp.engine.core.Game;
import ar.fiuba.tdd.tp.engine.core.GameBuilder;
import ar.fiuba.tdd.tp.engine.core.actions.*;
import ar.fiuba.tdd.tp.engine.core.rules.Rule;
import ar.fiuba.tdd.tp.engine.core.rules.RuleHasItem;
import ar.fiuba.tdd.tp.engine.core.rules.RuleHasItems;
import ar.fiuba.tdd.tp.engine.core.rules.RuleItemIsOpen;
import ar.fiuba.tdd.tp.engine.models.Stage;
import ar.fiuba.tdd.tp.engine.models.item.Item;
import ar.fiuba.tdd.tp.engine.models.item.ItemFactory;
import ar.fiuba.tdd.tp.engine.models.item.classifications.ItemClassificationType;

import java.util.*;

/**
 * Created by Nico on 24/05/2016.
 */
public class CursedObjectBuilder extends GameBuilder {

    private Stage mainStage;
    private Stage stage2;
    private Item cursedObject;
    private Item cursedDoor;
    private Item thieve;
    private Item door;
    private Map<Integer, Item> insignificantItems;
    private Map<Integer, Rule> insignificantItemsRules;
    private Map<Integer, Action> insignificantItemsActions;
    private Rule playerHasCursedObject;
    private Rule playerDontHasCursedObject;
    private Rule playerHasItems;
    private ActionSwitchItemOwner pickCursedObjectAction;
    private Action openCursedDoorAction;
    private Action gotoSecondRoomAction;
    private Action thieveStealsItemToPlayerAction;
    private Action openDoorAction;

    protected void buildEnvironment() {
        game.setGameName(CursedObject.GAME_NAME);
        game.setDescription(CursedObject.GAME_DESCRIPTION);
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

        stage2 = new Stage(CursedObject.STAGE2);
        mainStage.addAdjacentStage(stage2);
        stages.add(stage2);
    }

    private void createItems() {
        try {
            cursedObject = ItemFactory.createKey(CursedObject.CURSED_OBJ_ID);
            cursedObject.setName(CursedObject.CURSED_OBJ_NAME);
            mainStage.getItemsBag().addItem(cursedObject);

            cursedDoor = ItemFactory.createLockedDoor(CursedObject.CURSED_DOOR_ID);
            cursedDoor.setName(CursedObject.CURSED_DOOR_NAME);
            mainStage.getItemsBag().addItem(cursedDoor);

            ArrayList<String> itemNames = new ArrayList<>(Arrays.asList("lapiz", "silla", "buzo", "foto", "caja",
                                                                        "cuadro", "desodorante", "peine", "diamante"));
            insignificantItems = new HashMap<>();
            for (String name : itemNames) {
                Item item = ItemFactory.createItemByType(ItemClassificationType.PICKABLE);
                item.setName(name);
                mainStage.getItemsBag().addItem(item);
                insignificantItems.put(item.getId(), item);
            }

            thieve = new Item();
            thieve.setName("thieve");
            stage2.getItemsBag().addItem(thieve);

            door = ItemFactory.createItemByType(ItemClassificationType.OPENABLE);
            door.setName("door");
            stage2.getItemsBag().addItem(door);

        } catch (Exception e) {
            System.out.print("Error: " + e + "\n");
        }
    }

    private void createRules() {
        insignificantItemsRules = new HashMap<>();
        Iterator it = insignificantItems.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Item item = (Item)pair.getValue();

            insignificantItemsRules.put(item.getId(), new RuleHasItem(mainStage, item));
        }

        playerHasCursedObject = new RuleHasItem(player, CursedObject.CURSED_OBJ_ID);
        playerHasCursedObject.setErrorMessage("Player doesn't have the cursed object");

        playerDontHasCursedObject = new RuleHasItem(player, CursedObject.CURSED_OBJ_ID);
        playerDontHasCursedObject.negateCondition = true;
        playerDontHasCursedObject.setErrorMessage("Player has the cursed object");

        playerHasItems = new RuleHasItems(player);
        playerHasItems.setErrorMessage("Player doesn't have items");



        Rule doorIsOpened = new RuleItemIsOpen(door);
        doorIsOpened.setErrorMessage(door.getName()+" is closed");

        game.setConditionToWin(doorIsOpened);
    }

    private void createActions() {
        insignificantItemsActions = new HashMap<>();
        Iterator it = insignificantItems.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Item item = (Item)pair.getValue();

            ActionSwitchItemOwner pickAction = new ActionSwitchItemOwner();
            pickAction.setOldOwner(mainStage);
            pickAction.setNewOwner(player);
            pickAction.setItem(item.getId());
            insignificantItemsActions.put(item.getId(), pickAction);
        }

        pickCursedObjectAction = new ActionSwitchItemOwner();
        pickCursedObjectAction.setOldOwner(mainStage);
        pickCursedObjectAction.setNewOwner(player);
        pickCursedObjectAction.setItem(CursedObject.CURSED_OBJ_ID);

        openCursedDoorAction = new ActionDoWithItemClassification(cursedDoor, cursedObject, ItemClassificationType.OPENABLE);

        gotoSecondRoomAction = new ActionChangePlayerStage(player, stage2);

        thieveStealsItemToPlayerAction = new ActionRemoveItem(player);

        openDoorAction = new ActionDoItemClassification(door, ItemClassificationType.OPENABLE);
    }

    private void bindRulesAndActions() {
        Iterator it = insignificantItems.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Item item = (Item)pair.getValue();

            CommandExecution pickItemCommand = new CommandExecution(CursedObject.PICK, CursedObject.PICK_SUCCESS_MESSAGE);
            pickItemCommand.setRule(insignificantItemsRules.get(item.getId()));
            pickItemCommand.addAction(insignificantItemsActions.get(item.getId()));
            item.addCommand(pickItemCommand);
        }

        CommandExecution pickCursedObjectCommand = new CommandExecution(CursedObject.PICK, CursedObject.PICK_CURSED_OBJ);
        pickCursedObjectCommand.setRule(playerDontHasCursedObject);
        pickCursedObjectCommand.addAction(pickCursedObjectAction);
        cursedObject.addCommand(pickCursedObjectCommand);

        CommandExecution openCursedDoor = new CommandExecution(CursedObject.OPEN, CursedObject.OPEN_CURSED_DOOR);
        openCursedDoor.setRule(playerHasCursedObject);
        openCursedDoor.addAction(openCursedDoorAction);
        openCursedDoor.addAction(gotoSecondRoomAction);
        cursedDoor.addCommand(openCursedDoor);

        CommandExecution talkToThieve = new CommandExecution(CursedObject.TALK, CursedObject.THIEVE_MESSAGE);
        talkToThieve.setRule(playerHasItems);
        talkToThieve.addAction(thieveStealsItemToPlayerAction);
        thieve.addCommand(talkToThieve);

        CommandExecution openDoor = new CommandExecution(CursedObject.OPEN, CursedObject.OPEN_DOOR_SUCCESS_MESSAGE);
        openDoor.setRule(playerDontHasCursedObject);
        openDoor.addAction(openDoorAction);
        door.addCommand(openDoor);
    }

    protected void setKnownCommands() {
        this.knownCommands.add(CursedObject.PICK);
        this.knownCommands.add(CursedObject.OPEN);
        this.knownCommands.add(CursedObject.TALK);
    }
}
