package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.items.Item;

import java.util.Arrays;
import java.util.List;

/**
 * Created by swandelow on 4/26/16.
 */
public class Thief extends Item {

    public Thief() {
        super("thief", "A horrible thief.");
    }

    public String talk(Character speaker, String message) {
        String response = "No answer.";
        if ("Hello".equalsIgnoreCase(message)) {
            response = "Hi!.";
            response = response.concat(" ").concat(this.steal(speaker));
        }
        if ("Bye".equalsIgnoreCase(message)) {
            response = "Bye.";
        }
        return response;
    }

    public String steal(Character character) {
        character.getInventory().dropAllItems();
        return "The thief has just stolen your object!";
    }
}
