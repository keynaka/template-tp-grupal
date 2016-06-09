package ar.fiuba.tdd.tp.games.fetchquest;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.actions.SwitchItemOwnerAction;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.rules.HasItemRule;

/**
 * Created by sebass on 10/05/16.
 */
public class FetchQuestBuilder extends AbstractGameBuilder {
    private Item stick;
    private Stage mainStage;
    private HasItemRule roomHasStickRule;
    private SwitchItemOwnerAction pickStickAction;

    @Override
    protected void buildEnvironment() {
        game.setName("Fetch Quest");
        game.setEndGameMessage("You won the game!");
        game.setGameDescription("This is Fetch Quest. Pick the stick to win the game.");

        createStages();
        createItems();
        configurePlayer();
        configurePlayerManager();
        createRules();
        createActions();
        bindRulesAndActions();
    }

    private void configurePlayerManager() {
        PlayerCreator playerCreator = new PlayerCreator();
        playerCreator.setInitialRoom("room");
        this.playerManager = new PlayerManager(playerCreator, true, 2);
        this.game.setPlayerManager(this.playerManager);
    }

    private void createStages() {
        mainStage = new Stage("room");
        stages.add(mainStage);
    }

    private void createItems() {
        stick = new Item("stick", "it's a stick.");
        mainStage.addItem(stick);
    }

    @Override
    protected void configurePlayer() {
        player.setCurrentStage(mainStage.getName());
    }

    protected void createRules() {
        roomHasStickRule = new HasItemRule(mainStage, stick);
        HasItemRule conditionToWin = new HasItemRule(player, stick);
        game.setWinningCondition(conditionToWin);
    }

    private void createActions() {
        pickStickAction = new SwitchItemOwnerAction(mainStage, player, stick.getName());
    }

    private void bindRulesAndActions() {
        Behavior behavior = Behavior.builder()
                .actionName("pick")
                .resultMessage("There you go.")
                .failMessage("You can't pick this object.")
                .executionRule(roomHasStickRule)
                .actions(pickStickAction).build();

        stick.addBehavior(behavior);
    }

    @Override
    protected void setKnownActions() {
        game.registerKnownAction(ActionOld.LOOK_AROUND, (command) -> this.lookAroundHandler());
        game.registerKnownAction(ActionOld.PICK, (command) -> this.pickHandler(command.getItemName()));
    }

    private String lookAroundHandler() {
        return game.getCurrentStage().lookAround();
    }

    private String pickHandler(String itemName) {
        Stage currentStage = game.getCurrentStage();
        Item item = currentStage.getItem(itemName);
        return item.executeAction("pick");
    }
}
