package ar.fiuba.tdd.tp.red;

import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.games.fetchquest.FetchQuest2;
import ar.fiuba.tdd.tp.games.hanoitowers.HanoiTowers;
import ar.fiuba.tdd.tp.games.opendoor.OpenDoor;
import ar.fiuba.tdd.tp.games.opendoor.OpenDoor2;
import ar.fiuba.tdd.tp.games.woolfsheepcabbage.WolfSheepCabbage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

public class Server {

    private int clientAmount = 0; // Counts total connections
    private int maxClientAmount = 4;
    protected Collection<Integer> openPorts = new ArrayList<Integer>(); // List of all opened ports
    private boolean online = true; // Indicates if the server is online
    private int defaultPort = 8080;
    private int timeSleepThread = 4000;
    private Map<String, Game> games;


    // Runs the server and starts listening
    public void run() {
        loadGames();
        for (int i = 0; i < maxClientAmount; i++) {
            int portNumber = i + defaultPort;
            this.listenPort(portNumber);
        }

        String answer = "";
        while (openPorts.size() > 0) {
            try {
                Thread.sleep(timeSleepThread);
                //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
                //answer = stdIn.readLine();
                //String delimitadores = "[ .,;?!¡¿\'\"\\[\\]]+";
                //String[] palabrasSeparadas = answer.split(delimitadores);
                //System.out.println( palabrasSeparadas [0]);
                //System.out.println(palabrasSeparadas [1]);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void listenPort(int portNumber) {
        if (!openPorts.contains(portNumber)) {
            Game game = loadGame(portNumber);
            new ServerPortListenerThread(portNumber, this, game).start();
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

    private Game loadGame(int portNumber) {

        Game game;
        String gameStr = setGame();
        game = searchGame(gameStr);
        boolean searchedGame = (game != null);

        while (!searchedGame) {
            System.out.println("Error - Game not found ");
            gameStr = setGame();
            game = searchGame(gameStr);
            searchedGame = (game != null);
        }

        System.out.println('"' + game.getName() + '"' + " loaded and listening on port " + portNumber);

        return game;
    }

    private String setGame() {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
        String gameStr = null;
        try {
            System.out.println("Type a game:");
            gameStr = stdIn.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gameStr;
    }

    private Game searchGame(String gameStr) {
        if (gameStr == null) {
            return null;
        }
        return games.get(gameStr);
    }

    private void loadGames() {
        games = new HashMap<String, Game>();
        games.put("fetch quest", new FetchQuest2());
        games.put("hanoi towers", new HanoiTowers());
        games.put("open door", new OpenDoor());
        games.put("open door 2", new OpenDoor2());
        games.put("wolf", new WolfSheepCabbage());
    }
}