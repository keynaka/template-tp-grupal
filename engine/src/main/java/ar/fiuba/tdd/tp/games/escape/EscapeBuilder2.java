package ar.fiuba.tdd.tp.games.escape;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.actions.*;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.handlers.DefaultActionHandler;
import ar.fiuba.tdd.tp.games.handlers.LookAroundActionHandler;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.random.GameRandom;
import ar.fiuba.tdd.tp.games.random.GameRandomImpl;
import ar.fiuba.tdd.tp.games.rules.*;
import ar.fiuba.tdd.tp.games.timer.GameTimer;

import java.util.*;

import static ar.fiuba.tdd.tp.games.escape.EscapeProperties.*;

/**
 * Created by swandelow on 5/19/16.
 */

@SuppressWarnings("CPD-START")
public class EscapeBuilder2 extends AbstractGameBuilder {

    private static final String MOVE_BOATPICTURE_ACTION = "moveBoatPictureAction";
    private static final String OPEN_SAFEBOX_ACTION = "openSaveboxAction";
    private static final String PUT_PICTURE_ACTION = "putPictureAction";
    private static final String OPEN_SAFEBOX_RULE = "openSaveboxRule";
    private static final String BREAK_WINDOW_ACTION = "breakWindowAction";
    private static final String SHOW_ID_CARD_ACTION = "showIdCardAction";
    private static final String BAN_PLAYER_FROM_LIBRARY_ACTION = "banPlayerFromLibraryAction";
    private static final String SHOW_LIQUOR_ACTION = "showLiquorAction";
    private static final String UNLOCK_LIBRARY_ACTION = "unlockLibraryAction";
    private static final String UNLOCK_BASEMENT_ACTION = "unlockBasementAction";
    private static final String UNLOCK_OUTSIDE_ACTION = "unlockOutsideAction";
    private static final String MOVE_OLD_BOOK_ACTION = "moveOldBookAction";
    private static final String USE_STAIRS_ACTION = "useStairsAction";
    private static final String USE_RAILING_ACTION = "useRailingAction";
    private static final String SLEEP_LIBRARIAN_ACTION = "sleepLibrarian";
    private static final String AWAKE_LIBRARIAN_ACTION = "awakeLibrarian";
    private static final String RANDOM_WALK_LIBRARIAN_ACTION = "randomWalkLibrarianAction";
    private static final String MOVE_BOATPICTURE_RULE = "moveBoatPictureRule";
    private static final String SHOW_ID_CARD_RULE = "showIdCardRule";
    private static final String SHOW_WRONG_ID_CARD_RULE = "showWrongIdCardRule";
    private static final String SHOW_LIQUOR_RULE = "showLiquorRule";
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
        this.configurePlayerManager();
        this.createRules();
        this.createActions();
        this.bindActionsAndItems();
        this.bindStagesAndActions();
        this.createTimer();
        setWinningCondition();
        setLosingCondition();
    }

    private void createTimer() {
        this.gameTimer = new GameTimer();
    }

    private void createRules() {
        this.createRules1();

        Rule idCardHasNotPlayersPicture = new VerifiesStateRule(this.getItem(ID_CARD_NAME), ID_CARD_PICTURE_STATE, STRANGER_PICTURE_NAME);
        this.addRule(SHOW_WRONG_ID_CARD_RULE, idCardHasNotPlayersPicture);

        Rule moveOldBookRule = new IsInCurrentRoomRule(this.game, OLD_BOOK_NAME);
        this.addRule(MOVE_OLD_BOOK_RULE, moveOldBookRule);

        Rule useStairsRule = new IsInCurrentRoomRule(this.game, STAIRS_NAME);
        this.addRule(USE_STAIRS_RULE, useStairsRule);

        Rule railingIsInCurrentRoomRule = new IsInCurrentRoomRule(this.game, RAILING_NAME);
        Rule playerIsInBasement = new PlayerIsInRoomRule(game, BASEMENT_NAME);
        this.addRule(USE_RAILING_RULE, railingIsInCurrentRoomRule.and(playerIsInBasement));

        this.setRulesErrorMessage();
    }

    private void createRules1() {
        Rule hasKey = new PlayerHasItemRule(game, this.getItem(KEY_NAME));
        this.addRule(OPEN_SAFEBOX_RULE, hasKey);

        Rule moveBoatPictureRule = new IsInCurrentRoomRule(this.game, BOAT_PICTURE_NAME);
        this.addRule(MOVE_BOATPICTURE_RULE, moveBoatPictureRule);

        Rule hasIdCardRule = new PlayerHasItemRule(game, this.getItem(ID_CARD_NAME));
        this.addRule(HAS_ID_CARD, hasIdCardRule);

        Rule hasPlayerPictureRule = new PlayerHasItemRule(game, this.getItem(PLAYER_PICTURE_NAME));
        this.addRule(HAS_PLAYER_PICTURE, hasPlayerPictureRule);

        this.addRule(PUT_PICTURE_RULE, hasIdCardRule.and(hasPlayerPictureRule));

        Rule hasHammerRule = new PlayerHasItemRule(game, this.getItem(HAMMER_NAME));
        Rule isInBasementDownstairsRule = new PlayerIsInRoomRule(game, BASEMENT_DOWNSTAIRS_NAME);

        this.addRule(BREAK_WINDOW_RULE, hasHammerRule.and(isInBasementDownstairsRule));

        Rule idCardHasPlayersPicture = new VerifiesStateRule(this.getItem(ID_CARD_NAME), ID_CARD_PICTURE_STATE, PLAYER_PICTURE_NAME);
        Rule isInLibraryRule = new PlayerIsInRoomRule(game, LIBRARY_ACCESS_NAME);
        Rule isAllowedInLibrary = new VerifyPlayerStateRule(game, ALLOWED_IN_LIBRARY_STATUS, ALLOWED);
        this.addRule(SHOW_ID_CARD_RULE, idCardHasPlayersPicture.and(isInLibraryRule).and(hasIdCardRule).and(isAllowedInLibrary));

        Rule hasLiquorRule = new PlayerHasItemRule(game, this.getItem(LIQUOR_NAME));
        this.addRule(SHOW_LIQUOR_RULE, hasLiquorRule.and(isInLibraryRule));
    }

    private void setRulesErrorMessage() {
        for (String ruleName : this.rules.keySet()) {
            Rule rule = this.getRule(ruleName);
            rule.setErrorMessage(ERR_MSG);
        }
    }

    @SuppressWarnings("unchecked")
    private GameRandom<String> getGameRandom() {
        GameRandom<String> gameRandom = this.gameRandom != null ? (GameRandom<String>) this.gameRandom : new GameRandomImpl<>();
        return gameRandom;
    }

    private void createActions() {
        this.createActions1();
        //Action showIdCardAction = new SwitchItemOwnerAction(game.getPlayer(), this.getItemKeeper(LIBRARIAN_NAME), ID_CARD_NAME);
        //this.addAction(SHOW_ID_CARD_ACTION, showIdCardAction);

        Action showLiquorAction = new SwitchItemFromPlayerAction(game, this.getItemKeeper(LIBRARIAN_NAME), LIQUOR_NAME);
        this.addAction(SHOW_LIQUOR_ACTION, showLiquorAction);

        Action unlockLibraryAction = new SetStateValueAction(this.getStage(LIBRARY_NAME), LOCK_STATUS, UNLOCKED);
        this.addAction(UNLOCK_LIBRARY_ACTION, unlockLibraryAction);

        Action sleepLibraryAction = new SetStateValueAction(this.getStage(LIBRARY_NAME), SLEEP_STATUS, SLEEP_STATUS_SLEPT);
        this.addAction(SLEEP_LIBRARIAN_ACTION, sleepLibraryAction);

        Action awakeLibraryAction = new SetStateValueAction(this.getStage(LIBRARY_NAME), SLEEP_STATUS, SLEEP_STATUS_AWAKE);

        // Si no le configuraron un GameRandom al GameBuilder, creo uno para pasarle a este Action.
        GameRandom<String> gameRandom = this.getGameRandom();
        Action randomChangeStageLibrarian = new RandomChangeStageAction(this.game, LIBRARIAN_NAME, gameRandom);

        Action scheduledRandomWalkLibrarianAction = new PeriodicTimedAction(this.game, 0L,//
                RANDOM_WALKER_PERIOD, randomChangeStageLibrarian, "Librarian have moved.");
        this.addAction(RANDOM_WALK_LIBRARIAN_ACTION, scheduledRandomWalkLibrarianAction);

        Action combinedAction = new CombinedAction(Arrays.asList(awakeLibraryAction, scheduledRandomWalkLibrarianAction));
        Action scheduledAwakeLibrarianAction = new TimedAction(this.game, AWAKE_TIME, combinedAction, "Librarian is awake.");
        this.addAction(AWAKE_LIBRARIAN_ACTION, scheduledAwakeLibrarianAction);

        Action useStairsAction = new SetPlayerStateValueAction(game, LIFE_STATUS, DEAD_PLAYER);
        this.addAction(USE_STAIRS_ACTION, useStairsAction);

        Action useRailingAction = new ChangePlayerStageAction(this.game, this.getStage(BASEMENT_DOWNSTAIRS_NAME));
        this.addAction(USE_RAILING_ACTION, useRailingAction);
    }

    private void createActions1() {
        Action moveBoatPictureAction = new AddItemAction(this.getStage(ROOM1_NAME), this.getItem(SAFEBOX_NAME));
        this.addAction(MOVE_BOATPICTURE_ACTION, moveBoatPictureAction);

        Action moveOldBookAction = new SetStateValueAction(this.getItem(OLD_BOOK_NAME), OLD_BOOK_STATE, MOVED_BOOK);
        this.addAction(MOVE_OLD_BOOK_ACTION, moveOldBookAction);

        Action unlockBasementAction = new SetStateValueAction(this.getStage(BASEMENT_NAME), LOCK_STATUS, UNLOCKED);
        this.addAction(UNLOCK_BASEMENT_ACTION, unlockBasementAction);

        Action openSafeboxAction = new SwitchItemOwnerAction(this.getItemKeeper(SAFEBOX_NAME), this.getStage(ROOM1_NAME), ID_CARD_NAME);
        this.addAction(OPEN_SAFEBOX_ACTION, openSafeboxAction);

        Action putPictureAction = new SetStateValueAction(this.getItem(ID_CARD_NAME), ID_CARD_PICTURE_STATE, PLAYER_PICTURE_NAME);
        this.addAction(PUT_PICTURE_ACTION, putPictureAction);

        Action breakWindowAction = new SetStateValueAction(this.getItem(WINDOW_NAME), WINDOW_STATE, BROKEN_WINDOW);
        this.addAction(BREAK_WINDOW_ACTION, breakWindowAction);

        Action unlockOutsideAction = new SetStateValueAction(this.getStage(OUTSIDE_NAME), LOCK_STATUS, UNLOCKED);
        this.addAction(UNLOCK_OUTSIDE_ACTION, unlockOutsideAction);

        Action showIdCardAction = new SwitchItemFromPlayerAction(this.game, this.getItemKeeper(LIBRARIAN_NAME), ID_CARD_NAME);
        this.addAction(SHOW_ID_CARD_ACTION, showIdCardAction);

        Action banPlayerFromLibrary = new SetPlayerStateValueAction(game, ALLOWED_IN_LIBRARY_STATUS, NOT_ALLOWED);
        this.addAction(BAN_PLAYER_FROM_LIBRARY_ACTION, banPlayerFromLibrary);
    }

    private void bindActionsAndItems() {
        this.bindActionsAndItems1();

        Behavior behavior = Behavior.builder()
                .actionName(SHOW)
                .executionRule(this.getRule(SHOW_ID_CARD_RULE))
                .actions(this.getAction(SHOW_ID_CARD_ACTION), this.getAction(UNLOCK_LIBRARY_ACTION))
                .alternativeExecutionRule(this.getRule(SHOW_WRONG_ID_CARD_RULE))
                .alternativeActions(this.getAction(BAN_PLAYER_FROM_LIBRARY_ACTION))
                .resultMessage(SHOW_RESULT_MSG)
                .alternativeResultMessage(SHOW_ALT_RESULT_MSG)
                .build();
        this.getItem(ID_CARD_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(SHOW)
                .executionRule(this.getRule(SHOW_LIQUOR_RULE))
                .actions(this.getAction(SHOW_LIQUOR_ACTION), this.getAction(UNLOCK_LIBRARY_ACTION),//
                        this.getAction(SLEEP_LIBRARIAN_ACTION), this.getAction(AWAKE_LIBRARIAN_ACTION))
                .resultMessage(SHOW_LIQUOR_MSG)
                .build();
        this.getItem(LIQUOR_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(MOVE)
                .executionRule(this.getRule(MOVE_OLD_BOOK_RULE))
                .actions(this.getAction(MOVE_OLD_BOOK_ACTION), this.getAction(UNLOCK_BASEMENT_ACTION))
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

        this.addDropBehaviorToDroppableItems();
        this.addPickBehaviorToPickableItems();
    }

    private void bindActionsAndItems1() {
        Behavior behavior = Behavior.builder()
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
                .actionName(PUT)
                .executionRule(this.getRule(PUT_PICTURE_RULE))
                .actions(this.getAction(PUT_PICTURE_ACTION))
                .resultMessage(String.format(PUT_RESULT_MSG, PLAYER_PICTURE_NAME, ID_CARD_NAME))
                .build();
        this.getItem(PLAYER_PICTURE_NAME).addBehavior(behavior);

        behavior = Behavior.builder()
                .actionName(BREAK)
                .executionRule(this.getRule(BREAK_WINDOW_RULE))
                .actions(this.getAction(BREAK_WINDOW_ACTION), this.getAction(UNLOCK_OUTSIDE_ACTION))
                .resultMessage(BREAK_WINDOW_RESULT_MSG)
                .build();
        this.getItem(WINDOW_NAME).addBehavior(behavior);
    }

    private List<String> fillPickableAndDroppableItemsList() {
        List<String> itemNames = new ArrayList<>();
        itemNames.addAll(this.somePickableItems());
        itemNames.add(BOOK3_NAME);
        itemNames.add(BOOK4_NAME);
        itemNames.add(BOOK5_NAME);
        itemNames.add(BOOK6_NAME);
        itemNames.add(BOOK7_NAME);
        itemNames.add(BOOK8_NAME);
        itemNames.add(BOOK9_NAME);
        itemNames.add(HAMMER_NAME);
        itemNames.add(PLAYER_PICTURE_NAME);
        itemNames.add(PEN_NAME);
        itemNames.add(KEY_NAME);
        return itemNames;
    }

    private List<String> somePickableItems() {
        List<String> itemNames = new ArrayList<>();
        itemNames.add(LIQUOR_NAME);
        itemNames.add(GLASS1_NAME);
        itemNames.add(GLASS2_NAME);
        itemNames.add(ID_CARD_NAME);
        itemNames.add(SCREWDRIVER1_NAME);
        itemNames.add(SCREWDRIVER2_NAME);
        itemNames.add(BOOK1_NAME);
        itemNames.add(BOOK2_NAME);
        return itemNames;
    }

    private void addPickBehaviorToPickableItems() {
        List<String> pickableItemsNames = fillPickableAndDroppableItemsList();

        for (String itemName : pickableItemsNames) {
            Behavior behavior = Behavior.builder()
                    .actionName(PICK)
                    .executionRule(new IsInCurrentRoomRule(this.game, itemName))
                    .actions(new PlayerPickFromCurrentStageAction(this.game, itemName))
                    .resultMessage(PICK_RESULT_MSG)
                    .build();
            this.getItem(itemName).addBehavior(behavior);
        }
    }

    private void addDropBehaviorToDroppableItems() {
        List<String> droppableItemsNames = fillPickableAndDroppableItemsList();

        for (String itemName : droppableItemsNames) {
            Behavior behavior = Behavior.builder()
                    .actionName(DROP)
                    .executionRule(new PlayerHasItemRule(game, this.getItem(itemName)))
                    .actions(new DropInCurrentStageAction(this.game, itemName))
                    .resultMessage(String.format(DROP_RESULT_MSG, itemName))
                    .build();
            this.getItem(itemName).addBehavior(behavior);
        }
    }

    private void bindStagesAndActions() {
        // todos los escenarios soportan comando goto.
        for (Stage stage : this.stages) {
            IsNextRoomRule isNextRoomRule = new IsNextRoomRule(this.game, stage.getName());
            isNextRoomRule.setErrorMessage(String.format(GOTO_NOT_NEXT_ROOM_MSG, stage.getName()));
            VerifiesStateRule isUnlockedRule = new VerifiesStateRule(stage, LOCK_STATUS, UNLOCKED);

            Behavior behavior = Behavior.builder()
                    .actionName(GOTO)
                    .executionRule(isUnlockedRule.and(isNextRoomRule))
                    .actions(new ChangePlayerStageAction(this.game, stage))
                    .resultMessage(String.format(GOTO_RESULT_MSG, stage.getName()))
                    .build();
            stage.addBehavior(behavior);
        }

        // configurando acceso a biblioteca
        Stage library = this.getStage(LIBRARY_NAME);
        Rule isNextRoomRule = new IsNextRoomRule(this.game, library.getName());
        Rule isSlept = new VerifiesStateRule(this.getItem(LIBRARIAN_NAME), SLEEP_STATUS, SLEEP_STATUS_SLEPT);
        Rule isNotInLibraryAccess = new IsInCurrentRoomRule(this.game, LIBRARIAN_NAME).negate();
        Rule isAllowed = new VerifyPlayerStateRule(this.game, ALLOWED_IN_LIBRARY_STATUS, ALLOWED);

        Rule libraryAccessRule = isNextRoomRule.and(isSlept.or(isNotInLibraryAccess.or(isAllowed)));

        Behavior libraryBehavior = library.getBehavior(GOTO);
        libraryBehavior.setExecutionRule(libraryAccessRule);

    }

    private void configureStagesAndItems() {
        this.configureHall();
        this.configureRoom1();
        this.configureRoom2();
        this.configureRoom3();
        this.configureLibraryAccess();
        this.configureLibrary();
        this.configureBasement();
        this.configureBasementDownstairs();
        this.configureOutside();
    }

    private void configureHall() {
        Stage hall = this.getStage(HALL_NAME);
        hall.addState(LOCK_STATUS, UNLOCKED);
    }

    private void configureRoom1() {
        Stage room1 = this.getStage(ROOM1_NAME);
        room1.addItem(this.getItem(BOAT_PICTURE_NAME));
        room1.addItem(this.getItem(TRAIN_PICTURE_NAME));
        room1.addItem(this.getItem(TABLE_NAME));
        room1.addItem(this.getItem(GLASS1_NAME));
        room1.addItem(this.getItem(GLASS2_NAME));
        room1.addItem(this.getItem(CHAIR1_NAME));
        room1.addItem(this.getItem(CHAIR2_NAME));
        room1.addItem(this.getItem(SAFEBOX_NAME));
        room1.addItem(this.getItem(LIQUOR_NAME));
        room1.addState(LOCK_STATUS, UNLOCKED);
    }

    private void configureRoom2() {
        Stage room2 = this.getStage(ROOM2_NAME);
        room2.addItem(this.getItem(HAMMER_NAME));
        room2.addItem(this.getItem(SCREWDRIVER1_NAME));
        room2.addItem(this.getItem(SCREWDRIVER2_NAME));
        room2.addState(LOCK_STATUS, UNLOCKED);
    }

    private void configureRoom3() {
        Stage room3 = this.getStage(ROOM3_NAME);
        room3.addItem(this.getItem(KEY_NAME));
        room3.addState(LOCK_STATUS, UNLOCKED);
    }

    private void configureLibraryAccess() {
        Stage libraryAccess = this.getStage(LIBRARY_ACCESS_NAME);
        libraryAccess.addItem(this.getItem(LIBRARIAN_NAME));
        libraryAccess.addState(LOCK_STATUS, UNLOCKED);
    }

    private void configureLibrary() {
        Stage library = this.getStage(LIBRARY_NAME);
        library.addItem(this.getItem(OLD_BOOK_NAME));
        library.addState(LOCK_STATUS, LOCKED);
    }

    private void configureBasement() {
        Stage basement = this.getStage(BASEMENT_NAME);
        basement.addItem(this.getItem(STAIRS_NAME));
        basement.addItem(this.getItem(RAILING_NAME));
        basement.addState(LOCK_STATUS, LOCKED);
    }

    private void configureBasementDownstairs() {
        Stage basementDownstairs = this.getStage(BASEMENT_DOWNSTAIRS_NAME);
        basementDownstairs.addItem(this.getItem(WINDOW_NAME));
        basementDownstairs.addItem(this.getItem(STAIRS_NAME));
        basementDownstairs.addItem(this.getItem(RAILING_NAME));
        basementDownstairs.addState(LOCK_STATUS, LOCKED);
    }

    private void configureOutside() {
        Stage outside = this.getStage(OUTSIDE_NAME);
        outside.addState(LOCK_STATUS, LOCKED);
    }

    private void createItems() {
        EscapeItemsCreator escapeItemsCreator = new EscapeItemsCreator();
        List<Item> createdItems = escapeItemsCreator.create();
        for (Item createdItem : createdItems) {
            this.addItem(createdItem);
        }
    }

    private void setWinningCondition() {
        game.setWinningCondition(new PlayerIsInRoomRule(game, OUTSIDE_NAME));
    }

    private void setLosingCondition() {
        Rule isDead = new VerifyPlayerStateRule(game, LIFE_STATUS, DEAD_PLAYER);
        Item hammer = this.getItem(HAMMER_NAME);
        Rule hasntHammer = new PlayerHasItemRule(game, hammer).negate();
        Rule isInDownBasement = new PlayerIsInRoomRule(game, BASEMENT_DOWNSTAIRS_NAME);

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
        createStage(BASEMENT_DOWNSTAIRS_NAME, OUTSIDE_NAME);
        createStage(OUTSIDE_NAME);
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
//        game.registerKnownAction(ActionOld.PUT, new ParametizedActionHandler(this.game));
        game.registerKnownAction(ActionOld.PUT, new DefaultActionHandler(this.game));
        game.registerKnownAction(ActionOld.BREAK, new DefaultActionHandler(this.game));
        game.registerKnownAction(ActionOld.SHOW, new DefaultActionHandler(this.game));
        game.registerKnownAction(ActionOld.USE, new DefaultActionHandler(this.game));
        game.registerKnownAction(ActionOld.DROP, new DefaultActionHandler(this.game));
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
        // TODO: falta sacar, lo dejo para que pasen los test
        player.addState(LIFE_STATUS, ALIVE_PLAYER);
        player.addState(ALLOWED_IN_LIBRARY_STATUS, ALLOWED);
        player.setCurrentStage(HALL_NAME);
        player.addToInventory(this.getItem(PLAYER_PICTURE_NAME));
        player.addToInventory(this.getItem(PEN_NAME));
        game.setPlayer(player);
    }

    private void configurePlayerManager() {

        PlayerCreator playerCreator = new PlayerCreator();

        playerCreator.setInitialRoom(HALL_NAME);
        playerCreator.setItems(Arrays.asList(this.getItem(PLAYER_PICTURE_NAME), this.getItem(PEN_NAME)));

        Map<String, String> states = new HashMap<>();
        states.put(LIFE_STATUS, ALIVE_PLAYER);
        states.put(ALLOWED_IN_LIBRARY_STATUS, ALLOWED);

        playerCreator.setStates(states);

        this.playerManager = new PlayerManager(playerCreator, true, 4);
        this.game.setPlayerManager(this.playerManager);
    }

}
