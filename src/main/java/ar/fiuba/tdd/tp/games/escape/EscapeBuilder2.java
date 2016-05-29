package ar.fiuba.tdd.tp.games.escape;

import ar.fiuba.tdd.tp.games.AbstractGameBuilder;
import ar.fiuba.tdd.tp.games.ActionOld;
import ar.fiuba.tdd.tp.games.Stage;
import ar.fiuba.tdd.tp.games.actions.Action;
import ar.fiuba.tdd.tp.games.actions.SwitchItemOwnerAction;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.exceptions.GameException;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.rules.HasItemRule;
import ar.fiuba.tdd.tp.games.rules.PlayerIsInRoomRule;
import ar.fiuba.tdd.tp.games.rules.Rule;
import ar.fiuba.tdd.tp.games.rules.VerifiesStateRule;
import ar.fiuba.tdd.tp.red.server.Command;

import static ar.fiuba.tdd.tp.games.escape.EscapeProperties.*;

/**
 * Created by swandelow on 5/19/16.
 */

public class EscapeBuilder2 extends AbstractGameBuilder {

    private static final String PICK_KEY_ACTION = "pickKeyAction";


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
    }

    private void createActions() {
        Action pickKeyAction = new SwitchItemOwnerAction(this.getStage(ROOM3_NAME), this.player, KEY_NAME);
        this.addAction(PICK_KEY_ACTION, pickKeyAction);
    }

    private void bindActionsAndItems() {
        Behavior behavior = Behavior.builder()
                .actionName(PICK)
                .actions(this.getAction(PICK_KEY_ACTION))
                .resultMessage(PICK_RESULT_MSG)
                .build();
        this.getItem(KEY_NAME).addBehavior(behavior);
    }

    private void configureStagesAndItems() {
        this.configureRoom1();
        this.configureRoom3();
    }

    private void configureRoom1() {
        Stage room1 = this.getStage("Salon1");
        room1.addItem(this.getItem("Mesa"));
    }

    private void configureRoom3() {
        Stage room3 = this.getStage(ROOM3_NAME);
        room3.addItem(this.getItem(KEY_NAME));
    }

    private void createItems() {
        this.addItem(new Item("Martillo", HAMMER_DESCRIPTION));
        this.addItem(new Item(KEY_NAME, KEY_DESCRIPTION));
        this.addItem(new Item("Mesa", TABLE_DESCRIPTION));
        this.addItem(new Item("BotellaLicor", LIQUOR_DESCRIPTION));
        this.addItem(new Item("Vaso1", GLASS_DESCRIPTION));
        this.addItem(new Item("Vaso2", GLASS_DESCRIPTION));
        this.addItem(new Item("CuadroBarco", BOAT_PICTURE_DESCRIPTION));
        this.addItem(new Item("Destornillador1", SCREWDRIVER_DESCRIPTION));
        this.addItem(new Item("Destornillador2", SCREWDRIVER_DESCRIPTION));
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
//        game.addStage(createStage("Pasillo", "Salon1", "Salon2", "Salon3", "BibliotecaAcceso"));
//        game.addStage(createStage("Salon1", "Pasillo"));
//        game.addStage(createStage("Salon2", "Pasillo"));
//        game.addStage(createStage("Salon3", "Pasillo"));
//        game.addStage(createStage("BibliotecaAcceso", "Pasillo", "Biblioteca"));
//        game.addStage(createStage("Biblioteca", "BibliotecaAcceso", "Sotano"));
//        game.addStage(createStage("Sotano", "Biblioteca", "Sotano"));
//        game.addStage(createStage("SotanoAbajo", "Afuera"));
//        game.addStage(createStage("Afuera"));
        createStage("Pasillo", "Salon1", "Salon2", ROOM3_NAME, "BibliotecaAcceso");
        createStage("Salon1", "Pasillo");
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
        return game.getCurrentStage().getItem(command.getItemName()).executeAction(command.getAction().getActionName());
    }

    @Override
    protected void configurePlayer() {
        player.addState("lifeStatus", "alive");
        player.setCurrentStage("Pasillo");
        player.addToInventory(new Item("Foto", "picture"));
        player.addToInventory(new Item("Lapicera", "pen"));
    }
}
