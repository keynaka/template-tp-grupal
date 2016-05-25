package ar.fiuba.tdd.tp.network.server;

import ar.fiuba.tdd.tp.engine.core.Game;
import ar.fiuba.tdd.tp.engine.core.GameBuilder;
import ar.fiuba.tdd.tp.network.driver.GameDriver;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Nico on 25/05/2016.
 */
public class ServerPortListenerThread extends Thread {

    private int portNumber;
    private Server server;
    private GameDriver gameDriver;

    public ServerPortListenerThread(int portNumber, Server server, GameDriver gameDriver) {
        super("ServerPortListenerThread" + portNumber);
        this.portNumber = portNumber;
        this.server = server;
        this.gameDriver = gameDriver;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(this.portNumber);

            while (this.server.isOnline()) {
                // Starts new thread
                new ServerClientThread(serverSocket.accept(), this.server, gameDriver).start();
                this.server.increaseClientAmount();
                System.out.println("New connection in port " + this.portNumber + "! Client amount: " + this.server.getClientAmount());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}