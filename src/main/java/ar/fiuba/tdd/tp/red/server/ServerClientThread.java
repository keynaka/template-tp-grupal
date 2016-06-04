package ar.fiuba.tdd.tp.red.server;

import ar.fiuba.tdd.tp.games.AbstractGame;
import ar.fiuba.tdd.tp.games.ActionOld;
import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.games.creation.GamesCreator;
import ar.fiuba.tdd.tp.games.exceptions.GameException;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ServerClientThread extends Thread {

    private Socket clientSocket;
    private ServerPortListenerThread portThread;

    private DataInputStream in;
    private DataOutputStream out;
    private Command command;
    private Response response;
    private Game game;
    private boolean forceFinish = false;

    public ServerClientThread(Socket clientSocket, ServerPortListenerThread portThread) {
        try {
            this.clientSocket = clientSocket;
            this.portThread = portThread;

            this.in = new DataInputStream(clientSocket.getInputStream());
            this.out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void interrupt() {
        disconnectClient();
    }

    public void run() {
        try {
            portThread.newClientEvent(this);

            readMessage();

            while (!this.isInterrupted()) {
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

    public void sendMessage(String msg) {
        try {
            out.writeBytes(msg);
            out.flush(); // Force send
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void welcomeToClient() throws Exception {
        game.start(); // Starts the game

        StringBuilder welcomeMessage = new StringBuilder();
        welcomeMessage.append("Welcome to port " + clientSocket.getLocalPort() + "! ");
        welcomeMessage.append("\nYou are going to play " + game.getName());
        this.response = new Response(welcomeMessage.toString());

        this.sendMessage();
    }

    private void readMessage() throws Exception {
        if (!game.isFinished()) {
            //command = (Command) in.readObject();
            String rawCommand = (String) in.readObject();
            this.command = this.interpreter.getCommand(rawCommand);
        }
    }

    private void processGame() throws Exception {
        if (this.command.isSystemAction()) {
            this.processSystemAction();
        } else {
            String responseStr = null;
            try {
                responseStr = game.play(command);
            } catch (GameException e) {
                responseStr = e.getMessage();
            }
            response = new Response(responseStr);
            response.setGameFinalized(game.isFinished());
        }
    }

    private void processSystemAction() {
        if (this.command.getAction() == ActionOld._HELP) {
            this.setHelpResponse();
        } else if (this.command.getAction() == ActionOld._EXIT) {
            this.forceFinish = true;
            this.response = new Response("Thank you for playing with us! Good bye!");
            response.setGameFinalized(true);
        }
    }

    private void setHelpResponse() {
        StringBuilder helpMessage = new StringBuilder();
        helpMessage.append("Available commands:\n");

        for (ActionOld action : ((AbstractGame) this.game).getKnownActions().keySet()) {
            helpMessage.append(action.getActionName() + "\n");
        }
        helpMessage.append(ActionOld._HELP.getActionName() + "\n");
        helpMessage.append(ActionOld._EXIT.getActionName() + "\n");

        this.response = new Response(helpMessage.toString());
    }

    private void disconnectClient() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean gameEnded() {
        return game.isFinished();
    }
}
