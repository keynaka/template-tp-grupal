package ar.fiuba.tdd.tp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public abstract class Network {

    protected Socket socket = null;

    protected BufferedReader in = null;

    protected PrintStream out = null;

    protected int gamePort = 8082;

    public abstract void createSocket();

    public Socket getSocket() {
        return this.socket;
    }

    public BufferedReader getInThread() {
        return this.in;
    }

    public PrintStream getOutThread() {
        return this.out;
    }

    public void createInputOutputThreads() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            out = new PrintStream(socket.getOutputStream(),false,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
