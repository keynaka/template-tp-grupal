package ar.fiuba.tdd.tp;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerPortListenerThread extends Thread {

    private int portNumber;
    private Server server;

    public ServerPortListenerThread(int portNumber, Server server) {
        super("ServerPortListenerThread" + portNumber);
        this.portNumber = portNumber;
        this.server = server;

        server.activePortThreads++;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server is now listening at port " + portNumber);
            boolean listening = true;
            while (listening) {
                // Starts new thread
                new ServerClientThread(serverSocket.accept(), server).start();
                server.clientAmount++;
                System.out.println("New connection in port " + portNumber + "! Client amount: " + server.clientAmount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
