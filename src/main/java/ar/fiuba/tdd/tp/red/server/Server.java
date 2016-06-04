package ar.fiuba.tdd.tp.red.server;

import ar.fiuba.tdd.tp.games.GameBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

public class Server {

    private String userInput = "";
    private int defaultPort = 8080;
    private List<Thread> portThreads = new ArrayList<>();

    // Runs the server and starts listening
    public void run() {
        while (!isExit()) {
            userInput();
            if (userInput != null && userInput.matches("^(?i)/load game .+\\.jar$")) {
                String gameName = userInput.split(" ")[2];
                loadGame(gameName);
            }
        }

        exitServer();
    }

    private boolean isExit() {
        return userInput.equalsIgnoreCase("exit");
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

    private void loadGame(String gameName) {
        try {
            GameBuilder gameBuilder = BuilderLoader.load(gameName);
            listenPort(defaultPort++, gameBuilder);

            System.out.println('"' + gameName + '"' + " loaded and listening on port " + defaultPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listenPort(int portNumber, GameBuilder gameBuilder) {
        ServerPortListenerThread thread = new ServerPortListenerThread(portNumber, gameBuilder);
        thread.run();
        portThreads.add(thread);
    }

    public void exitServer() {
        for (Thread thread : portThreads) {
            thread.interrupt();
        }
    }
}