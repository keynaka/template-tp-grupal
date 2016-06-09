package ar.fiuba.tdd.tp.red.server;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.exceptions.AddingPlayerException;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class ServerPortListenerThread extends Thread implements GameObserver {

    private int portNumber;
    private GameBuilder gameBuilder;
    private Game game;
    ServerSocket serverSocket;
   // private List<ServerClientThread> clientThreads = new ArrayList<>();
    Map<Integer, ServerClientThread> clientThreads = new HashMap<>();
    private CommandInterpreter interpreter = new CommandInterpreter();

    public ServerPortListenerThread(int portNumber, GameBuilder gameBuilder) {
        super("ServerPortListenerThread" + portNumber);
        this.portNumber = portNumber;
        this.gameBuilder = gameBuilder;
    }

    public void run() {
        try {
            game = gameBuilder.build();
            game.registerObserver(this);
            serverSocket = new ServerSocket(portNumber);
            while (!this.isInterrupted()) {
                // Starts new client thread
                int playerNumber = clientThreads.size() + 1;
                ServerClientThread clientThread = new ServerClientThread(serverSocket.accept(), this, playerNumber);
                clientThread.start();
                System.out.println("New connection in port " + portNumber + "! Client amount: " + playerNumber);

                if (this.game.getPlayerManager().isPossibleAddPlayer()) {
                    clientThreads.put(playerNumber, clientThread);
                } else {
                    clientThread.sendMessage("More players are not allowed. Goodbye");
                    clientThread.interrupt();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void interrupt() {
        for (Thread thread : clientThreads.values()) {
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
            newClientThread.sendMessage("More players are not allowed. Goodbye player " + newClientThread.getPlayerNumber());
            //clientThreads.remove(newClientThread.getPlayerNumber());
        }
    }

    public void processMessageByClientEvent(ServerClientThread clientThread, String command) {

        if (game.isFinished()) {
            String endGameMsg = "Game Over - Press Exit";
            clientThread.sendMessage(endGameMsg);
            return;
        }

        int playerNumber = clientThread.getPlayerNumber();

        Player player = this.game.getPlayerManager().getPlayer(playerNumber);
        this.game.setPlayer(player);

        // Notifies remaining clients
        notify(clientThread, "Player " + playerNumber + " executed: " + command);

        String response = game.play(interpreter.getCommandForDriver(command));
        clientThread.sendMessage(response);
        verifyAndNotifyWinnig(clientThread);
    }

    public void notify(ServerClientThread notifier, String msg) {
        for (ServerClientThread client : clientThreads.values()) {
            if (!client.isInterrupted() && client != notifier) {
                client.sendMessage(msg);
            }
        }
    }

    @Override
    public void update() {
        String eventMessage = this.game.getEventMessage();
        this.notifyAllClients(eventMessage);
    }

    private void notifyAllClients(String message) {
        for (ServerClientThread client : clientThreads.values()) {
            client.sendMessage(message);
        }
    }

    private void verifyAndNotifyWinnig(ServerClientThread clientThread) {
        if (game.isFinished()) {
            String endGameMsg = "Game Over - The winner is Player " + clientThread.getPlayerNumber();
            notify(clientThread, endGameMsg);
        }
    }

}
