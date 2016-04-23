package ar.fiuba.tdd.tp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Server {

    public int clientAmount = 0; // Counts total connections
    protected Collection<Integer> openPorts = new ArrayList<>(); // List of all opened ports
    public boolean online = true; // Indicates if the server is online

    public static void main(String[] args) throws IOException {
        new Server().run();
    }


    // Runs the server and starts listeing
    public void run() {
        for (int i = 0;i < 4;i++) {
            int portNumber = i + 8080;
            this.listenPort(portNumber);
        }

        try {
            while (openPorts.size() > 0) {
                Thread.sleep(4000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void listenPort(int portNumber) {
        if (!openPorts.contains(portNumber)) {
            new ServerPortListenerThread(portNumber, this).start();
            openPorts.add(portNumber);
        }
    }
}