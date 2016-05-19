package ar.fiuba.tdd.tp.red;

import ar.fiuba.tdd.tp.games.creation.GamesList;
import ar.fiuba.tdd.tp.red.server.Server;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException {

//        GamesList.setGame(new HanoiTowers());

        new Server().run();
        System.out.println("Server Disconnect");
        System.exit(0);
    }
}