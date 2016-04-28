package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/28/16.
 */
public interface Talking {

    /**
     * For game objects that can talk and modify the character.
     * @param speaker A game character.
     * @param message Message.
     * @return A result message for Talk operation.
     */
    String talk(Character speaker, String message);
}
