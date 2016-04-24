package ar.fiuba.tdd.tp.games.hanoitowers;

import ar.fiuba.tdd.tp.games.AbstractGame;
import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.exceptions.InvalidMoveException;
import ar.fiuba.tdd.tp.games.items.DiskAdapter;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.Tower;

import java.util.ArrayList;

/**
 * Created by Patri on 23/04/2016.
 */
public class HanoiTowers extends AbstractGame {

    private ArrayList<Tower> towers;
    private int disksNumber;
    Tower tower1;
    Tower tower2;
    Tower tower3;

    public HanoiTowers() {
        super("Hanoi Towers", "You won the game!");
    }

    @Override
    protected void doStart() {
        this.disksNumber = 0;

        tower2 = new Tower(0, "2");
        tower3 = new Tower(0, "3");

        towers = new ArrayList<Tower>();
        towers.add(tower2);
        towers.add(tower3);
    }

    @Override
    protected void registerKnownActions() {
        this.knownActions.put(Action.ASK_POSSIBILITY, (itemName) -> this.askPossibility(itemName));
        this.knownActions.put(Action.SET_DISKS, (itemName) -> this.setNumberDisks(itemName));
        this.knownActions.put(Action.MOVE_TOP, (itemName) -> this.moveTop(itemName));
    }

    /*
    * Input structure must be: tower i tower j
    * If input does not respect this structure, it returns empty ArrayList
    */
    private String[] parseMoveTopInput(String input) {

        String[] output = input.split(" ");
        if (output[0].equals("tower") && output[2].equals("tower")) {
            try {
                Integer.parseInt(output[1]);
                Integer.parseInt(output[3]);
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }

        return output;
    }


    private int getTowerPosition(String towerId) {
        for (int i = 0; i < towers.size(); i++) {
            Tower tower = towers.get(i);
            if (tower.getTowerId().equals(towerId)) {
                return i;
            }
        }
        return -1;
    }

    private String moveTop(String itemName) {

        if (disksNumber == 0) {
            return "You have to tell me how many disks you want to use first!";
        }

        String[] input = this.parseMoveTopInput(itemName);
        if (input == null) {
            return "Invalid Command: you must specify origin tower and destiny tower.";
        }

        return switchDisk(input);
    }

    private String switchDisk(String[] input) {

        int originPosition = getTowerPosition(input[1]);
        int destinyPosition = getTowerPosition(input[3]);

        if (originPosition < 0) {
            return "Invalid Command: origin tower's number is invalid.";
        } else if (destinyPosition < 0) {
            return "Invalid Command: destiny tower's number is invalid.";
        }

        Item originTop = towers.get(originPosition).getTop();

        if (originTop == null) {
            return "Origin tower is empty!";
        }

        try {
            towers.get(destinyPosition).addDisk(originTop);
        } catch (InvalidMoveException e) {
            String message = "Invalid Move: tower %s top is smaller than tower %s top!";
            return String.format(message, input[1], input[3]);
        }

        return "moved!";

    }

    private String setNumberDisks(String itemName) {

        try {
            this.disksNumber = Integer.parseInt(itemName);
            tower1 = new Tower(disksNumber, "1");
            towers.add(tower1);
            return String.format("You are now playing with %s disks !", itemName);
        } catch (NumberFormatException e) {
            return "Invalid command: you must specify a number of disks to play with.";
        }

    }

    private String askPossibility(String itemName) {
        if (disksNumber == 0) {
            return "You have to tell me how many disks you want to use first!";
        }
        if (itemName.equals("tower 1") || itemName.equals("tower 2") || itemName.equals("tower 3")) {
            return "You can check top/move top.";
        }
        return "Invalid command: try asking about one of the towers (you have three).";
    }

    @Override
    public boolean isFinished() {
        if (disksNumber != 0) {
            if (tower3.getSize() == this.disksNumber) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
