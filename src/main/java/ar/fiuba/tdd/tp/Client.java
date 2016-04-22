package ar.fiuba.tdd.tp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client extends Network {

    public Client() {
        System.out.println("Client initialazing..");
    }

    public void createSocket() {
        try {
            socket = new Socket("localhost", gamePort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.out.println("Client clossed..");
        super.close();
    }

}
