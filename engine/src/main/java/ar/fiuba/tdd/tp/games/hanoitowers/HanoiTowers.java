package ar.fiuba.tdd.tp.games.hanoitowers;

import ar.fiuba.tdd.tp.games.AbstractGame;
import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.exceptions.InvalidMoveException;
import ar.fiuba.tdd.tp.games.items.Disk;
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
        this.knownActions.put(Action.SET_DISKS, (itemName, args) -> this.setNumberDisks(itemName));
        this.registerMoveTop();
        this.registerExamine();
        registerMoreActions();
    }

    private void registerExamine() {
        this.knownActions.put(Action.EXAMINE, (itemName, arg) -> this.examine(itemName));
    }

    private void registerMoveTop() {
        this.knownActions.put(Action.MOVE_TOP, (itemName, args) -> this.moveTop(itemName, args));
    }

    private void registerMoreActions() {
        this.knownActions.put(Action.TOP_SIZE, (itemName, args) -> this.getTopSize(itemName));
    }

    private String topSize(String input) {

        String errorMsg = "Invalid Command: Error getting top's size.";
        int towerPosition = getTowerPosition(input);

        if (towerPosition < 0) {

            return errorMsg;

        } else {

            Disk topDisk = towers.get(towerPosition).checkTop();

            if (topDisk == null) {

                return errorMsg;

            } else {

                String topSize = Integer.toString(topDisk.getSize());
                String message = "Size of top from tower %s is %s.";
                return String.format(message, input, topSize);

            }
        }
    }

    private String getTopSize(String itemName) {

        if (disksNumber == 0) {
            return "You have to tell me how many disks you want to use first!";
        }

        if (towerExists(itemName)) {
            return topSize(itemName);
        }

        return "Invalid Command: Error getting top's size.";

    }

    private boolean towerExists(String towerId) {

        if (getTowerPosition(towerId) >= 0) {
            return true;
        }

        return false;
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

    private String moveTop(String itemName, String[] args) {
        if (disksNumber == 0) {
            return "You have to tell me how many disks you want to use first!";
        }

        if (!towerExists(itemName) || !towerExists(args[0])) {
            return "Invalid Command: you must specify origin tower and destiny tower (between numbers 1 and 3).";
        }

        return switchDisk(itemName, args);
    }

    private String switchDisk(String itemName, String[] args) {

        int originPosition = getTowerPosition(itemName);
        int destinyPosition = getTowerPosition(args[0]);

        if (originPosition < 0) {
            return "Invalid Command: you must specify origin tower and destiny tower (between numbers 1 and 3).";
        } else if (destinyPosition < 0) {
            return "Invalid Command: you must specify origin tower and destiny tower (between numbers 1 and 3).";
        }

        Disk originTop = towers.get(originPosition).getTop();

        if (originTop == null) {
            return "Origin tower is empty!";
        }

        return move(destinyPosition, originTop, itemName, args[0]);
    }


    private String move(int destinyPosition, Disk originTop, String originTower, String destinyTower) {
        try {
            towers.get(destinyPosition).addDisk(originTop);
        } catch (InvalidMoveException e) {
            String message = "Invalid Move: tower %s top is smaller than tower %s top!";
            return String.format(message, originTower, destinyTower);
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

        if (towerExists(itemName)) {
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
