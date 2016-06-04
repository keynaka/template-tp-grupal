package ar.fiuba.tdd.tp.red.server;

import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.games.GameBuilder;

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
                ServerClientThread clientThread = new ServerClientThread(serverSocket.accept(), this);
                clientThread.run();
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
        welcomeMessage += "\nYou are going to play " + game.getName();
        welcomeMessage += "\nYou are the player number " + clientThreads.size();
        newClientThread.sendMessage(welcomeMessage);

        // Notifies remaining clients
        notify(newClientThread, "Player number " + clientThreads.size() + " has logged in.")

    }

    public void notify(ServerClientThread notifier, String msg) {
        for (ServerClientThread client : clientThreads) {
            if (client != notifier) {
                client.sendMessage(msg);
            }
        }
    }
}
