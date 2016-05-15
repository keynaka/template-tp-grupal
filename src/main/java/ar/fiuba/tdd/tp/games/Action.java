package ar.fiuba.tdd.tp.games;

import java.io.Serializable;

/**
 * Created by swandelow on 4/22/16.
 */
public class Action implements Serializable {

    private String actionName;

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
