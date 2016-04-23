package ar.fiuba.tdd.tp.games.woolfsheepcabbage;

import ar.fiuba.tdd.tp.games.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Fede on 23/04/2016.
 */
public class WolfSheepCabbage implements Game {

    private Shore northShore;
    private Shore southShore;
    private boolean boatAtSouth;

    @Override
    public String start() {
        List<Transportable> southTransportables = Arrays.asList(new Wolf(), new Sheep(), new Cabbage());
        List<Transportable> northTransportables = new ArrayList<>();
        this.southShore = new Shore("SouthShore", southTransportables);
        this.northShore = new Shore("NorthShore", northTransportables);
        this.boatAtSouth = true;

        return "Welcome to " + getDescription();
    }

    @Override
    public String play(Command command) {
        String result = null;
        switch (command.getAction()) {
            case LOOK_AROUND:
                result = this.lookAround();
                break;
            case CROSS:
                result = this.cross(command.getItemName());
                break;
            case TAKE:
                result = this.take(command.getItemName());
                break;
            case LEAVE:
                result = this.leave(command.getItemName());
                break;
            default:
                result = "Unknown command.";
        }
        if (this.isFinished()) {
            result = "You won the game!";
        }


        return result;
    }

    @Override
    public boolean isFinished() {
        return this.northShore.getTransportables().size() == 3;
    }

    @Override
    public String getDescription() {
        return "Wolf, Sheep and Cabbage";
    }

    private String lookAround() {
        if (this.boatAtSouth) {
            return southShore.lookAround();
        } else {
            return northShore.lookAround();
        }
    }

    private String cross(String item) {
        //TODO
        return "";
    }

    private String take(String item) {
        //TODO
        return "";
    }

    private String leave(String item) {
        //TODO
        return "";
    }
}

