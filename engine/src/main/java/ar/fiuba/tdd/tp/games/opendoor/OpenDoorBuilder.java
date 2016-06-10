package ar.fiuba.tdd.tp.games.opendoor;

import ar.fiuba.tdd.tp.games.AbstractGameBuilder;
import ar.fiuba.tdd.tp.games.ActionOld;
import ar.fiuba.tdd.tp.games.Stage;
import ar.fiuba.tdd.tp.games.actions.Action;
import ar.fiuba.tdd.tp.games.actions.PickFromCurrentStageAction;
import ar.fiuba.tdd.tp.games.actions.SetStateValueAction;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.handlers.DefaultActionHandler;
import ar.fiuba.tdd.tp.games.handlers.LookAroundActionHandler;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.rules.*;

import static ar.fiuba.tdd.tp.games.opendoor.OpenDoorProperties.*;

/**
 * Created by Eze on 07/06/2016.
 */
@SuppressWarnings("CPD-START")
public class OpenDoorBuilder extends AbstractGameBuilder {

    private static final String PICK_KEY_ACTION = "pickKeyAction";
    private static final String PICK_KEY_RULE = "pickKeyRule";
    private static final String HAS_PLAYER_KEY = "hasKeyRule";
    private static final String OPEN_DOOR_ACTION = "openDoorAction";

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
    }

    private void createRules() {
        Rule pickKeyRule = new IsInCurrentRoomRule(this.game, KEY_NAME);
        this.addRule(PICK_KEY_RULE, pickKeyRule);

        Rule hasKeyRule = new HasItemRule(this.player, this.getItem(KEY_NAME));
        hasKeyRule.setErrorMessage(DOOR_CLOSED_MSG);
        this.addRule(HAS_PLAYER_KEY,hasKeyRule);
    }

    private void createActions() {
        Action pickKeyAction = new PickFromCurrentStageAction(this.game, this.player.getName(), KEY_NAME);
        this.addAction(PICK_KEY_ACTION, pickKeyAction);

        Action openDoorAction = new SetStateValueAction(this.getItem(DOOR_NAME),IsOpenRule.OPEN_STATUS_KEY,IsOpenRule.OPENED);
        this.addAction(OPEN_DOOR_ACTION,openDoorAction);
    }

    private void bindActionsAndItems() {
        Behavior bvior = Behavior.builder()
                .actionName(PICK)
                .executionRule(this.getRule(PICK_KEY_RULE))
                .actions(this.getAction(PICK_KEY_ACTION))
                .resultMessage(KEY_TAKED_MSG)
                .build();
        this.getItem(KEY_NAME).addBehavior(bvior);

        bvior = Behavior.builder()
                .actionName(OPEN)
                .executionRule(this.getRule(HAS_PLAYER_KEY))
                .actions(this.getAction(OPEN_DOOR_ACTION))
                .resultMessage(DOOR_OPENED_MSG)
                .build();
        this.getItem(DOOR_NAME).addBehavior(bvior);
    }

    private void createStages() {
        this.addStage(new Stage(STAGE_NAME));
    }

    private void createItems() {
        this.addItem(this.buildKey());
        this.addItem(this.buildDoor());
    }

    private Item buildKey() {
        return new Item(KEY_NAME, KEY_DESC);
    }

    private Item buildDoor() {
        Item door = new Item(DOOR_NAME, DOOR_DESC);
        door.addState(IsOpenRule.OPEN_STATUS_KEY, IsOpenRule.CLOSED);
        return door;
    }

    private void configureStagesAndItems() {
        Stage room1 = this.getStage(STAGE_NAME);
        room1.addItem(this.getItem(KEY_NAME));
        room1.addItem(this.getItem(DOOR_NAME));
    }

    private void setWinningCondition() {
        Rule playerIsInRoom = new PlayerIsInRoomRule(game, STAGE_NAME);
        Rule doorIsOpen = new IsOpenRule(this.getItem(DOOR_NAME));
        Rule rule = playerIsInRoom.and(doorIsOpen);
        game.setWinningCondition(rule);
    }

}
