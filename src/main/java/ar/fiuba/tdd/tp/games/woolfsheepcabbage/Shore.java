package ar.fiuba.tdd.tp.games.woolfsheepcabbage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fede on 23/04/2016.
 */
public class Shore {

    private ArrayList<Transportable> transportables;
    //private boolean hasBoat;
    private String description;

    public Shore(String description, ArrayList<Transportable> transportables/*, boolean hasBoat*/) {
        this.description = description;
        this.transportables = transportables;
        //this.hasBoat = hasBoat;
    }

    public String getDescripcion() {
        return this.description;
    }

    /*public void setHasBoat(boolean hasBoat) {
        this.hasBoat = hasBoat;
    }

    public boolean getHasBoat() {
        return hasBoat;
    }*/

    private String getTransportablesDescription() {
        StringBuffer result = new StringBuffer("");
        for (Transportable transportable : this.transportables) {
            result.append(transportable.getDescription() + " ");
        }

        return result.toString();
    }

    public String lookAround() {
        return "You are at: " + getDescripcion() + " and have " + getTransportablesDescription();
    }

    public ArrayList<Transportable> getTransportables() {
        return transportables;
    }

}
