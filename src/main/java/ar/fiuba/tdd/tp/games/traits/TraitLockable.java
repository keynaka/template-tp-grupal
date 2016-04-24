package ar.fiuba.tdd.tp.games.traits;

import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.ActionFunction;
import ar.fiuba.tdd.tp.games.State;

/**
 * Created by swandelow on 4/24/16.
 */
public class TraitLockable extends TraitOpenable {
    private String keyName;
    private State lockState;

    public TraitLockable(State lockState, String keyName) {
        super(State.CLOSED);
        this.lockState = lockState;
        this.keyName = keyName;
    }

    @Override
    public String open(String itemName) {
        if (this.isLocked()) {
            return String.format("Ey! Where do you go?! %s is locked.", itemName);
        } else {
            return super.open(itemName);
        }
    }

    public String openWithKey(String itemName, String keyName) {
        if (this.isValidKey(keyName)) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.unlock(itemName, keyName));
            sb.append(this.open(itemName));
            return sb.toString();
        } else {
            return "Invalid key.";
        }
    }

    public String unlock(String itemName, String keyName) {
        String result = null;
        boolean isUnlocked = !this.isLocked();
        if (isUnlocked) {
            result = String.format("The %s is already unlocked.", itemName);
        } else {
            if (this.isValidKey(keyName)) {
                this.lockState = State.UNLOCKED;
                result = String.format("%s unlocked.", itemName);
            } else {
                result = "Invalid key.";
            }
        }
        return result;
    }

    public String lock(String itemName) {
        String result = null;
        if (this.isLocked()) {
            result = String.format("The %s is already locked.", itemName);
        } else {
            this.lockState = State.UNLOCKED;
            result = String.format("%s unlocked.", itemName);
        }
        return result;
    }

    private boolean isValidKey(String keyName) {
        return this.keyName.equalsIgnoreCase(keyName);
    }

    public boolean isLocked() {
        return State.LOCKED.equals(this.lockState);
    }

    @Override
    protected void registerActions() {
        this.knownActions.put(Action.OPEN, (itemName, args) -> this.open(itemName));
        this.knownActions.put(Action.CLOSE, (itemName, args) -> this.close(itemName));
        this.knownActions.put(Action.LOCK, (itemName, args) -> this.lock(itemName));
        this.knownActions.put(Action.UNLOCK, (itemName, args) -> {
            String keyName = args[0];
            return this.unlock(itemName, keyName);
        });
    }

}
