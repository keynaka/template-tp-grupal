package ar.fiuba.tdd.tp.network.server;

import ar.fiuba.tdd.tp.network.driver.GameDriver;
import ar.fiuba.tdd.tp.network.driver.GameState;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Nico on 25/05/2016.
 */
public class ServerClientThread extends Thread {

    private Socket clientSocket;
    private Server server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String rawCommand;
    //private Command command;
    private Response response;
    private GameDriver gameDriver;
    //private CommandInterpreter interpreter;
    private boolean forceFinish = false;

    public ServerClientThread(Socket clientSocket, Server server, GameDriver gameDriver) {
        super("ServerThread" + server.getClientAmount());
        try {
            this.clientSocket = clientSocket;
            this.server = server;
            this.in = new ObjectInputStream(clientSocket.getInputStream());
            this.out = new ObjectOutputStream(clientSocket.getOutputStream());
            this.gameDriver = gameDriver;
            //this.interpreter = new CommandInterpreter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            welcomeToClient();
            readMessage();

            while (!gameEnded() && !this.forceFinish) {
                processGame();
                sendMessage();

                if (!this.forceFinish) {
                    readMessage();
                }
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
        StringBuilder welcomeMessage = new StringBuilder();
        welcomeMessage.append("Welcome to port " + clientSocket.getLocalPort() + "! ");
        welcomeMessage.append("\nYou are the client number " + server.getClientAmount() + '.');
        welcomeMessage.append("\nYou are going to play " + gameDriver.getGameName());
        this.response = new Response(welcomeMessage.toString());

        this.sendMessage();
    }

    private void readMessage() throws Exception {
        if (gameDriver.getCurrentState() != GameState.Lost) {
            //command = (Command) in.readObject();
            rawCommand = (String) in.readObject();
        }
    }

    private void processGame() throws Exception {
        String responseStr = gameDriver.sendCommand(rawCommand);
        response = new Response(responseStr);
    }

    private void sendMessage() throws Exception {
        out.writeObject(response);
    }

    private void disconnectClient() {
        server.decrementedClientAmount();
        System.out.println("Client amount: " + server.getClientAmount());
    }

    private boolean gameEnded() {
        return gameDriver.getCurrentState() == GameState.Lost || gameDriver.getCurrentState() == GameState.Won;
    }
}