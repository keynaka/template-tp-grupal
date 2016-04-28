package ar.fiuba.tdd.tp.games.cursedobject;

import ar.fiuba.tdd.tp.games.Character;
import ar.fiuba.tdd.tp.games.Openable;
import ar.fiuba.tdd.tp.games.State;
import ar.fiuba.tdd.tp.games.items.Door;

import java.util.function.Predicate;

/**
 * Created by swandelow on 4/27/16.
 */
public class CursedDoor extends Door implements Openable {

    private static final String ENTERED_MSG = "You have entered the %s.";

    private Predicate<Character> openCondition;

    public CursedDoor(String name) {
        super(name, State.CLOSED);
    }

    @Override
    public String open(Character character) {
        if (openCondition.test(character)) {
            character.setCurrentStage(this.getNextStageName());
            String result = this.open();
            String nextStageMsg = String.format(ENTERED_MSG, this.getNextStageName());
            return this.isClosed() ? result : result.concat(" ").concat(nextStageMsg);
        }
        return "You can't open this door. You need the CursedObject.";
    }

    public void setOpenCondition(Predicate<Character> openCondition) {
        this.openCondition = openCondition;
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
