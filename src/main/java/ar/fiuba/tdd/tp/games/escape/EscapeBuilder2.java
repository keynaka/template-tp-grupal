package ar.fiuba.tdd.tp.games.escape;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.behavior.BehaviorView;
import ar.fiuba.tdd.tp.games.exceptions.GameException;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;
import ar.fiuba.tdd.tp.games.rules.HasItemRule;
import ar.fiuba.tdd.tp.games.rules.PlayerIsInRoomRule;
import ar.fiuba.tdd.tp.games.rules.Rule;
import ar.fiuba.tdd.tp.games.rules.VerifiesStateRule;
import ar.fiuba.tdd.tp.red.server.Command;

import java.util.function.Predicate;

/**
 * Created by swandelow on 5/19/16.
 */

public class EscapeBuilder2 extends AbstractGameBuilder {


    @Override
    protected void buildEnvironment() {
        game.setName("Escape");
        this.createStages();
        this.createItems();
        this.configurePlayer();
        setWinningCondition();
        setLosingCondition();
    }

    private void createItems() {
        this.addItem(new Item("Martillo", "Es un martillo."));
        this.addItem(new Item("Llave", "Es una llave."));
        this.addItem(new Item("Mesa", "Es una mesa."));
        this.addItem(new Item("BotellaLicor", "Es una llave."));
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
        game.addStage(createStage("Pasillo", "Salon1", "Salon2", "Salon3", "BibliotecaAcceso"));
        game.addStage(createStage("Salon1", "Pasillo"));
        game.addStage(createStage("Salon2", "Pasillo"));
        game.addStage(createStage("Salon3", "Pasillo"));
        game.addStage(createStage("BibliotecaAcceso", "Pasillo", "Biblioteca"));
        game.addStage(createStage("Biblioteca", "BibliotecaAcceso", "Sotano"));
        game.addStage(createStage("Sotano", "Biblioteca", "Sotano"));
        game.addStage(createStage("SotanoAbajo", "Afuera"));
        game.addStage(createStage("Afuera"));
    }

    private Stage createStage(String stageName, String... adjacentStages) {
        Stage stage = new Stage(stageName);
        for (String adjacentStage : adjacentStages) {
            stage.addConsecutiveStage(adjacentStage);
        }
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
