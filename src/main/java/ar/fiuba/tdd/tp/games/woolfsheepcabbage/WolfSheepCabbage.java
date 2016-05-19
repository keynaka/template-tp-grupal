package ar.fiuba.tdd.tp.games.woolfsheepcabbage;

import ar.fiuba.tdd.tp.games.AbstractGame;
import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.Stage;
import ar.fiuba.tdd.tp.games.items.Item;

import java.util.Iterator;

/**
 * Created by Fede on 07/05/2016.
 */
public class WolfSheepCabbage extends AbstractGame {

    private boolean boatAtSouth;
    private Item transportableAtboat;
    private Stage northShore;
    private Stage southShore;

    public WolfSheepCabbage() {
        super("Wolf, Sheep and Cabbage", "You won the game!");
    }

    @Override
    protected void doStart() {

        this.northShore = new Stage("NorthShore");
        this.southShore = new Stage("SouthShore");
        this.southShore.addItems(this.generateWolf(), this.generateSheep(), this.generateCabbage());
        this.boatAtSouth = true;
    }

    @Override
    protected void registerKnownActions() {
        this.registerLookAround();
        this.knownActions.put(Action.CROSS, (itemName, args) -> this.cross(itemName));
        this.registerMoreActions();
    }

    private void registerLookAround() {
       // this.knownActions.put(Action.LOOK_AROUND, (itemName, args) -> this.lookAround());

    }

    private void registerMoreActions() {
        this.knownActions.put(Action.TAKE, (itemName, args) -> this.take(itemName));
        this.knownActions.put(Action.LEAVE, (itemName, args) -> this.leave(itemName));
    }

    @Override
    public boolean isFinished() {
        return this.northShore.getItemContainer().getSize() == 3;
    }

    @Override
    public String getDescription() {
        return "Welcome to: Wolf, Sheep and Cabbage. Accepted commands: look around, cross, take, leave";
    }

    private Stage getActualShore() {
        if (boatAtSouth) {
            return southShore;
        } else {
            return northShore;
        }
    }

    private boolean shoreHasTransportable(Item transportableToFind) {
        return this.getActualShore().getItemContainer().contains(transportableToFind.getName());
    }

    private Iterator lookAround() {
        if (this.boatAtSouth) {
            return southShore.lookAround();
        } else {
            return northShore.lookAround();
        }
    }

    private String cross(String item) {

        String validateShore = this.validateShore(item);
        if (!validateShore.equals("Ok")) {
            return validateShore;
        }

        String validateEatSituation = this.validateEatSituation();
        if (!validateEatSituation.equals("Ok")) {
            return validateEatSituation;
        }

        return this.crossToShore(item);

    }

    private String validateShore(String item) {

        if (item.equals("north-shore")) {
            if (!this.boatAtSouth) {
                return "You are at north-shore!";
            }
        } else if (item.equals("south-shore")) {
            if (this.boatAtSouth) {
                return "You are at south-shore!";
            }
        } else {
            return "Unknown location";
        }
        return "Ok";
    }

    private String crossToShore(String item) {

        if (item.equals("north-shore")) {
            this.boatAtSouth = false;
        }

        if (item.equals("south-shore")) {
            this.boatAtSouth = true;
        }
        return "You have crossed";
    }

    private String validateEatSituation() {

        for (Item transportableToCompare : this.getActualShore().getItemContainer().getAllItems()) {
            for (Item transportable : this.getActualShore().getItemContainer().getAllItems()) {
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
        return ((first.getName().equals("Wolf") && second.getName().equals("Sheep"))
                || (first.getName().equals("Sheep") && second.getName().equals("Cabbage")));
    }

    private String take(String item) {
        if (this.transportableAtboat != null) {
            return "You can't do that. The boat is full";
        }
        return this.takeTransportable(item);
    }

    private String takeTransportable(String item) {
        if (item.equals("wolf") && shoreHasTransportable(generateWolf())) {
            this.transportableAtboat = generateWolf();
            this.removeTransportable(generateWolf());
        } else if (item.equals("cabbage") && shoreHasTransportable(generateCabbage())) {
            this.transportableAtboat = generateCabbage();
            this.removeTransportable(generateCabbage());
        } else if (item.equals("sheep") && shoreHasTransportable(generateSheep())) {
            this.transportableAtboat = generateSheep();
            this.removeTransportable(generateSheep());
        } else {
            return "The shore doesn't have that object";
        }
        return "Ok";
    }

    private void removeTransportable(Item transportableToFind) {
        this.getActualShore().getItemContainer().extract(transportableToFind.getName());
    }

    private String leave(String item) {
        if (this.transportableAtboat == null) {
            return "You can't do that. The boat is empty";
        }
        return this.leaveTransportable(item);

    }

    private String leaveTransportable(String item) {

        if (item.equals("wolf") && transportableAtboat.getName().equals("Wolf")) {
            this.transportableAtboat = null;
            return this.addTransportable(generateWolf());
        } else if (item.equals("cabbage") && transportableAtboat.getName().equals("Cabbage")) {
            this.transportableAtboat = null;
            return this.addTransportable(generateCabbage());
        } else if (item.equals("sheep") && transportableAtboat.getName().equals("Sheep")) {
            this.transportableAtboat = null;
            return this.addTransportable(generateSheep());
        } else {
            return "The boat doesn't have that object";
        }

    }

    private String addTransportable(Item transportableToAdd) {
        this.getActualShore().getItemContainer().addItem(transportableToAdd);
        return "Ok";
    }

    private Item generateWolf() {
        return new Item("Wolf", "Its a Wolf");
    }

    private Item generateSheep() {
        return (new Item("Sheep", "Its a Sheep"));
    }

    private Item generateCabbage() {
        return new Item("Cabbage", "Its a Cabbage");
    }

}

