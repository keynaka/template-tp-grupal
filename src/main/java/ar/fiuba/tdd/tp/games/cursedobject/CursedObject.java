package ar.fiuba.tdd.tp.games.cursedobject;

import ar.fiuba.tdd.tp.games.Character;
import ar.fiuba.tdd.tp.games.CharacterState;
import ar.fiuba.tdd.tp.games.Collectible;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by swandelow on 4/27/16.
 */
public class CursedObject extends Item implements Collectible {

    public CursedObject() {
        super("CursedObject", "it's a cursed object.");
    }

    @Override
    public String pick(Character character) {
        character.modifyState(CharacterState.CURSED);
        return "Now you are cursed!";
    }
}
