package ar.fiuba.tdd.tp.engine.games;

import ar.fiuba.tdd.tp.engine.core.CommandExecution;
import ar.fiuba.tdd.tp.engine.core.Game;
import ar.fiuba.tdd.tp.engine.core.GameBuilder;
import ar.fiuba.tdd.tp.engine.core.actions.ActionSwitchItemOwner;
import ar.fiuba.tdd.tp.engine.core.rules.RuleHasItem;
import ar.fiuba.tdd.tp.engine.models.Stage;
import ar.fiuba.tdd.tp.engine.models.item.Item;
import ar.fiuba.tdd.tp.engine.models.item.ItemFactory;
import ar.fiuba.tdd.tp.engine.models.item.classifications.ItemClassificationType;

/**
 * Created by Nico on 21/05/2016.
 */
public class FetchQuestBuilder extends GameBuilder {
    private Item stick;
    private Stage mainStage;
    private RuleHasItem roomHasStickRule;
    private ActionSwitchItemOwner pickStickAction;

    protected void buildEnvironment() {
        game.setGameName(FetchQuest.GAME_NAME);
        game.setDescription(FetchQuest.GAME_DESCRIPTION);
        createStages();
        createItems();
        createPlayer();
        createRules();
        createActions();
        bindRulesAndActions();
    }

    private void createStages() {
        mainStage = new Stage(Game.MAIN_ROOM);
        mainStage.setOpen();

        stages.add(mainStage);
    }

    private void createItems() {
        try {
            stick = ItemFactory.createItemByType(ItemClassificationType.PICKABLE);
            //SingleActionItem PickableActionItem = new Pickable(true);
            //Item stick = new Item();
            //stick.addItemClassification(ItemClassificationType.PICKABLE, PickableActionItem);
            stick.setId(FetchQuest.ID_STICK);
            stick.setName("stick");

            mainStage.getItemsBag().addItem(stick);
        } catch (Exception e) {
            System.out.print("Error: " + e + "\n");
        }
    }

    private void createRules() {
        roomHasStickRule = new RuleHasItem(mainStage, FetchQuest.ID_STICK);
        RuleHasItem conditionToWin = new RuleHasItem(player, FetchQuest.ID_STICK);
        game.setConditionToWin(conditionToWin);
    }

    private void createActions() {
        pickStickAction = new ActionSwitchItemOwner();
        pickStickAction.setOldOwner(mainStage);
        pickStickAction.setNewOwner(player);
        pickStickAction.setItem(FetchQuest.ID_STICK);
    }

    private void bindRulesAndActions() {
        CommandExecution pickStickCommand = new CommandExecution(FetchQuest.PICK);
        pickStickCommand.setRule(roomHasStickRule);
        pickStickCommand.setAction(pickStickAction);

        stick.addCommand(pickStickCommand);
    }

    protected void setKnownCommands() {
        this.knownCommands.add(FetchQuest.PICK);
    }
}
