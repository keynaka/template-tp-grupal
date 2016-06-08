package ar.fiuba.tdd.tp.red.server;

import ar.fiuba.tdd.tp.games.CommandInterpreter;
import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.games.GameBuilder;
import ar.fiuba.tdd.tp.games.Player;
import ar.fiuba.tdd.tp.games.exceptions.AddingPlayerException;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ServerPortListenerThread extends Thread {

    private int portNumber;
    private GameBuilder gameBuilder;
    private Game game;
    ServerSocket serverSocket;
    private List<ServerClientThread> clientThreads = new ArrayList<>();
    private CommandInterpreter interpreter = new CommandInterpreter();

    public ServerPortListenerThread(int portNumber, GameBuilder gameBuilder) {
        super("ServerPortListenerThread" + portNumber);
        this.portNumber = portNumber;
        this.gameBuilder = gameBuilder;
    }

    public void run() {
        try {
            game = gameBuilder.build();
            serverSocket = new ServerSocket(portNumber);

            while (!this.isInterrupted()) {
                // Starts new client thread
                int playerNumber = clientThreads.size() + 1;

                ServerClientThread clientThread = new ServerClientThread(serverSocket.accept(), this, playerNumber);
                clientThread.start();
                clientThreads.add(clientThread);

                System.out.println("New connection in port " + portNumber + "! Client amount: " + clientThreads.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void interrupt() {
        for (Thread thread : clientThreads) {
            thread.interrupt();
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newClientEvent(ServerClientThread newClientThread) {
        // Welcomes the client
        String welcomeMessage = "Welcome to port " + portNumber + "! ";
        welcomeMessage += "\nAre you ready to play " + game.getName() + "?";
        welcomeMessage += "\nYou are player number " + newClientThread.getPlayerNumber();

        try {
            // agrego player al juego
            this.game.getPlayerManager().addNewPlayer(newClientThread.getPlayerNumber());

            newClientThread.sendMessage(welcomeMessage);
            // Notifies remaining clients
            notify(newClientThread, "Player number " + newClientThread.getPlayerNumber() + " has logged in.");
        } catch (AddingPlayerException e) {
            // TODO cerrar conexion fallida con el client
            welcomeMessage = e.getMessage();
            newClientThread.sendMessage("More players are not allowed. Goodbye.");
        }

    }

    public void processMessageByClientEvent(ServerClientThread clientThread, String command) {
        int playerNumber = clientThread.getPlayerNumber();

        Player player = this.game.getPlayerManager().getPlayer(playerNumber);
        this.game.setPlayer(player);

        // Notifies remaining clients
        notify(clientThread, "Player " + playerNumber + " executed: " + command);

        String response = game.play(interpreter.getCommandForDriver(command));
        clientThread.sendMessage(response);
    }

    public void notify(ServerClientThread notifier, String msg) {
        for (ServerClientThread client : clientThreads) {
            if (client != notifier) {
                client.sendMessage(msg);
            }
        }
    }
}
