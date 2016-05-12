package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/26/16.
 */
public interface Collectible {

    /**
     * Method to be implemented for items that support action Pick and modify the player's state.
     * @param player A game player.
     * @return A message resulted of pick the object.
     */
    String pick(Player player);
}
