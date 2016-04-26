package ar.fiuba.tdd.tp.red;

import java.util.ArrayList;
import java.util.Collection;

public class Server {

    private int clientAmount = 0; // Counts total connections
    private int maxClientAmount = 4;
    protected Collection<Integer> openPorts = new ArrayList<Integer>(); // List of all opened ports
    private boolean online = true; // Indicates if the server is online
    private int defaultPort = 8080;
    private int timeSleepThread = 4000;


    // Runs the server and starts listeing
    public void run() {
        for (int i = 0; i < maxClientAmount; i++) {
            int portNumber = i + defaultPort;
            this.listenPort(portNumber);
        }

        try {
            while (openPorts.size() > 0) {
                Thread.sleep(timeSleepThread);
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

    protected void increaseClientAmount() {
        this.clientAmount++;
    }

    protected void decrementedClientAmount() {
        this.clientAmount--;
    }

    public int getClientAmount() {
        return clientAmount;
    }

    public boolean isOnline() {
        return online;
    }
}