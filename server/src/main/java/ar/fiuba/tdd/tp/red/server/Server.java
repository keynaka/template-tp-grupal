package ar.fiuba.tdd.tp.red.server;

import ar.fiuba.tdd.tp.games.GameBuilder;
import ar.fiuba.tdd.tp.games.fetchquest.FetchQuestBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private String userInput = "";
    private int defaultPort = 8080;
    private List<Thread> portThreads = new ArrayList<>();

    // Runs the server and starts listening
    public void run() {
        while (!isExit()) {
            this.userInput = getUserInput();
            if (userInput != null && userInput.matches("^(?i)load game .+\\.jar$")) {
                String gameName = userInput.split(" ")[2];
                loadGame(gameName);
            }
        }

        exitServer();
    }

    private boolean isExit() {
        return userInput.equalsIgnoreCase("exit");
    }

    private String getUserInput() {
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
            //GameBuilder gameBuilder = new FetchQuestBuilder();
            listenPort(defaultPort++, gameBuilder);

            System.out.println('"' + gameName + '"' + " loaded and listening on port " + (defaultPort - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listenPort(int portNumber, GameBuilder gameBuilder) {
        ServerPortListenerThread thread = new ServerPortListenerThread(portNumber, gameBuilder);
        thread.start();
        portThreads.add(thread);
    }

    public void exitServer() {
        for (Thread thread : portThreads) {
            thread.interrupt();
        }
    }
}