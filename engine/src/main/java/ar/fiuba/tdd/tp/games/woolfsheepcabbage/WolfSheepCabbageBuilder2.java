package ar.fiuba.tdd.tp.games.woolfsheepcabbage;

import ar.fiuba.tdd.tp.games.AbstractGameBuilder;
import ar.fiuba.tdd.tp.games.ActionOld;
import ar.fiuba.tdd.tp.games.Stage;
import ar.fiuba.tdd.tp.games.actions.*;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.handlers.DefaultActionHandler;
import ar.fiuba.tdd.tp.games.handlers.LookAroundActionHandler;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.rules.*;

import java.util.ArrayList;
import java.util.List;

import static ar.fiuba.tdd.tp.games.woolfsheepcabbage.WolfProperties.*;

/**
 * Created by Fede on 05/06/2016.
 */
@SuppressWarnings("CPD-START")
public class WolfSheepCabbageBuilder2 extends AbstractGameBuilder {

    private static final String PICK_WOLF_ACTION = "pickWolfAction";
    private static final String PICK_SHEEP_ACTION = "pickSheepAction";
    private static final String PICK_CABBAGE_ACTION = "pickCabaggeAction";

    private static final String PICK_WOLF_RULE = "pickWolfRule";
    private static final String PICK_SHEEP_RULE = "pickSheepRule";
    private static final String PICK_CABBAGE_RULE = "pickCabbageRule";

    private static final String NO_ITEMS_RULE = "noItemsRule";


    @Override
    protected void buildEnvironment() {
        game.setName(GAME_NAME);
        this.createStages();
        this.createItems();
        this.configureStagesAndItems();
        this.configurePlayer();
        this.createRules();
        this.createActions();
        this.bindActionsAndItems();
        this.bindStagesAndActions();
        setWinningCondition();
    }

    private void createRules() {
        Rule pickWolfRule = new IsInCurrentRoomRule(this.game, WOLF_NAME);
        this.addRule(PICK_WOLF_RULE, pickWolfRule);

        Rule pickSheepRule = new IsInCurrentRoomRule(this.game, SHEEP_NAME);
        this.addRule(PICK_SHEEP_RULE, pickSheepRule);

        Rule pickCabbageRule = new IsInCurrentRoomRule(this.game, CABBAGE_NAME);
        this.addRule(PICK_CABBAGE_RULE, pickCabbageRule);

        Rule hasItemsRule = new NoItemsRule(this.game);
        hasItemsRule.setErrorMessage(BOAT_FULL);
        this.addRule(NO_ITEMS_RULE, hasItemsRule);

    }

    private void createActions() {
        Action pickWolfAction = new PickFromCurrentStageAction(this.game, this.player.getName(), WOLF_NAME);
        this.addAction(PICK_WOLF_ACTION, pickWolfAction);

        Action pickSheepAction = new PickFromCurrentStageAction(this.game, this.player.getName(), SHEEP_NAME);
        this.addAction(PICK_SHEEP_ACTION, pickSheepAction);

        Action pickCabbageAction = new PickFromCurrentStageAction(this.game, this.player.getName(), CABBAGE_NAME);
        this.addAction(PICK_CABBAGE_ACTION, pickCabbageAction);

    }

