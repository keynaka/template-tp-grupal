package ar.fiuba.tdd.tp.games.escape;

import ar.fiuba.tdd.tp.games.AbstractGameBuilder;
import ar.fiuba.tdd.tp.games.ActionOld;
import ar.fiuba.tdd.tp.games.Stage;
import ar.fiuba.tdd.tp.games.actions.*;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.exceptions.GameException;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;
import ar.fiuba.tdd.tp.games.rules.HasItemRule;
import ar.fiuba.tdd.tp.games.rules.PlayerIsInRoomRule;
import ar.fiuba.tdd.tp.games.rules.Rule;
import ar.fiuba.tdd.tp.games.rules.VerifiesStateRule;
import ar.fiuba.tdd.tp.red.server.Command;

import static ar.fiuba.tdd.tp.games.escape.EscapeProperties.*;

/**
 * Created by swandelow on 5/19/16.
 */

@SuppressWarnings("CPD-START")
public class EscapeBuilder2 extends AbstractGameBuilder {

    private static final String PICK_KEY_ACTION = "pickKeyAction";
    private static final String MOVE_BOATPICTURE_ACTION = "moveBoatPictureAction";
    private static final String OPEN_SAFEBOX_ACTION = "openSaveboxAction";
    private static final String PICK_ID_CARD_ACTION = "pickIdCardAction";
    private static final String PUT_PICTURE_ACTION = "putPictureAction";
    private static final String OPEN_SAFEBOX_RULE = "openSaveboxRule";


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
        setWinningCondition();
        setLosingCondition();
    }

    private void createRules() {
        Rule hasKey = new HasItemRule(this.player, this.getItem(KEY_NAME));
        this.addRule(OPEN_SAFEBOX_RULE, hasKey);
    }

    private void createActions() {
//        Action pickKeyAction = new SwitchItemOwnerAction(this.getStage(ROOM3_NAME), this.player, KEY_NAME);
        Action pickKeyAction = new PickFromCurrentStageAction(this.game, this.player.getName(), KEY_NAME);
        this.addAction(PICK_KEY_ACTION, pickKeyAction);

        Action moveBoatPictureAction = new AddItemAction(this.getStage(ROOM1_NAME), this.getItem(SAFEBOX_NAME));
        this.addAction(MOVE_BOATPICTURE_ACTION, moveBoatPictureAction);

        Action openSafeboxAction = new SwitchItemOwnerAction(this.getItemKeeper(SAFEBOX_NAME), this.getStage(ROOM1_NAME), ID_CARD_NAME);
        this.addAction(OPEN_SAFEBOX_ACTION, openSafeboxAction);

//        Action pickIdCardAction = new SwitchItemOwnerAction(this.getStage(ROOM1_NAME), this.player, ID_CARD_NAME);
        Action pickIdCardAction = new PickFromCurrentStageAction(this.game, this.player.getName(), ID_CARD_NAME);
        this.addAction(PICK_ID_CARD_ACTION, pickIdCardAction);

        Action putPictureAction = new SetStateValueAction(this.getItem(ID_CARD_NAME), ID_CARD_PICTURE_STATE, PLAYER_PICTURE_NAME);
        this.addAction(PUT_PICTURE_ACTION, putPictureAction);
    }

    private void bindActionsAndItems() {
        Behavior behavior = Behavior.builder()
                .actionName(PICK)
                .actions(this.getAction(PICK_KEY_ACTION))
                .resultMessage(PICK_RESULT_MSG)
                .build();
        this.getItem(KEY_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(MOVE)
                .actions(this.getAction(MOVE_BOATPICTURE_ACTION))
                .resultMessage(MOVE_RESULT_MSG)
                .build();
        this.getItem(BOAT_PICTURE_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(OPEN)
                .executionRule(this.getRule(OPEN_SAFEBOX_RULE))
                .actions(this.getAction(OPEN_SAFEBOX_ACTION))
                .resultMessage(String.format(OPEN_RESULT_MSG, SAFEBOX_NAME))
                .build();
        this.getItem(SAFEBOX_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(PICK)
                .actions(this.getAction(PICK_ID_CARD_ACTION))
                .resultMessage(PICK_RESULT_MSG)
                .build();
        this.getItem(ID_CARD_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(PUT)
                .actions(this.getAction(PUT_PICTURE_ACTION))
                .resultMessage(String.format(PUT_RESULT_MSG, PLAYER_PICTURE_NAME, ID_CARD_NAME))
                .build();
        this.getItem(PLAYER_PICTURE_NAME).addBehavior(behavior);
    }

    private void configureStagesAndItems() {
        this.configureRoom1();
        this.configureRoom3();
    }

    private void configureRoom1() {
        Stage room1 = this.getStage(ROOM1_NAME);
        room1.addItem(this.getItem(BOAT_PICTURE_NAME));
    }

    private void configureRoom3() {
        Stage room3 = this.getStage(ROOM3_NAME);
        room3.addItem(this.getItem(KEY_NAME));
    }

    private void createItems() {
        this.addItem(new Item(PLAYER_PICTURE_NAME, PLAYER_PICTURE_DESCRIPTION));
        this.addItem(new Item("Martillo", HAMMER_DESCRIPTION));
        this.addItem(new Item(KEY_NAME, KEY_DESCRIPTION));
        this.addItem(new Item("Mesa", TABLE_DESCRIPTION));
        this.addItem(new Item("BotellaLicor", LIQUOR_DESCRIPTION));
        this.addItem(new Item("Vaso1", GLASS_DESCRIPTION));
        this.addItem(new Item("Vaso2", GLASS_DESCRIPTION));
        this.addItem(new Item(BOAT_PICTURE_NAME, BOAT_PICTURE_DESCRIPTION));
        this.addItem(new Item("Destornillador1", SCREWDRIVER_DESCRIPTION));
        this.addItem(new Item("Destornillador2", SCREWDRIVER_DESCRIPTION));
        Item idCard = new Item(ID_CARD_NAME, ID_CARD_DESCRIPTION);
        idCard.addState(ID_CARD_PICTURE_STATE, STRANGER_PICTURE_NAME);
        this.addItem(idCard);
        ItemContainer safebox = new ItemContainer(SAFEBOX_NAME, SAFEBOX_DESCRIPTION, SAFEBOX_SIZE);
        safebox.addItem(idCard);
        this.addItem(safebox);
    }

    private void setWinningCondition() {
        game.setWinningCondition(new PlayerIsInRoomRule(this.player, "Afuera"));
    }

    private void setLosingCondition() {
        Rule isDead = new VerifiesStateRule(this.player, "lifeStatus", "dead");
        Item hammer = this.getItem("Martillo");
        Rule hasntHammer = new HasItemRule(this.player, hammer).negate();
        Rule isInDownBasement = new PlayerIsInRoomRule(this.player, "SotanoAbajo");

        game.setLoosingCondition(isDead.or(hasntHammer.and(isInDownBasement)));
    }

    private void createStages() {
        createStage("Pasillo", ROOM1_NAME, "Salon2", ROOM3_NAME, "BibliotecaAcceso");
        createStage(ROOM1_NAME, "Pasillo");
        createStage("Salon2", "Pasillo");
        createStage(ROOM3_NAME, "Pasillo");
        createStage("BibliotecaAcceso", "Pasillo", "Biblioteca");
        createStage("Biblioteca", "BibliotecaAcceso", "Sotano");
        createStage("Sotano", "Biblioteca", "Sotano");
        createStage("SotanoAbajo", "Afuera");
        createStage("Afuera");
    }

    private Stage createStage(String stageName, String... adjacentStages) {
        Stage stage = new Stage(stageName);
        for (String adjacentStage : adjacentStages) {
            stage.addConsecutiveStage(adjacentStage);
        }
        // agrego stage a la lista de stages del builder antes de retornarlo.
        this.addStage(stage);
        return stage;
    }


    @Override
    protected void setKnownActions() {
        game.registerKnownAction(ActionOld.LOOK_AROUND, (command) -> this.lookAroundHandler());
        game.registerKnownAction(ActionOld.GOTO, (command) -> this.gotoHandler(command));
        game.registerKnownAction(ActionOld.PICK, (command) -> this.actionHandler(command));
        game.registerKnownAction(ActionOld.MOVE, (command) -> this.actionHandler(command));
        game.registerKnownAction(ActionOld.OPEN, (command) -> this.openActionHandler(command));
        game.registerKnownAction(ActionOld.PUT, (command) -> this.putActionHandler(command));
//        game.registerKnownAction(ActionOld.OPEN, (itemName, args) -> this.actionHandler(ActionOld.OPEN.getActionName(),itemName));
//        game.registerKnownAction(ActionOld.MOVE, (itemName, args) -> this.actionHandler(ActionOld.MOVE.getActionName(),itemName));
//        game.registerKnownAction(ActionOld.USE, (itemName, arguments) -> this.actionHandler(ActionOld.USE.getActionName(),itemName));
//        game.registerKnownAction(ActionOld.BREAK, (itemName, arguments) -> this.actionHandler(ActionOld.BREAK.getActionName(),itemName));
//        game.registerKnownAction(ActionOld.SHOW, (itemName, arguments) -> this.actionHandler(ActionOld.SHOW.getActionName(),itemName));
//        game.registerKnownAction(ActionOld.PUT, (itemName, arguments) -> this.actionHandler(ActionOld.PUT.getActionName(), itemName));
//        game.registerKnownAction(ActionOld.DROP, (itemName, arguments) -> this.actionHandler(ActionOld.DROP.getActionName(),itemName));
    }

    private String lookAroundHandler() {
        return game.getCurrentStage().lookAround();
    }

    private String gotoHandler(Command command) {
        String stageName = command.getItemName();
        String nextStageName;
        try {
            nextStageName = game.getCurrentStage().getConsecutiveStage(stageName);
            Stage nextStage = game.getStage(nextStageName);
            nextStage.enter(game.getPlayer());
            return String.format("You have entered to %s.", nextStage.getName());
        } catch (GameException e) {
            return "Invalid stage.";
        }
    }

    private String actionHandler(Command command) {
        String actionName = command.getAction().getActionName();
        return game.getCurrentStage().getItem(command.getItemName()).executeAction(actionName);
    }

    private String openActionHandler(Command command) {
        String keyName = command.getArgument();
        if (game.getPlayer().hasItem(keyName)) {
            return this.actionHandler(command);
        }
        return String.format(NOT_IN_INVENTORY_MSG, keyName);
    }

    private String putActionHandler(Command command) {
        String item2Name = command.getArgument();
        if (game.getPlayer().hasItem(item2Name)) {
            return this.actionHandler(command);
        }
        return String.format(NOT_IN_INVENTORY_MSG, item2Name);
    }

    @SuppressWarnings("CPD-END")

    @Override
    protected void configurePlayer() {
        player.addState("lifeStatus", "alive");
        player.setCurrentStage("Pasillo");
        player.addToInventory(this.getItem(PLAYER_PICTURE_NAME));
        player.addToInventory(new Item("Lapicera", "pen"));
    }
}
