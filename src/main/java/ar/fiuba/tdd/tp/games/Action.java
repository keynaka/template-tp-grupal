package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/22/16.
 */
public enum Action {

    LOOK_AROUND("look around"),
    PICK("pick"),
    OPEN("open"),
    UNKNOWNACTION("");


    private String actionName;

    Action(String actionName) {
        this.actionName = actionName;
    }


}
