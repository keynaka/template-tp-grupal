package ar.fiuba.tdd.tp.red;

import ar.fiuba.tdd.tp.games.Action;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

public class Client {

    private Socket serverSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String hostName;
    private String exitClient = "exit";
    private Command command;
    private Response response;

    public Client(String hostName, int portNumber) {
        this.hostName = hostName;
        try {
            serverSocket = new Socket(this.hostName, portNumber);
            out = new ObjectOutputStream(serverSocket.getOutputStream());
            in = new ObjectInputStream(serverSocket.getInputStream());
        } catch (IOException e) {
            System.err.println("IOException!");
        } catch (NumberFormatException e) {
            System.err.println("Invalid Format!");
        }
    }

    public void run() {
        try {
            receive();

            while (!exitToClient()) {

                String action = setInstruction("action");
                String item = setInstruction("item");

                send(action, item);
                receive();
            }
            serverSocket.close();

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
        } catch (Exception e) {
            System.err.println("Couldn't transfer data");
        }
    }

    private String setInstruction(String type) throws Exception {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
        System.out.println("Ingrese " + type + ": ");
        String instrution = stdIn.readLine();
        return instrution;
    }

    private void receive() throws Exception {
        response = (Response) in.readObject();
        System.out.println("Server: " + response.getResponse());
    }

    private void send(String actionStr, String item) throws Exception {

        if (actionStr != null) {
            Action action = Action.getActionByValue(actionStr);
            command = new Command(action, item);
            out.writeObject(command);
        }
    }

    private boolean exitToClient() {
        return exitClient.equalsIgnoreCase(response.getResponse());
    }
}
