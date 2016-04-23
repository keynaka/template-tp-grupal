package ar.fiuba.tdd.tp;

import java.io.IOException;

public class Server {

    public int clientAmount = 0; // Counts total connections
    public int activePortThreads = 0; // Amount of active threads listening to a specific port

    public static void main(String[] args) throws IOException {
        new Server().run();
    }

    public void run() {
        for (int i = 0;i < 4;i++) {
            int portNumber = i + 8080;
            new ServerPortListenerThread(portNumber, this).start();
        }

        try {
            while (this.activePortThreads > 0) {
                Thread.sleep(4000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}