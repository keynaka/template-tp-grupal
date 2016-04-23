package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/22/16.
 */
public class Command {
    private Action action;
    private String itemName;

    public Command(Action action, String itemName) {
        this.action = action;
        this.itemName = itemName;
    }

    public Action getAction() {
        return this.action;
    }

    public String getItemName() {
        return this.itemName;
    }

}
