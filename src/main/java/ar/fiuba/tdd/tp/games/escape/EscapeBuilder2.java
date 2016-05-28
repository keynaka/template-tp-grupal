package ar.fiuba.tdd.tp.games.escape;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.behavior.BehaviorView;
import ar.fiuba.tdd.tp.games.exceptions.GameException;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;
import ar.fiuba.tdd.tp.red.server.Command;

import java.util.function.Predicate;

/**
 * Created by swandelow on 5/19/16.
 */

public class EscapeBuilder2 extends AbstractGameBuilder {


    @Override
    protected void buildEnvironment() {

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

    }
}
