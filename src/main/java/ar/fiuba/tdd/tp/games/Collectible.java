package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/26/16.
 */
public interface Collectible {

    /**
     * Method to be implemented for items that support action Pick and modify the character's state.
     * @param character A game character.
     * @return A message resulted of pick the object.
     */
    String pick(Character character);
}
