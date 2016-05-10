package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by swandelow on 4/26/16.
 */
public class Thief extends Item implements Talking {

    public Thief() {
        super("thief", "A horrible thief.");
    }

    @Override
    public String talk(Player speaker, String message) {
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

    public String steal(Player player) {
        player.getInventory().dropAllItems();
        return "The thief has just stolen your object!";
    }
}
