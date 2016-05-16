package ar.fiuba.tdd.tp.red;

import ar.fiuba.tdd.tp.games.creation.GamesList;
import ar.fiuba.tdd.tp.games.hanoitowers.HanoiTowers;
import ar.fiuba.tdd.tp.games.opendoor.OpenDoorNew;
import ar.fiuba.tdd.tp.games.woolfsheepcabbage.WolfSheepCabbage;
import ar.fiuba.tdd.tp.red.server.Server;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException {

        GamesList.setGame(new OpenDoorNew());
        GamesList.setGame(new HanoiTowers());
        GamesList.setGame(new WolfSheepCabbage());

        new Server().run();
        System.out.println("Server Disconnect");
        System.exit(0);
    }
}