    private void bindActionsAndItems() {


        Behavior behavior = Behavior.builder()
                .actionName(TAKE)
                .executionRule(this.getRule(PICK_WOLF_RULE))
                .actions(this.getAction(PICK_WOLF_ACTION))
                .resultMessage(TAKE_RESULT_MSG)
                .build();
        this.getItem(WOLF_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(TAKE)
                .executionRule(this.getRule(PICK_SHEEP_RULE))
                .actions(this.getAction(PICK_SHEEP_ACTION))
                .resultMessage(TAKE_RESULT_MSG)
                .build();
        this.getItem(SHEEP_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(TAKE)
                .executionRule(this.getRule(PICK_CABBAGE_RULE))
                .actions(this.getAction(PICK_CABBAGE_ACTION))
                .resultMessage(TAKE_RESULT_MSG)
                .build();
        this.getItem(CABBAGE_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(TAKE)
                .executionRule(this.getRule(NO_ITEMS_RULE))
                .actions(this.getAction(PICK_SHEEP_ACTION))
                .resultMessage(TAKE_RESULT_MSG)
                .build();
        this.getItem(SHEEP_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(TAKE)
                .executionRule(this.getRule(NO_ITEMS_RULE))
                .actions(this.getAction(PICK_WOLF_ACTION))
                .resultMessage(TAKE_RESULT_MSG)
                .build();
        this.getItem(WOLF_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(TAKE)
                .executionRule(this.getRule(NO_ITEMS_RULE))
                .actions(this.getAction(PICK_CABBAGE_ACTION))
                .resultMessage(TAKE_RESULT_MSG)
                .build();
        this.getItem(CABBAGE_NAME).addBehavior(behavior);

        this.addBehaviorToDroppableItems();

    }

    private List<String> fillDroppableItemsList() {
        List<String> droppableItemsNames = new ArrayList<String>();
        droppableItemsNames.add(WOLF_NAME);
        droppableItemsNames.add(SHEEP_NAME);
        droppableItemsNames.add(CABBAGE_NAME);
        return droppableItemsNames;
    }


    private void addBehaviorToDroppableItems() {
        List<String> droppableItemsNames = fillDroppableItemsList();

        for (String itemName : droppableItemsNames) {
            Rule rule = new HasItemRule(this.player, this.getItem(itemName));
            rule.setErrorMessage(OBJECT_NOT_ON_BOAT);
            Behavior behavior = Behavior.builder()
                    .actionName(LEAVE)
                    .executionRule(rule)
                    .actions(new DropInCurrentStageAction(this.game, itemName))
                    .resultMessage(LEAVE_RESULT_MSG)
                    .build();
            this.getItem(itemName).addBehavior(behavior);
        }
    }

    private void bindStagesAndActions() {
        // todos los escenarios soportan comando cross.
        for (Stage stage : this.stages) {
            NotBothItemsRule wolfSheep = new NotBothItemsRule(this.game, WOLF_NAME, SHEEP_NAME);
            NotBothItemsRule cabbageSheep = new NotBothItemsRule(this.game, CABBAGE_NAME, SHEEP_NAME);
            Rule complex = new AndComplexRule(wolfSheep, cabbageSheep);
            complex.setErrorMessage(EAT_ISSUE);

            Behavior behavior = Behavior.builder()
                    .actionName(CROSS)
                    .executionRule(complex)
                    .actions(new ChangePlayerStageAction(this.game, stage))
                    .resultMessage(GOTO_RESULT_MSG)
                    .build();
            stage.addBehavior(behavior);
        }
    }

    private void configureStagesAndItems() {
        Stage room1 = this.getStage(SOUTH_SHORE);
        room1.addItem(this.getItem(WOLF_NAME));
        room1.addItem(this.getItem(SHEEP_NAME));
        room1.addItem(this.getItem(CABBAGE_NAME));
        room1.addState(LOCK_STATUS, UNLOCKED);

        Stage room2 = this.getStage(NORTH_SHORE);
        room2.addState(LOCK_STATUS, UNLOCKED);
    }

    private void createItems() {
        this.addItem(this.buildWolf());
        this.addItem(this.buildSheep());
        this.addItem(this.buildCabbage());

    }

    private void setWinningCondition() {
        Rule playerIsInRoom = new PlayerIsInRoomRule(game, NORTH_SHORE);
        Rule playerHasWolf = new IsInCurrentRoomRule(this.game, WOLF_NAME);
        Rule playerHasSheep = new IsInCurrentRoomRule(this.game, SHEEP_NAME);
        Rule playerHasCabbage = new IsInCurrentRoomRule(this.game, CABBAGE_NAME);
        Rule rule = playerIsInRoom.and(playerHasWolf.and(playerHasSheep.and(playerHasCabbage)));
        game.setWinningCondition(rule);
    }

    private void createStages() {
        this.addStage(new Stage(NORTH_SHORE));
        this.addStage(new Stage(SOUTH_SHORE));
    }


    @Override
    protected void setKnownActions() {
        game.registerKnownAction(ActionOld.LOOK_AROUND, new LookAroundActionHandler(this.game));
        game.registerKnownAction(ActionOld.TAKE, new DefaultActionHandler(this.game));
        game.registerKnownAction(ActionOld.LEAVE, new DefaultActionHandler(this.game));
        game.registerKnownAction(ActionOld.CROSS, new DefaultActionHandler(this.game));
    }

    @SuppressWarnings("CPD-END")

    @Override
    protected void configurePlayer() {
        player.addState(LIFE_STATE, ALIVE_PLAYER);
        player.setCurrentStage(SOUTH_SHORE);
        game.setPlayer(player);
    }

    private Item buildWolf() {
        return new Item(WOLF_NAME, WOLF_DESC);
    }

    private Item buildSheep() {
        return (new Item(SHEEP_NAME, SHEEP_DESC));
    }

    private Item buildCabbage() {
        return new Item(CABBAGE_NAME, CABBAGE_DESC);
    }
}

