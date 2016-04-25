package ar.fiuba.tdd.tp.games.woolfsheepcabbage;

import ar.fiuba.tdd.tp.games.*;

import java.util.*;

/**
 * Created by Fede on 23/04/2016.
 */
public class WolfSheepCabbage extends AbstractGame {


    private Shore northShore;
    private Shore southShore;
    private boolean boatAtSouth;
    private Transportable transportableAtboat;

    public WolfSheepCabbage() {
        super("Wolf, Sheep and Cabbage", "You won the game!");
    }

    @Override
    protected void doStart() {
        ArrayList<Transportable> southTransportables = new ArrayList<Transportable>();
        southTransportables.add(new Wolf());
        southTransportables.add(new Sheep());
        southTransportables.add(new Cabbage());

        ArrayList<Transportable> northTransportables = new ArrayList<>();
        this.southShore = new Shore("SouthShore", southTransportables);
        this.northShore = new Shore("NorthShore", northTransportables);
        this.boatAtSouth = true;
    }

    @Override
    protected void registerKnownActions() {
        this.knownActions.put(Action.LOOK_AROUND, (itemName) -> this.lookAround());
        this.knownActions.put(Action.CROSS, (itemName) -> this.cross(itemName));

        /* To avoid code duplication warning */
        this.registerMoreActions();
    }

    private void registerMoreActions() {
        this.knownActions.put(Action.TAKE, (itemName) -> this.take(itemName));
        this.knownActions.put(Action.LEAVE, (itemName) -> this.leave(itemName));
    }

    @Override
    public boolean isFinished() {
        return this.northShore.getTransportables().size() == 3;
    }

    @Override
    public String getDescription() {
        return "Wolf, Sheep and Cabbage";
    }

    private Shore getActualShore (){
        if (boatAtSouth){
            return southShore;
        }else{
            return northShore;
        }
    }

    private boolean shoreHasTransportable (Transportable transportableToFind){
        boolean result = false;
        for (Transportable transportable : this.getActualShore().getTransportables()) {
            if (transportableToFind.getClass() == transportable.getClass()) {
                return true;
            }
        }
        return result;
    }

    private String lookAround() {
        if (this.boatAtSouth) {
            return southShore.lookAround();
        } else {
            return northShore.lookAround();
        }
    }

    private String cross(String item) {

        if (!item.equals("north-shore") && !item.equals("south-shore")){
            return "Unknown location";
        }

        if (item.equals("north-shore") && !this.boatAtSouth){
            return "You are at north-shore!";
        }

        if (item.equals("south-shore") && this.boatAtSouth){
            return "You are at south-shore!";
        }

        String validateEatSituation = this.validateEatSituation();
        if (!validateEatSituation.equals("Ok")){
            return validateEatSituation;
        }

        if (item.equals("north-shore")){
            this.boatAtSouth = false;
        }

        if (item.equals("south-shore")){
            this.boatAtSouth = true;
        }
        return "You have crossed";
    }

    private String validateEatSituation () {
        for (Transportable transportableToCompare : this.getActualShore().getTransportables()) {
            for (Transportable transportable : this.getActualShore().getTransportables()) {
                if (transportableToCompare.eats(transportable)) {
                    return "You can't do that. The " + transportableToCompare.getDescription() + " will eat the " + transportable.getDescription ();
                } else if (transportable.eats(transportableToCompare)) {
                    return "You can't do that. The " + transportable.getDescription() + " will eat the " + transportableToCompare.getDescription ();
                }
            }
        }
        return "Ok";
    }

    private String take(String item) {
        if (this.transportableAtboat != null){
            return "You can't do that. The boat is full";
        }
        if (item.equals("wolf") && shoreHasTransportable (new Wolf())){
            this.transportableAtboat= new Wolf();
            this.removeTransportable (new Wolf());
        } else if (item.equals("cabbage") && shoreHasTransportable (new Cabbage())) {
                this.transportableAtboat = new Cabbage();
                this.removeTransportable (new Cabbage());
            } else if (item.equals("sheep") && shoreHasTransportable (new Sheep())) {
                    this.transportableAtboat = new Sheep();
                    this.removeTransportable (new Sheep());
            }else {
                return "The shore doesn't have that object";
            }

        return "Ok";
    }

    private void removeTransportable(Transportable transportableToFind) {

        ListIterator<Transportable> iterator = this.getActualShore().getTransportables().listIterator();
        while(iterator.hasNext()){
            Transportable transportable = iterator.next();
            if (transportableToFind.getClass() == transportable.getClass()) {
                iterator.remove();
            }
        }
    }

    private String leave(String item) {
        if (this.transportableAtboat == null){
            return "You can't do that. The boat is empty";
        }
        if (item.equals("wolf") && transportableAtboat instanceof  Wolf){
            this.transportableAtboat= null;
            return this.addTransportable (new Wolf());
        } else if (item.equals("cabbage") && transportableAtboat instanceof  Cabbage) {
            this.transportableAtboat = null;
            return this.addTransportable (new Cabbage());
        } else if (item.equals("sheep") && transportableAtboat instanceof  Sheep) {
            this.transportableAtboat =null;
            return this.addTransportable (new Sheep());
        }else {
            return "The boat doesn't have that object";
        }

    }

    private String addTransportable(Transportable transportableToAdd) {
        this.getActualShore().getTransportables().add(transportableToAdd);
        return "Ok";
    }

}

