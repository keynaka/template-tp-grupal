package ar.fiuba.tdd.tp.games.escape;

import ar.fiuba.tdd.tp.games.AbstractGameBuilder;
import ar.fiuba.tdd.tp.games.ActionOld;
import ar.fiuba.tdd.tp.games.Stage;
import ar.fiuba.tdd.tp.games.actions.*;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.handlers.DefaultActionHandler;
import ar.fiuba.tdd.tp.games.handlers.LookAroundActionHandler;
import ar.fiuba.tdd.tp.games.handlers.ParametizedActionHandler;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;
import ar.fiuba.tdd.tp.games.rules.*;
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
    private static final String BREAK_WINDOW_ACTION = "breakWindowAction";
    private static final String SHOW_ID_CARD_ACTION = "showIdCardAction";
    private static final String MOVE_OLD_BOOK_ACTION = "moveOldBookAction";
    private static final String USE_STAIRS_ACTION = "useStairsAction";
    private static final String USE_RAILING_ACTION = "useRailingAction";
    private static final String PICK_KEY_RULE = "pickKeyRule";
    private static final String PICK_HAMMER_RULE = "pickHammerRule";
    private static final String SCREWDRIVER_NUMBER1_RULE = "pickScrewdriverNumber1Rule";
    private static final String SCREWDRIVER_NUMBER2_RULE = "pickScrewdriverNumber2Rule";
    private static final String PICK_ID_CARD_RULE = "pickIdCardRule";
    private static final String MOVE_BOATPICTURE_RULE = "moveBoatPictureRule";
    private static final String SHOW_ID_CARD_RULE = "showIdCardRule";
    private static final String MOVE_OLD_BOOK_RULE = "moveOldBookRule";
    private static final String USE_STAIRS_RULE = "useStairsRule";
    private static final String USE_RAILING_RULE = "useRailingRule";
    private static final String HAS_ID_CARD = "hasIdCard";
    private static final String HAS_PLAYER_PICTURE = "hasPlayerPicture";
    private static final String BREAK_WINDOW_RULE = "breakWindowRule";
    private static final String PUT_PICTURE_RULE = "putPictureRule";


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
        setLosingCondition();
    }

    private void createRules() {
        Rule pickKeyRule = new IsInCurrentRoomRule(this.game, KEY_NAME);
        this.addRule(PICK_KEY_RULE, pickKeyRule);

        Rule pickIdCardRule = new IsInCurrentRoomRule(this.game, ID_CARD_NAME);
        this.addRule(PICK_ID_CARD_RULE, pickIdCardRule);

        Rule pickHammerRule = new IsInCurrentRoomRule(this.game, HAMMER_NAME);
        this.addRule(PICK_HAMMER_RULE, pickHammerRule);

        Rule pickScrewDriver1Rule = new IsInCurrentRoomRule(this.game, SCREWDRIVER_NUMBER1_NAME);
        this.addRule(SCREWDRIVER_NUMBER1_RULE, pickScrewDriver1Rule);

        Rule pickScrewDriver2Rule = new IsInCurrentRoomRule(this.game, SCREWDRIVER_NUMBER2_NAME);
        this.addRule(SCREWDRIVER_NUMBER2_RULE, pickScrewDriver2Rule);

        Rule hasKey = new HasItemRule(this.player, this.getItem(KEY_NAME));
        this.addRule(OPEN_SAFEBOX_RULE, hasKey);

        Rule moveBoatPictureRule = new IsInCurrentRoomRule(this.game, BOAT_PICTURE_NAME);
        this.addRule(MOVE_BOATPICTURE_RULE, moveBoatPictureRule);

        Rule hasIdCardRule = new HasItemRule(this.player, this.getItem(ID_CARD_NAME));
        this.addRule(HAS_ID_CARD, hasIdCardRule);

        Rule hasPlayerPictureRule = new HasItemRule(this.player, this.getItem(PLAYER_PICTURE_NAME));
        this.addRule(HAS_PLAYER_PICTURE, hasPlayerPictureRule);

        this.addRule(PUT_PICTURE_RULE, hasIdCardRule.and(hasPlayerPictureRule));

        Rule hasHammerRule = new HasItemRule(this.player, this.getItem(HAMMER_NAME));
        Rule isInBasementDownstairsRule = new PlayerIsInRoomRule(this.player, BASEMENT_DOWNSTAIRS_NAME);

        this.addRule(BREAK_WINDOW_RULE, hasHammerRule.and(isInBasementDownstairsRule));

        Rule idCardHasPlayersPicture = new VerifiesStateRule(this.getItem(ID_CARD_NAME), ID_CARD_PICTURE_STATE, PLAYER_PICTURE_NAME);
        Rule isInLibraryRule = new PlayerIsInRoomRule(this.player, LIBRARY_ACCESS_NAME);
        this.addRule(SHOW_ID_CARD_RULE, idCardHasPlayersPicture.and(isInLibraryRule));

        Rule moveOldBookRule = new IsInCurrentRoomRule(this.game, OLD_BOOK_NAME);
        this.addRule(MOVE_OLD_BOOK_RULE, moveOldBookRule);

        Rule useStairsRule = new IsInCurrentRoomRule(this.game, STAIRS_NAME);
        this.addRule(USE_STAIRS_RULE, useStairsRule);

        Rule railingIsInCurrentRoomRule = new IsInCurrentRoomRule(this.game, RAILING_NAME);
        Rule railingIsNotUsed = new VerifiesStateRule(this.getItem(RAILING_NAME), RAILING_STATE, NOT_USED);
        this.addRule(USE_STAIRS_RULE, railingIsInCurrentRoomRule.and(railingIsNotUsed));

    }

    private void createActions() {
        Action pickKeyAction = new PickFromCurrentStageAction(this.game, this.player.getName(), KEY_NAME);
        this.addAction(PICK_KEY_ACTION, pickKeyAction);

        Action moveBoatPictureAction = new AddItemAction(this.getStage(ROOM1_NAME), this.getItem(SAFEBOX_NAME));
        this.addAction(MOVE_BOATPICTURE_ACTION, moveBoatPictureAction);

        Action moveOldBookAction = new SetStateValueAction(this.getItem(OLD_BOOK_NAME), OLD_BOOK_STATE, MOVED_BOOK);
        this.addAction(MOVE_OLD_BOOK_ACTION, moveOldBookAction);

        Action openSafeboxAction = new SwitchItemOwnerAction(this.getItemKeeper(SAFEBOX_NAME), this.getStage(ROOM1_NAME), ID_CARD_NAME);
        this.addAction(OPEN_SAFEBOX_ACTION, openSafeboxAction);

        Action pickIdCardAction = new PickFromCurrentStageAction(this.game, this.player.getName(), ID_CARD_NAME);
        this.addAction(PICK_ID_CARD_ACTION, pickIdCardAction);

        Action putPictureAction = new SetStateValueAction(this.getItem(ID_CARD_NAME), ID_CARD_PICTURE_STATE, PLAYER_PICTURE_NAME);
        this.addAction(PUT_PICTURE_ACTION, putPictureAction);

        Action breakWindowAction = new SetStateValueAction(this.getItem(WINDOW_NAME), WINDOW_STATE, BROKEN_WINDOW);
        this.addAction(BREAK_WINDOW_ACTION, breakWindowAction);

        Action showIdCardAction = new SwitchItemOwnerAction(this.player, this.getItemKeeper(LIBRARIAN_NAME), ID_CARD_NAME);
        this.addAction(SHOW_ID_CARD_ACTION, showIdCardAction);

        Action useStairsAction = new SetStateValueAction(this.player, LIFE_STATE, DEAD_PLAYER);
        this.addAction(USE_STAIRS_ACTION, useStairsAction);

        Action useRailingAction = new ChangePlayerStageAction(this.game, this.getStage(BASEMENT_DOWNSTAIRS_NAME));
        this.addAction(USE_RAILING_ACTION, useRailingAction);

    }

    private void bindActionsAndItems() {
        Behavior behavior = Behavior.builder()
                .actionName(PICK)
                .executionRule(this.getRule(KEY_NAME))
                .actions(this.getAction(PICK_KEY_ACTION))
                .resultMessage(PICK_RESULT_MSG)
                .build();
        this.getItem(KEY_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(MOVE)
                .executionRule(this.getRule(MOVE_BOATPICTURE_RULE))
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
                .executionRule(this.getRule(PICK_ID_CARD_RULE))
                .actions(this.getAction(PICK_ID_CARD_ACTION))
                .resultMessage(PICK_RESULT_MSG)
                .build();
        this.getItem(ID_CARD_NAME).addBehavior(behavior);


        behavior = Behavior.builder()
                .actionName(PUT)
                .executionRule(this.getRule(PUT_PICTURE_RULE))
                .actions(this.getAction(PUT_PICTURE_ACTION))
                .resultMessage(String.format(PUT_RESULT_MSG, PLAYER_PICTURE_NAME, ID_CARD_NAME))
                .build();
        this.getItem(PLAYER_PICTURE_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(BREAK)
                .executionRule(this.getRule(BREAK_WINDOW_RULE))
                .actions(this.getAction(BREAK_WINDOW_ACTION))
                .resultMessage(BREAK_WINDOW_RESULT_MSG)
                .build();
        this.getItem(WINDOW_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(SHOW)
                .executionRule(this.getRule(SHOW_ID_CARD_RULE))
                .actions(this.getAction(SHOW_ID_CARD_ACTION))
                .resultMessage(SHOW_ID_CARD_RESULT_MSG)
                .build();
        this.getItem(ID_CARD_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(MOVE)
                .executionRule(this.getRule(MOVE_OLD_BOOK_RULE))
                .actions(this.getAction(MOVE_OLD_BOOK_ACTION))
                .resultMessage(MOVE_OLD_BOOK_RESULT_MSG)
                .build();
        this.getItem(OLD_BOOK_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(USE)
                .executionRule(this.getRule(USE_STAIRS_RULE))
                .actions(this.getAction(USE_STAIRS_ACTION))
                .resultMessage(USE_STAIRS_RESULT_MSG)
                .build();
        this.getItem(STAIRS_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(USE)
                .executionRule(this.getRule(USE_RAILING_RULE))
                .actions(this.getAction(USE_RAILING_ACTION))
                .resultMessage(USE_RAILING_RESULT_MSG)
                .build();
        this.getItem(RAILING_NAME).addBehavior(behavior);

    }

    private void bindStagesAndActions() {
        // todos los escenarios soportan comando goto.
        for (Stage stage : this.stages) {
            IsNextRoomRule isNextRoomRule = new IsNextRoomRule(this.game, stage.getName());
            isNextRoomRule.setErrorMessage(String.format(GOTO_NOT_NEXT_ROOM_MSG, stage.getName()));

            Behavior behavior = Behavior.builder()
                    .actionName(GOTO)
                    .executionRule(isNextRoomRule)
                    .actions(new ChangePlayerStageAction(this.game, stage))
                    .resultMessage(String.format(GOTO_RESULT_MSG, stage.getName()))
                    .build();
            stage.addBehavior(behavior);
        }
        // TODO configurar RULE de entrada a la biblioteca
        // TODO configurar RULE de entrada al Sotano (libro viejo movido)
        // TODO configurar RULE de entrada al SotanoAfuera (baranda usada)
        // TODO configurar RULE de entrada a "Afuera" (ventana rota)
    }

    private void configureStagesAndItems() {
        this.configureRoom1();
        this.configureRoom2();
        this.configureRoom3();
        this.configureLibraryAccess();
        this.configureLibrary();
        this.configureBasement();
        this.configureBasementDownstairs();
    }

    private void configureRoom1() {
        Stage room1 = this.getStage(ROOM1_NAME);
        room1.addItem(this.getItem(BOAT_PICTURE_NAME));
        room1.addItem(this.getItem(SAFEBOX_NAME));
    }

    private void configureRoom2() {
        Stage room2 = this.getStage(ROOM2_NAME);
        room2.addItem(this.getItem(HAMMER_NAME));
        room2.addItem(this.getItem(SCREWDRIVER_NUMBER1_NAME));
        room2.addItem(this.getItem(SCREWDRIVER_NUMBER2_NAME));
    }

    private void configureRoom3() {
        Stage room3 = this.getStage(ROOM3_NAME);
        room3.addItem(this.getItem(KEY_NAME));
    }

    private void configureLibraryAccess() {
        Stage libraryAccess = this.getStage(LIBRARY_ACCESS_NAME);
        libraryAccess.addItem(this.getItem(LIBRARIAN_NAME));
    }

    private void configureLibrary() {
        Stage library = this.getStage(LIBRARY_NAME);
        library.addItem(this.getItem(OLD_BOOK_NAME));
    }

    private void configureBasement() {
        Stage basement = this.getStage(BASEMENT_NAME);
        basement.addItem(this.getItem(STAIRS_NAME));
        basement.addItem(this.getItem(RAILING_NAME));
    }

    private void configureBasementDownstairs() {
        Stage basementDownstairs = this.getStage(BASEMENT_DOWNSTAIRS_NAME);
        basementDownstairs.addItem(this.getItem(WINDOW_NAME));
        basementDownstairs.addItem(this.getItem(STAIRS_NAME));
        basementDownstairs.addItem(this.getItem(RAILING_NAME));
    }

    private void createItems() {
        this.addItem(new Item(PLAYER_PICTURE_NAME, PLAYER_PICTURE_DESCRIPTION));
        this.addItem(new Item(HAMMER_NAME, HAMMER_DESCRIPTION));
        this.addItem(new Item(KEY_NAME, KEY_DESCRIPTION));
        this.addItem(new Item("Mesa", TABLE_DESCRIPTION));
        this.addItem(new Item("BotellaLicor", LIQUOR_DESCRIPTION));
        this.addItem(new Item("Vaso1", GLASS_DESCRIPTION));
        this.addItem(new Item("Vaso2", GLASS_DESCRIPTION));
        this.addItem(new Item(STAIRS_NAME, STAIRS_DESCRIPTION));
        this.addItem(new Item(RAILING_NAME, RAILING_DESCRIPTION));
        this.addItem(new Item(BOAT_PICTURE_NAME, BOAT_PICTURE_DESCRIPTION));
        this.addItem(new Item(SCREWDRIVER_NUMBER1_NAME, SCREWDRIVER_DESCRIPTION));
        this.addItem(new Item(SCREWDRIVER_NUMBER2_NAME, SCREWDRIVER_DESCRIPTION));
        this.addItem(new Item(WINDOW_NAME, WINDOW_DESCRIPTION));
        this.addItem(new Item(OLD_BOOK_NAME, OLD_BOOK_DESCRIPTION));
        Item idCard = new Item(ID_CARD_NAME, ID_CARD_DESCRIPTION);
        idCard.addState(ID_CARD_PICTURE_STATE, STRANGER_PICTURE_NAME);
        this.addItem(idCard);
        ItemContainer safebox = new ItemContainer(SAFEBOX_NAME, SAFEBOX_DESCRIPTION, SAFEBOX_SIZE);
        safebox.addItem(idCard);
        this.addItem(safebox);
        // Ver si el bibliotecario tiene que ser un ItemContainer
        ItemContainer librarian = new ItemContainer(LIBRARIAN_NAME, LIBRIARIAN_DESCRIPTION, LIBRARIAN_SIZE);
        this.addItem(librarian);

    }

    private void setWinningCondition() {
        game.setWinningCondition(new PlayerIsInRoomRule(this.player, "Afuera"));
    }

    private void setLosingCondition() {
        Rule isDead = new VerifiesStateRule(this.player, LIFE_STATE, DEAD_PLAYER);
        Item hammer = this.getItem(HAMMER_NAME);
        Rule hasntHammer = new HasItemRule(this.player, hammer).negate();
        Rule isInDownBasement = new PlayerIsInRoomRule(this.player, BASEMENT_DOWNSTAIRS_NAME);

        game.setLoosingCondition(isDead.or(hasntHammer.and(isInDownBasement)));
    }

    private void createStages() {
        createStage(HALL_NAME, ROOM1_NAME, ROOM2_NAME, ROOM3_NAME, LIBRARY_ACCESS_NAME);
        createStage(ROOM1_NAME, HALL_NAME);
        createStage(ROOM2_NAME, HALL_NAME);
        createStage(ROOM3_NAME, HALL_NAME);
        createStage(LIBRARY_ACCESS_NAME, HALL_NAME, LIBRARY_NAME);
        createStage(LIBRARY_NAME, LIBRARY_ACCESS_NAME, BASEMENT_NAME);
        createStage(BASEMENT_NAME, LIBRARY_NAME, BASEMENT_DOWNSTAIRS_NAME);
        createStage(BASEMENT_DOWNSTAIRS_NAME, "Afuera");
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
        game.registerKnownAction(ActionOld.LOOK_AROUND, new LookAroundActionHandler(this.game));
        game.registerKnownAction(ActionOld.GOTO, new DefaultActionHandler(this.game));
        game.registerKnownAction(ActionOld.PICK, new DefaultActionHandler(this.game));
        game.registerKnownAction(ActionOld.MOVE, new DefaultActionHandler(this.game));
        game.registerKnownAction(ActionOld.OPEN, (command) -> this.openActionHandler(command));
        game.registerKnownAction(ActionOld.PUT, new ParametizedActionHandler(this.game));
        game.registerKnownAction(ActionOld.BREAK, new DefaultActionHandler(this.game));
        game.registerKnownAction(ActionOld.SHOW, new DefaultActionHandler(this.game));
        game.registerKnownAction(ActionOld.USE, new DefaultActionHandler(this.game));

//        game.registerKnownAction(ActionOld.OPEN, (itemName, args) -> this.actionHandler(ActionOld.OPEN.getActionName(),itemName));
//        game.registerKnownAction(ActionOld.MOVE, (itemName, args) -> this.actionHandler(ActionOld.MOVE.getActionName(),itemName));
//        game.registerKnownAction(ActionOld.PUT, (itemName, arguments) -> this.actionHandler(ActionOld.PUT.getActionName(), itemName));
//        game.registerKnownAction(ActionOld.DROP, (itemName, arguments) -> this.actionHandler(ActionOld.DROP.getActionName(),itemName));
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

    @SuppressWarnings("CPD-END")

    @Override
    protected void configurePlayer() {
        player.addState(LIFE_STATE, ALIVE_PLAYER);
        player.setCurrentStage(HALL_NAME);
        player.addToInventory(this.getItem(PLAYER_PICTURE_NAME));
        player.addToInventory(new Item("Lapicera", "pen"));
    }
}
