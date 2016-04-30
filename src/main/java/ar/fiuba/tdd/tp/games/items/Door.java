package ar.fiuba.tdd.tp.games.items;

import ar.fiuba.tdd.tp.games.Character;
import ar.fiuba.tdd.tp.games.Openable;
import ar.fiuba.tdd.tp.games.State;

import java.util.function.Predicate;

/**
 * Created by swandelow on 4/22/16.
 */
public class Door extends Item implements Openable {

    private static final String DEFAULT_OPENING_ERROR_MSG = "You can't open this door.";
    private static final Predicate<Character> DEFAULT_OPENING_CONDITION = (character) -> true;

    private State state;
    private String nextStageName;
    private Predicate<Character> openingCondition;
    private String openingErrorMessage;

    public Door(State state) {
        super("door", "it's a door.");
        this.state = state;
    }

    public Door(String doorName, State state) {
        super(doorName, "it's a door.");
        this.state = state;
    }

    public boolean isClosed() {
        return State.CLOSED.equals(this.state);
    }

    public String open() {
        if (this.isClosed()) {
            this.state = State.OPEN;
            return "Open door.";
        } else {
            return "Door it's already open.";
        }
    }

    @Override
    public String open(Character character) {
        if (this.getOpeningCondition().test(character)) {
            return this.open();
        }
        return this.getOpeningErrorMessage();
    }

    public String close() {
        if (this.isClosed()) {
            return "Door it's already closed.";
        } else {
            this.state = State.CLOSED;
            return "Closed door.";
        }
    }

    public String getNextStageName() {
        return this.nextStageName;
    }

    public void setNextStageName(String nextStageName) {
        this.nextStageName = nextStageName;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public Predicate<Character> getOpeningCondition() {
        return this.openingCondition != null ? this.openingCondition : DEFAULT_OPENING_CONDITION;
    }

    public void setOpeningCondition(Predicate<Character> condition) {
        this.openingCondition = condition;
    }

    private String getOpeningErrorMessage() {
        return this.openingErrorMessage != null ? this.openingErrorMessage : DEFAULT_OPENING_ERROR_MSG;
    }

    public void setOpeningErrorMessage(String message) {
        this.openingErrorMessage = message;
    }

    static class DoorBuilder {
        private static final String DEFAULT_NAME = "door";

        private String name;
        private State state;
        private String nextStageName;
        private Predicate<Character> openingCondition;
        private String openingErrorMessage;

        public DoorBuilder(State state) {
            this.state = state;
        }

        public DoorBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DoorBuilder nextStageName(String nextStageName) {
            this.nextStageName = nextStageName;
            return this;
        }

        public DoorBuilder openingCondition(Predicate<Character> openingCondition) {
            this.openingCondition = openingCondition;
            return this;
        }

        public DoorBuilder openingErrorMessage(String openingErrorMessage) {
            this.openingErrorMessage = openingErrorMessage;
            return this;
        }

        public Door build() {
            String doorName = this.name != null ? this.name : DEFAULT_NAME;
            Door door = new Door(doorName, this.state);
            door.setNextStageName(this.nextStageName);
            door.setOpeningCondition(this.openingCondition);
            door.setOpeningErrorMessage(this.openingErrorMessage);
            return door;
        }
    }
}
