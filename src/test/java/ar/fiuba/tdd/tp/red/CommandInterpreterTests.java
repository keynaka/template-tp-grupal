package ar.fiuba.tdd.tp.red;

import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.red.server.Command;
import ar.fiuba.tdd.tp.red.server.CommandInterpreter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Patri on 26/04/2016.
 */
public class CommandInterpreterTests {

    private CommandInterpreter interpreter = new CommandInterpreter();
/*
    @Test
    public void pickStickActionIsPick() {
        String gameCommand = "pick stick";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getAction(), new Action("Pick"));
    }

    @Test
    public void pickStickItemIsStick() {
        String gameCommand = "pick stick";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getItemName(), "stick");
        assertEquals(command.getArgument(), "");
    }

    @Test
    public void lookAroundActionIsLookAround() {
        String gameCommand = "look around";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getAction(), new Action("Look Around"));
    }

    @Test
    public void lookAroundHasNotItem() {
        String gameCommand = "look around";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getItemName(), "");
        assertEquals(command.getArgument(), "");
    }

    @Test
    public void openActionIsOpen() {
        String gameCommand = "open door";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getAction(), new Action("Open"));
        assertEquals(command.getArgument(), "");
    }

    @Test
    public void openDoorItemIsDoor() {
        String gameCommand = "open door";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getItemName(), "door");
        assertEquals(command.getArgument(), "");
    }

    @Test
    public void examineActionIsExamine() {
        String gameCommand = "examine box";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getAction(),new Action("Examine"));
    }

    @Test
    public void examineBoxItemIsBox() {
        String gameCommand = "examine box";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getItemName(), "box");
        assertEquals(command.getArgument(), "");
    }

    @Test
    public void diskNumberActionIsSetDisks() {
        String gameCommand = "play with 2";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getAction(), new Action("Set disks"));
    }

    @Test
    public void diskNumber2ItemIs2() {
        String gameCommand = "play with 2";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getItemName(), "2");
        assertEquals(command.getArgument(), "");
    }

    @Test
    public void moveTopActionIsMove() {
        String gameCommand = "move top";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getAction(), new Action("Move Top"));
        assertEquals(command.getArgument(), "");
    }

    @Test
    public void moveTopItemIsTop() {
        String gameCommand = "move top 1 2";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getItemName(), "1");
        assertEquals(command.getArgument(), "2");
    }

    @Test
    public void unknownAction() {
        String gameCommand = "anything else";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getAction(), new Action("Unknow Action"));
    }

    @Test
    public void unknownActionItemIsEmpty() {
        String gameCommand = "anything else";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getItemName(), "");
        assertEquals(command.getArgument(), "");
    }

    @Test
    public void takeActionIsTake() {
        String gameCommand = "take wolf";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getAction(), new Action("Take"));
    }

    @Test
    public void takeWolfItemIsWolf() {
        String gameCommand = "take wolf";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getItemName(), "wolf");
        assertEquals(command.getArgument(), "");
    }

    @Test
    public void leaveActionIsLeave() {
        String gameCommand = "leave sheep";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getAction(),new Action("Leave"));
    }

    @Test
    public void leaveSheepItemIsSheep() {
        String gameCommand = "leave sheep";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getItemName(), "sheep");
        assertEquals(command.getArgument(), "");
    }

    @Test
    public void crossActionIsCross() {
        String gameCommand = "cross north-shore";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getAction(), new Action("Cross"));
    }

    @Test
    public void crossNorthShoreItemIsNorthShore() {
        String gameCommand = "cross north-shore";
        Command command = interpreter.getCommand(gameCommand);
        assertEquals(command.getItemName(), "north-shore");
        assertEquals(command.getArgument(), "");
    }
*/
}