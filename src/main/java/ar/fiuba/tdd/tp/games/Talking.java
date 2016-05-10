package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/28/16.
 */
public interface Talking {

    /**
     * For game objects that can talk and modify the player.
     * @param speaker A game player.
     * @param message Message.
     * @return A result message for Talk operation.
     */
    String talk(Player speaker, String message);
}
