package ar.fiuba.tdd.tp.red.server;

import ar.fiuba.tdd.tp.games.creation.GamesCreator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;

public class Server {

    private int clientAmount = 0; // Counts total connections
    private Integer maxClientAmount = 1;
    protected Collection<Integer> openPorts = new ArrayList<Integer>(); // List of all opened ports
    private boolean online = true; // Indicates if the server is online
    private int defaultPort = 8080;

    // Runs the server and starts listening
    public void run() {
        this.maxClientAmount = readAmountOfGames();
        for (int i = 0; i < maxClientAmount; i++) {
            int portNumber = i + defaultPort;
            this.listenPort(portNumber);
        }

        String inOptional = "";
        while (!"exit".equalsIgnoreCase(inOptional)) {
            try {
                inOptional = userInput();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private Integer readAmountOfGames() {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
        Integer amountOfGames = 1;
        try {
            System.out.print("> Amount of games: ");
            amountOfGames = Integer.valueOf(stdIn.readLine());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error. Using default value: 1");
        }
        return amountOfGames;
    }

    private String userInput() {
        try {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
            System.out.print("> ");
            return stdIn.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void listenPort(int portNumber) {
        if (!openPorts.contains(portNumber)) {
            String gameName = loadGame(portNumber);
            new ServerPortListenerThread(portNumber, this, gameName).start();
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

    private String loadGame(int portNumber) {

        String gameStr = getGame();
        boolean searchedGame = searchGame(gameStr);

        while (!searchedGame) {
            System.out.println("Error - Game not found ");
            gameStr = getGame();
            searchedGame = searchGame(gameStr);
        }

        System.out.println('"' + gameStr + '"' + " loaded and listening on port " + portNumber);

        return gameStr;
    }

    private String getGame() {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
        String gameStr = null;
        try {
            System.out.print("> load game ");
            gameStr = stdIn.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gameStr;
    }

    private boolean searchGame(String gameStr) {
        if (gameStr == null) {
            return false;
        }
        return GamesCreator.existGame(gameStr);
    }
}