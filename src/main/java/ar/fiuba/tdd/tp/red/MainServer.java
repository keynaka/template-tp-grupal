package ar.fiuba.tdd.tp.red;

import ar.fiuba.tdd.tp.games.creation.GamesList;
<<<<<<< HEAD
import ar.fiuba.tdd.tp.games.woolfsheepcabbage.WolfSheepCabbage;
=======
import ar.fiuba.tdd.tp.games.hanoitowers.HanoiTowers;
>>>>>>> develop
import ar.fiuba.tdd.tp.red.server.Server;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException {

<<<<<<< HEAD
        GamesList.setGame(new WolfSheepCabbage());
=======
        GamesList.setGame(new HanoiTowers());
>>>>>>> develop

        new Server().run();
        System.out.println("Server Disconnect");
        System.exit(0);
    }
}
