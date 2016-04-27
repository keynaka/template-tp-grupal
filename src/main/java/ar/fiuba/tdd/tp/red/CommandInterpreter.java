package ar.fiuba.tdd.tp.red;

import ar.fiuba.tdd.tp.games.AbstractGame;
import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.ActionFunction;
import ar.fiuba.tdd.tp.games.fetchquest.FetchQuest2;
import ar.fiuba.tdd.tp.games.hanoitowers.HanoiTowers;
import ar.fiuba.tdd.tp.games.opendoor.OpenDoor;
import ar.fiuba.tdd.tp.games.opendoor.OpenDoor2;
import ar.fiuba.tdd.tp.games.treasurehunt.TreasureHunt;
import ar.fiuba.tdd.tp.games.woolfsheepcabbage.WolfSheepCabbage;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Patri on 26/04/2016.
 */
public class CommandInterpreter {

    private ArrayList<AbstractGame> games;

    public CommandInterpreter() {
        this.games = new ArrayList<AbstractGame>();

        this.addGame(new FetchQuest2());
        this.addGame(new OpenDoor());
        this.addGame(new OpenDoor2());
        this.addGame(new HanoiTowers());
        this.addGame(new WolfSheepCabbage());
        this.addGame(new TreasureHunt());
    }

    public void addGame(AbstractGame game) {
        game.start();
        this.games.add(game);
    }

    public Command getCommand(String gameCommand) {

        Command command = this.checkSystemAction(gameCommand);

        if (command.isSystemAction()) {
            return command;
        }

        for (AbstractGame game : this.games) {
            command = this.bindActions(gameCommand, game);
            if (command.getAction() != Action.UNKNOWN_ACTION) {
                break;
            }
        }
        return command;
    }

    private Command bindActions(String gameCommand, AbstractGame game) {
        Command command = new Command();
        for (Action action : game.getKnownActions().keySet()) {

            String actionName = action.getActionName();

            if (gameCommand.contains(actionName)) {
                String itemName = this.getItemName(action.getActionName(), gameCommand);
                String argument = this.getArgument(action.getActionName(), gameCommand, itemName);
                command = new Command(action, itemName, argument);
                break;
            }
        }
        return command;
    }

    private Command checkSystemAction(String gameCommand) {
        Action systemAction = Command.checkSystemAction(gameCommand);
        return new Command(systemAction);
    }

    private String getItemName(String actionName, String gameCommand) {
        String itemName = "";
        if (actionName.length() + 1 < gameCommand.length()) {
            String[] parameters = getParameters(actionName, gameCommand);
            itemName = parameters[0];
        }
        return itemName;
    }

    private String getArgument(String actionName, String gameCommand, String itemName) {
        String argument = "";
        if (actionName.length() + 1 + itemName.length() < gameCommand.length()) {
            String[] parameters = getParameters(actionName, gameCommand);
            argument = parameters[1];
        }
        return argument;
    }

    private String[] getParameters(String actionName, String gameCommand) {
        String commandWithoutAction = gameCommand.substring(actionName.length() + 1);
        String[] parameters = commandWithoutAction.split("\\s");
        return parameters;
    }
}



