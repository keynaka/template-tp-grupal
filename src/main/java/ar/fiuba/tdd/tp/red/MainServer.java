package ar.fiuba.tdd.tp.red;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException {
        new Server().run();
    }
}