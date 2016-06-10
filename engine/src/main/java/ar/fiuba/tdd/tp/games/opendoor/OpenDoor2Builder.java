package ar.fiuba.tdd.tp.games.opendoor;


import ar.fiuba.tdd.tp.games.AbstractGameBuilder;
import ar.fiuba.tdd.tp.games.ActionOld;
import ar.fiuba.tdd.tp.games.Stage;
import ar.fiuba.tdd.tp.games.actions.Action;
import ar.fiuba.tdd.tp.games.actions.PickFromCurrentStageAction;
import ar.fiuba.tdd.tp.games.actions.SetStateValueAction;
import ar.fiuba.tdd.tp.games.actions.SwitchItemOwnerAction;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.handlers.DefaultActionHandler;
import ar.fiuba.tdd.tp.games.handlers.ExamineActionHandler;
import ar.fiuba.tdd.tp.games.handlers.LookAroundActionHandler;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;
import ar.fiuba.tdd.tp.games.rules.*;

import static ar.fiuba.tdd.tp.games.opendoor.OpenDoor2Properties.*;

/**
 * Created by Eze on 07/06/2016.
 */
@SuppressWarnings("CPD-START")
public class OpenDoor2Builder extends AbstractGameBuilder {
    private static final String PICK_KEY_ACTION = "pickKeyAction";
    private static final String PICK_KEY_RULE = "pickKeyRule";
    private static final String HAS_PLAYER_KEY = "hasKeyRule";
    private static final String OPEN_DOOR_ACTION = "openDoorAction";
    private static final String OPEN_BOX_RULE = "openBoxRule";
    private static final String OPEN_BOX_ACTION = "openBoxAction";
    private static final String BOX_STATE_RULE = "boxStateRule";
    private static final String UNBOX_KEY_ACTION = "unboxKeyAction";


    @Override
    protected void buildEnvironment() {
        game.setName(GAME_NAME);
        game.setEndGameMessage(DOOR_OPENED_MSG);
        this.createStages();
        this.createItems();
        this.configureStagesAndItems();
        this.configurePlayer();
        this.createRules();
        this.createActions();
        this.bindActionsAndItems();
        setWinningCondition();
    }

    @Override
    protected void configurePlayer() {
        player.addState(LIFE_STATE, ALIVE_PLAYER);
        player.setCurrentStage(STAGE_NAME);
    }

    @Override
    protected void setKnownActions() {
        game.registerKnownAction(ActionOld.LOOK_AROUND, new LookAroundActionHandler(this.game));
        game.registerKnownAction(ActionOld.PICK, new DefaultActionHandler(this.game));
        game.registerKnownAction(ActionOld.OPEN, new DefaultActionHandler(this.game));
        game.registerKnownAction(ActionOld.EXAMINE, new ExamineActionHandler(this.game));
    }

    private void createRules() {
        Rule pickKeyRule = new IsInCurrentRoomRule(this.game, KEY_NAME);
        this.addRule(PICK_KEY_RULE, pickKeyRule);

        Rule hasKeyRule = new HasItemRule(this.player, this.getItem(KEY_NAME));
        hasKeyRule.setErrorMessage(DOOR_CLOSED_MSG);
        this.addRule(HAS_PLAYER_KEY,hasKeyRule);

        Rule openBoxRule = new IsOpenRule(this.getItem(BOX_NAME));
        this.addRule(OPEN_BOX_RULE,openBoxRule);

        Rule boxStateRule = new VerifiesStateRule(this.getItem(BOX_NAME),IsOpenRule.OPEN_STATUS_KEY,IsOpenRule.CLOSED);
        this.addRule(BOX_STATE_RULE,boxStateRule);

    }

    private void createActions() {
        Action pickKeyAction = new PickFromCurrentStageAction(this.game, this.player.getName(), KEY_NAME);
        this.addAction(PICK_KEY_ACTION, pickKeyAction);

        Action openDoorAction = new SetStateValueAction(this.getItem(DOOR_NAME), IsOpenRule.OPEN_STATUS_KEY,IsOpenRule.OPENED);
        this.addAction(OPEN_DOOR_ACTION,openDoorAction);

        Action openBoxAction = new SetStateValueAction(this.getItem(BOX_NAME), IsOpenRule.OPEN_STATUS_KEY,IsOpenRule.OPENED);
        this.addAction(OPEN_BOX_ACTION,openBoxAction);

        Action unboxKey = new SwitchItemOwnerAction(this.getItemKeeper(BOX_NAME),this.getStage(STAGE_NAME),KEY_NAME);
        this.addAction(UNBOX_KEY_ACTION,unboxKey);

    }

    private void bindActionsAndItems() {
        Behavior behavior = Behavior.builder()
                .actionName(PICK)
                .executionRule(this.getRule(OPEN_BOX_RULE))
                .actions(this.getAction(PICK_KEY_ACTION))
                .resultMessage(KEY_TAKED_MSG)
                .build();
        this.getItem(KEY_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(OPEN)
                .executionRule(this.getRule(HAS_PLAYER_KEY))
                .actions(this.getAction(OPEN_DOOR_ACTION))
                .resultMessage(DOOR_OPENED_MSG)
                .build();
        this.getItem(DOOR_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(OPEN)
                .executionRule(this.getRule(BOX_STATE_RULE))
                .actions(this.getAction(OPEN_BOX_ACTION),this.getAction(UNBOX_KEY_ACTION))
                .resultMessage(OPEN_BOX_MSG)
                .build();
        this.getItem(BOX_NAME).addBehavior(behavior);


    }

    private void createStages() {
        this.addStage(new Stage(STAGE_NAME));
    }

    private void createItems() {
        this.addItem(this.buildDoor());
        this.addItem(this.buildBox());
    }

    private Item buildKey() {
        return new Item(KEY_NAME, KEY_DESC);
    }

    private Item buildDoor() {
        Item door = new Item(DOOR_NAME, DOOR_DESC);
        door.addState(IsOpenRule.OPEN_STATUS_KEY, IsOpenRule.CLOSED);
        return door;
    }

    private Item buildBox() {
        Item key = this.buildKey();
        this.addItem(key);
        ItemContainer box = new ItemContainer(BOX_NAME, BOX_DESC, BOX_SIZE);
        box.addItem(key);
        box.addState(IsOpenRule.OPEN_STATUS_KEY,IsOpenRule.CLOSED);
        return box;
    }

    private void configureStagesAndItems() {
        Stage room1 = this.getStage(STAGE_NAME);
        room1.addItem(this.getItem(BOX_NAME));
        room1.addItem(this.getItem(DOOR_NAME));
    }

    private void setWinningCondition() {
        Rule playerIsInRoom = new PlayerIsInRoomRule(this.player, STAGE_NAME);
        Rule doorIsOpen = new IsOpenRule(this.getItem(DOOR_NAME));
        Rule rule = playerIsInRoom.and(doorIsOpen);
        game.setWinningCondition(rule);
    }

}
