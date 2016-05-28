package ar.fiuba.tdd.tp.red.server;

import ar.fiuba.tdd.tp.games.AbstractGame;
import ar.fiuba.tdd.tp.games.ActionOld;
import ar.fiuba.tdd.tp.games.creation.GamesCreator;

import java.util.ArrayList;

/**
 * Created by Patri on 26/04/2016.
 */
public class CommandInterpreter {

    private ArrayList<AbstractGame> games;

    public CommandInterpreter() {

        this.games = GamesCreator.getAllGames();
        startGames();
    }

    /*
    this.games = new ArrayList<AbstractGame>();

    this.addGame(new FetchQuest());
    this.addGame(new OpenDoor());
    this.addGame(new OpenDoor2());
    this.addGame(new HanoiTowers());
    this.addGame(new WolfSheepCabbage());
    this.addGame(new TreasureHunt());
    this.addGame(new CursedObjectGame());


public void addGame(AbstractGame game) {
    game.start();
    this.games.add(game);
}
*/
    private void startGames() {
        for (AbstractGame game : this.games) {
            game.start();
        }
    }

    public Command getCommand(String gameCommand) {

        Command command = this.checkSystemAction(gameCommand);

        if (command.isSystemAction()) {
            return command;
        }

        for (AbstractGame game : this.games) {
            command = this.bindActions(gameCommand, game);
            if (command.getAction() != ActionOld.UNKNOWN_ACTION) {
                break;
            }
        }
        return command;
    }

    public Command getCommandForDriver(String gameCommand) {

        if ((ActionOld.getActionByValue(gameCommand) != null)) {
            return new Command(ActionOld.getActionByValue(gameCommand));
        }

        String[] commandWords = gameCommand.split(" ");
        if (commandWords.length == 1) {
            return new Command(ActionOld.getActionByValue(commandWords[0]));
        } else if (commandWords.length == 2) {
            return new Command(ActionOld.getActionByValue(commandWords[0]), commandWords[1]);
        } else {
            return new Command(ActionOld.getActionByValue(commandWords[0]), commandWords[1], commandWords[2]);
        }
    }

    private Command bindActions(String gameCommand, AbstractGame game) {
        Command command = new Command();
        for (ActionOld action : game.getKnownActions().keySet()) {

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
        ActionOld systemAction = Command.checkSystemAction(gameCommand);
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



