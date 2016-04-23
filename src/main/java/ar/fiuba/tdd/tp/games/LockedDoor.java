package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/23/16.
 */
public class LockedDoor extends Door {
    private String keyName;
    private State lockState;

    public LockedDoor(String keyName) {
        super(State.CLOSED);
        this.lockState = State.LOCKED;
        this.keyName = keyName;
    }

    @Override
    public String open() {
        if (this.isLocked()) {
            return "Ey! Where do you go?! Room 2 is locked.";
        } else {
            return super.open();
        }
    }

    public String openWithKey(String keyName) {
        if (this.isValidKey(keyName)) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.unlock(keyName));
            sb.append(this.open());
            return sb.toString();
        } else {
            return "Invalid key.";
        }
    }

    public String unlock(String keyName) {
        String result = null;
        boolean isUnlocked = !this.isLocked();
        if (isUnlocked) {
            result = "The door is already unlocked.";
        } else {
            if (this.isValidKey(keyName)) {
                this.lockState = State.UNLOCKED;
                result = "Door unlocked.";
            } else {
                result = "Invalid key.";
            }
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
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
