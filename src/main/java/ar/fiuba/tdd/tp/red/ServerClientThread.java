package ar.fiuba.tdd.tp.red;

import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.games.fetchquest.FetchQuest2;

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
    private static String exitServer = "exit";

    public ServerClientThread(Socket clientSocket, Server server) {
        super("ServerThread" + server.getClientAmount());
        try {
            this.clientSocket = clientSocket;
            this.server = server;
            this.in = new ObjectInputStream(clientSocket.getInputStream());
            this.out = new ObjectOutputStream(clientSocket.getOutputStream());
            //TODO Juego pre seteado - Cambiarlo
            this.game = new FetchQuest2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            welcomeToClient();
            readMessage();

            while (!exitToServer()) {
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
        StringBuilder welcomeMessage = new StringBuilder();
        welcomeMessage.append("Bienvenido al puerto " + clientSocket.getLocalPort() + "! ");
        welcomeMessage.append("\nUsted es el cliente numero " + server.getClientAmount());
        String welcomeStart = game.start();
        welcomeMessage.append("\nUsted va a jugar al juego: " + welcomeStart);
        response = new Response(welcomeMessage.toString());
        out.writeObject(response);
    }

    private void readMessage() throws Exception {
        command = (Command) in.readObject();
    }

    private void processGame() throws Exception {
        String responseStr = game.play(command);
        response = new Response(responseStr);
        //command = new Command(response);
    }

    private void sendMessage() throws Exception {
        out.writeObject(response);
    }

    private void disconnectClient() {
        server.decrementedClientAmount();
        System.out.println("Client amount: " + server.getClientAmount());
    }

    private boolean exitToServer() {
        return exitServer.equalsIgnoreCase(command.getItemName());
    }
}
