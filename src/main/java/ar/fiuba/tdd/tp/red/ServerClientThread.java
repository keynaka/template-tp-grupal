package ar.fiuba.tdd.tp.red;

import ar.fiuba.tdd.tp.games.Game;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ServerClientThread extends Thread {

    private Socket clientSocket;
    private Server server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Command command;
    private Response response;
    private Game game;
    private CommandInterpreter interpreter;

    public ServerClientThread(Socket clientSocket, Server server, Game game) {
        super("ServerThread" + server.getClientAmount());
        try {
            this.clientSocket = clientSocket;
            this.server = server;
            this.in = new ObjectInputStream(clientSocket.getInputStream());
            this.out = new ObjectOutputStream(clientSocket.getOutputStream());
            this.game = game;
            this.interpreter = new CommandInterpreter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            welcomeToClient();
            readMessage();

            while (!gameEnded()) {
                processGame();
                sendMessage();

                readMessage();
            }
            disconnectClient();
            clientSocket.close();

        } catch (ClassNotFoundException e) {
            disconnectClient();
            System.err.println("Unkown object recived from socket.");
        } catch (SocketException e) {
            System.out.println("Client has gone away.");
            disconnectClient();
        } catch (Exception e) {
            disconnectClient();
            e.printStackTrace();
        }
    }

    private void welcomeToClient() throws Exception {
        this.game.start(); // Starts the game

        StringBuilder welcomeMessage = new StringBuilder();
        welcomeMessage.append("Welcome to port " + clientSocket.getLocalPort() + "! ");
        welcomeMessage.append("\nYou are the client number " + server.getClientAmount() + '.');
        welcomeMessage.append("\nAre you ready to play '" + this.game.getName() + "'?");
        response = new Response(welcomeMessage.toString());
        out.writeObject(response);
    }

    private void readMessage() throws Exception {
        if (!game.isFinished()) {
            //command = (Command) in.readObject();
            String rawCommand = (String)in.readObject();
            this.command = this.interpreter.getCommand(rawCommand);
        }
    }

    private void processGame() throws Exception {
        String responseStr = game.play(command);
        response = new Response(responseStr);
        response.setGameFinalized(game.isFinished());
    }

    private void sendMessage() throws Exception {
        out.writeObject(response);
    }

    private void disconnectClient() {
        server.decrementedClientAmount();
        System.out.println("Client amount: " + server.getClientAmount());
    }

    private boolean gameEnded() {
        return game.isFinished();
    }
}
