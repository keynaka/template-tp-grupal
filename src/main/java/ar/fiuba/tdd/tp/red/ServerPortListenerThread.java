package ar.fiuba.tdd.tp.red;

import ar.fiuba.tdd.tp.games.Game;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerPortListenerThread extends Thread {

    private int portNumber;
    private Server server;
    private Game game;

    public ServerPortListenerThread(int portNumber, Server server, Game game) {
        super("ServerPortListenerThread" + portNumber);
        this.portNumber = portNumber;
        this.server = server;
        this.game = game;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(this.portNumber);

            while (this.server.isOnline()) {
                // Starts new thread
                new ServerClientThread(serverSocket.accept(), this.server, this.game).start();
                this.server.increaseClientAmount();
                System.out.println("New connection in port " + this.portNumber + "! Client amount: " + this.server.getClientAmount());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
