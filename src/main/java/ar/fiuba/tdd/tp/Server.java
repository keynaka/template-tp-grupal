package ar.fiuba.tdd.tp;

import java.io.*;
import java.net.*;

public class Server extends Network{

    private  ServerSocket gameOneListener;

    public Server() {
        System.out.println("Server initialazing..");
    }

    public void createSocket() {
        try {
            gameOneListener = new ServerSocket(gamePort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitClient() {
        System.out.println("Waiting for client..");
        try {
            socket = gameOneListener.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.out.println("Server clossed..");
        super.close();
        try {
            gameOneListener.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}