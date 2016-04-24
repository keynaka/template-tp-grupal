package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/22/16.
 */
public enum Action {

    LOOK_AROUND("look around"),
    PICK("pick"),
    OPEN("open"),
    CLOSE("close"),
    LOCK("lock"),
    UNLOCK("unlock"),
    TAKE("take"),
    LEAVE("leave"),
    CROSS("cross"),
    EXAMINE("examine"),
    SET_DISKS("play with"),
    MOVE_TOP("move top"),
    ASK_POSSIBILITY("what can i do with"),
    UNKNOWN_ACTION("");



    private String actionName;

    Action(String actionName) {
        this.actionName = actionName;
    }


}
