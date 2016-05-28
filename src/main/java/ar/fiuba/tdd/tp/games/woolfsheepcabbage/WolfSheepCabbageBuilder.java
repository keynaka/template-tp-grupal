package ar.fiuba.tdd.tp.games.woolfsheepcabbage;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.items.Item;

import java.util.function.Predicate;

/**
 * Created by Fede on 17/05/2016.
 */
public class WolfSheepCabbageBuilder implements GameBuilder {
    @SuppressWarnings("CPD-START")
    private ConcreteGame wolfSheepCabbage;

    @Override
    public Game build() {
        wolfSheepCabbage = new ConcreteGame();

        wolfSheepCabbage.setName("Wolf, Sheep and Cabbage");
        wolfSheepCabbage.setEndGameMessage("You won the game!");
        wolfSheepCabbage.setGameDescription("Welcome to: Wolf, Sheep and Cabbage. Accepted commands: look around, cross, take, leave");
        wolfSheepCabbage.setPlayer(this.buildPlayer());
        wolfSheepCabbage.addStage(this.buildNorthShore());
        wolfSheepCabbage.addStage(this.buildSouthShore());
        wolfSheepCabbage.setWinningCondition(this.buildWinningCondition());
        registerKnownActions(wolfSheepCabbage);
        return wolfSheepCabbage;
    }

    private Predicate<ConcreteGame> buildWinningCondition() {
        return (concreteGame) -> {
            return concreteGame.getCurrentStage().getName().equals("NorthShore")
                    && concreteGame.getCurrentStage().getItemContainer().getSize() == 3;
        };
    }

    private void registerKnownActions(ConcreteGame game) {
        game.registerKnownAction(ActionOld.LOOK_AROUND, (command) -> this.lookAroundHandler(game));
        game.registerKnownAction(ActionOld.CROSS, (command) -> this.crossHandler(game, command.getItemName()));
        game.registerKnownAction(ActionOld.TAKE, (command) -> this.takeHandler(game, command.getItemName()));
        game.registerKnownAction(ActionOld.LEAVE, (command) -> this.leaveHandler(game, command.getItemName()));
    }

    private String lookAroundHandler(ConcreteGame game) {
        return game.getCurrentStage().lookAround();
    }

    private Player buildPlayer() {
        Player player = new Player();
        player.setCurrentStage("SouthShore");
        return player;
    }

    private Stage buildNorthShore() {
        return new Stage("NorthShore");
    }

    private Stage buildSouthShore() {
        Stage stage = new Stage("SouthShore");
        stage.addItems(this.buildWolf(), this.buildSheep(), this.buildCabbage());
        return stage;
    }

    private String crossHandler(ConcreteGame game, String item) {

        String validateShore = this.validateShore(game, item);
        if (!validateShore.equals("Ok")) {
            return validateShore;
        }

        String validateEatSituation = this.validateEatSituation(game);
        if (!validateEatSituation.equals("Ok")) {
            return validateEatSituation;
        }

        return this.crossToShore(game, item);

    }

    private String validateShore(ConcreteGame game, String item) {

        if (item.equals("north-shore")) {
            if (!this.isBoatAtSouth(game)) {
                return "You are at north-shore!";
            }
        } else if (item.equals("south-shore")) {
            if (this.isBoatAtSouth(game)) {
                return "You are at south-shore!";
            }
        } else {
            return "Unknown location";
        }
        return "Ok";
    }

    private String crossToShore(ConcreteGame game, String item) {
        //Posible mejora
        if (item.equals("north-shore")) {
            game.getPlayer().setCurrentStage("NorthShore");
        }

        if (item.equals("south-shore")) {
            game.getPlayer().setCurrentStage("SouthShore");
        }
        return "You have crossed";
    }

    private String validateEatSituation(ConcreteGame game) {

        for (Item transportableToCompare : game.getCurrentStage().getItemContainer().getAllItems()) {
            for (Item transportable : game.getCurrentStage().getItemContainer().getAllItems()) {
                if (this.firstEatsSecond(transportableToCompare, transportable)) {
                    return "You can't do that. The " + transportableToCompare.getName()
                            + " will eat the " + transportable.getName();
                } else if (this.firstEatsSecond(transportable, transportableToCompare)) {
                    return "You can't do that. The " + transportable.getName()
                            + " will eat the " + transportableToCompare.getName();
                }
            }
        }
        return "Ok";
    }

    private boolean firstEatsSecond(Item first, Item second) {
        return ((first.getName().equals("wolf") && second.getName().equals("sheep"))
                || (first.getName().equals("sheep") && second.getName().equals("cabbage")));
    }

    private String takeHandler(ConcreteGame game, String item) {
        if (game.getPlayer().getInventory().size() > 0) {
            return "You can't do that. The boat is full";
        }
        return this.takeTransportable(game, item);
    }

    private String takeTransportable(ConcreteGame game, String itemName) {
        if (!game.getCurrentStage().getItemContainer().contains(itemName)) {
            return "The shore doesn't have that object";
        }

        game.getCurrentStage().pickItem(itemName);
        game.getPlayer().addToInventory(new Item(itemName, itemName));

        return "Ok";
    }

    private String leaveHandler(ConcreteGame game, String item) {
        if (game.getPlayer().getInventory().size() == 0) {
            return "You can't do that. The boat is empty";
        }
        return this.leaveTransportable(game, item);

    }

    private String leaveTransportable(ConcreteGame game, String itemName) {

        if (!game.getPlayer().getInventory().contains(itemName)) {
            return "The boat doesn't have that object";
        }

        game.getCurrentStage().addItem(new Item(itemName, itemName));
        game.getPlayer().getInventory().dropItem(itemName);

        return "Ok";
    }


    private Item buildWolf() {
        return new Item("wolf", "Its a Wolf");
    }

    private Item buildSheep() {
        return (new Item("sheep", "Its a Sheep"));
    }

    private Item buildCabbage() {
        return new Item("cabbage", "Its a Cabbage");
    }

    private boolean isBoatAtSouth(ConcreteGame game) {
        return game.getCurrentStage().getName().equals("SouthShore");
    }
}
