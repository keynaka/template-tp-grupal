package ar.fiuba.tdd.tp.red.server;

import ar.fiuba.tdd.tp.games.AbstractGame;
import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.games.fetchquest.FetchQuest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ServerClientThread extends Thread {

    private Socket clientSocket;
    private Server server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Command command;
    private Response response;
    private Game game;
    private CommandInterpreter interpreter;
    private boolean forceFinish = false;

    public ServerClientThread(Socket clientSocket, Server server, String nameGame) {
        super("ServerThread" + server.getClientAmount());
        try {
            this.clientSocket = clientSocket;
            this.server = server;
            this.in = new ObjectInputStream(clientSocket.getInputStream());
            this.out = new ObjectOutputStream(clientSocket.getOutputStream());
            this.game = new FetchQuest(); //TODO dejo harcodeado el juego
            this.interpreter = new CommandInterpreter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            welcomeToClient();
            readMessage();

            while (!gameEnded() && !this.forceFinish) {
                processGame();
                sendMessage();

                if (!this.forceFinish) {
                    readMessage();
                }
            }
            disconnectClient();
            clientSocket.close();

        } catch (ClassNotFoundException e) {
            disconnectClient();
            System.err.println("Unkown object recived from socket.");
        } catch (SocketException e) {
            System.out.println("Client has gone away.");
            disconnectClient();
        } catch (Exception e) {
            disconnectClient();
            e.printStackTrace();
        }
    }

    private void welcomeToClient() throws Exception {
        this.game.start(); // Starts the game

        StringBuilder welcomeMessage = new StringBuilder();
        welcomeMessage.append("Welcome to port " + clientSocket.getLocalPort() + "! ");
        welcomeMessage.append("\nYou are the client number " + server.getClientAmount() + '.');
        welcomeMessage.append("\nYou are going to play " + this.game.getName());
        this.response = new Response(welcomeMessage.toString());

        this.sendMessage();
    }

    private void readMessage() throws Exception {
        if (!game.isFinished()) {
            //command = (Command) in.readObject();
            String rawCommand = (String)in.readObject();
            this.command = this.interpreter.getCommand(rawCommand);
        }
    }

    private void processGame() throws Exception {
        if (this.command.isSystemAction()) {
            this.processSystemAction();
        } else {
            String responseStr = game.play(command);
            response = new Response(responseStr);
            response.setGameFinalized(game.isFinished());
        }
    }

    private void processSystemAction() {
        if (this.command.getAction() == new Action("Help")) {
            this.setHelpResponse();
        } else if (this.command.getAction() == new Action("Exit")) {
            this.forceFinish = true;
            this.response = new Response("Thank you for playing with us! Good bye!");
            response.setGameFinalized(true);
        }
    }

    private void setHelpResponse() {
        StringBuilder helpMessage = new StringBuilder();
        helpMessage.append("Available commands:\n");

        for (Action action : ((AbstractGame)this.game).getKnownActions().keySet()) {
            helpMessage.append(action.getActionName() + "\n");
        }
        helpMessage.append(new Action("Help").getActionName() + "\n");
        helpMessage.append(new Action("Exit").getActionName() + "\n");

        this.response = new Response(helpMessage.toString());
    }

    private void sendMessage() throws Exception {
        out.writeObject(response);
    }

    private void disconnectClient() {
        server.decrementedClientAmount();
        System.out.println("Client amount: " + server.getClientAmount());
    }

    private boolean gameEnded() {
        return game.isFinished();
    }
}
