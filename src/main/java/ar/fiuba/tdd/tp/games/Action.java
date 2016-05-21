package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/22/16.
 */
public enum Action {

    LOOK_AROUND("look around"),
    PICK("pick"),
    OPEN("open"),
    TAKE("take"),
    LEAVE("leave"),
    CROSS("cross"),
    EXAMINE("examine"),
    SET_DISKS("play with"),
    MOVE_TOP("move top"),
    TOP_SIZE("check top"),
    DROP("drop"),
    TALK("talk"),
    _HELP("help"),
    _EXIT("exit"),
    MOVE("move"),
    GOTO("goto"),
    PUT("put"),
    SHOW("show"),
    USE("use"),
    BREAK("break"),
    UNKNOWN_ACTION("");

    private String actionName;

    Action(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return this.actionName;
    }

    public static Action getActionByValue(String value) {
        for (Action action : Action.values()) {
            if (action.getActionName().equals(value)) {
                return action;
            }
        }
        return null;
    }

}
