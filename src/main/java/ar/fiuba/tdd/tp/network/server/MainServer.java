package ar.fiuba.tdd.tp.network.server;

import java.io.IOException;

/**
 * Created by Nico on 25/05/2016.
 */
public class MainServer {
    public static void main(String[] args) throws IOException {
        new Server().run();
        System.out.println("Server Disconnect");
        System.exit(0);
    }
}
