package ar.fiuba.tdd.tp.games.hanoitowers;

import ar.fiuba.tdd.tp.games.AbstractGame;
import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.exceptions.InvalidMoveException;
import ar.fiuba.tdd.tp.games.items.DiskAdapter;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.Tower;

import java.util.ArrayList;
import java.util.regex.Pattern;

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
        this.knownActions.put(Action.EXAMINE, (itemName) -> this.examine(itemName));
        this.knownActions.put(Action.SET_DISKS, (itemName) -> this.setNumberDisks(itemName));
        this.knownActions.put(Action.MOVE_TOP, (itemName) -> this.moveTop(itemName));
        /* To avoid code duplication warning */
        registerMoreActions();
    }

    private void registerMoreActions() {
        this.knownActions.put(Action.TOP_SIZE, (itemName) -> this.getTopSize(itemName));
    }

    /*
    * Checks if input matches pattern, if it does splits input into String[].
    * If it doesn't, returns null.
    */
    private String[] getInput(String pattern, String input) {

        Pattern target = Pattern.compile(pattern);

        if (target.matcher(input).matches()) {
            return input.split(" ");
        }

        return null;
    }


    private String topSize(String[] input) {

        String errorMsg = "Invalid Command: Error getting top's size.";
        int towerPosition = getTowerPosition(input[1]);

        if (towerPosition < 0) {

            return errorMsg;

        } else {

            Item top = towers.get(towerPosition).checkTop();

            if (top == null) {

                return errorMsg;

            } else if (top instanceof DiskAdapter) {

                DiskAdapter topDisk = DiskAdapter.class.cast(top);
                String topSize = Integer.toString(topDisk.getSize());
                String message = "Size of top from tower %s is %s.";
                return String.format(message, input[1], topSize);

            }
        }

        return errorMsg;
    }

    private String getTopSize(String itemName) {

        if (disksNumber == 0) {
            return "You have to tell me how many disks you want to use first!";
        }

        String errorMsg = "Invalid Command: Error getting top's size.";

        String pattern = "tower [1-3]";
        String[] input = getInput(pattern, itemName);

        if (input == null) {
            return errorMsg;
        }

        return topSize(input);

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

        String pattern = "tower [1-3] tower [1-3]";

        String[] input = getInput(pattern, itemName);

        if (input == null) {
            return "Invalid Command: you must specify origin tower and destiny tower (between numbers 1 and 3).";
        }

        return switchDisk(input);
    }

    private String switchDisk(String[] input) {

        int originPosition = getTowerPosition(input[1]);
        int destinyPosition = getTowerPosition(input[3]);

        if (originPosition < 0) {
            return "Invalid Command: you must specify origin tower and destiny tower (between numbers 1 and 3).";
        } else if (destinyPosition < 0) {
            return "Invalid Command: you must specify origin tower and destiny tower (between numbers 1 and 3).";
        }

        Item originTop = towers.get(originPosition).getTop();

        if (originTop == null) {
            return "Origin tower is empty!";
        }

        return move(destinyPosition, originTop, input);
    }


    private String move(int destinyPosition, Item originTop, String[] input) {
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

    private String examine(String itemName) {
        if (disksNumber == 0) {
            return "You have to tell me how many disks you want to use first!";
        }

        String pattern = "tower [1-3]";

        if (getInput(pattern, itemName) != null) {
            return "You can check top/move top.";
        }

        return "Invalid command!";
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
