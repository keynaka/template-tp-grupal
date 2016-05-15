package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/22/16.
 */
public class Action {

    private String actionName;

    public static Action unknow() {
        return new Action("Unknow action");
    }

    public static Action help() {
        return new Action("Help");
    }

    public static Action exit() {
        return new Action("Exit");
    }

    public Action(String action) {
        this.actionName = action;
    }

    public String getActionName() {
        return this.actionName;
    }

    public Boolean equals(Action otherAction) {
        if (this.getActionName() == null || otherAction.getActionName() == null) {
            return Boolean.FALSE;
        }

        return this.getActionName().equalsIgnoreCase(otherAction.getActionName());
    }

    /*
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
    UNKNOWN_ACTION("");
*/
}
