package ar.fiuba.tdd.tp.red.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

public class Client {

    private Socket serverSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private String hostName;
    public boolean connected;
    private ServerListenerThread serverListenerThread;

    public Client(String hostName, int portNumber) {
        this.hostName = hostName;
        connected = true;
        try {
            serverSocket = new Socket(this.hostName, portNumber);
            out = new DataOutputStream(serverSocket.getOutputStream());
            in = new DataInputStream(serverSocket.getInputStream());
        } catch (IOException e) {
            System.err.println("IOException!");
        } catch (NumberFormatException e) {
            System.err.println("Invalid Format!");
        }
    }

    public void run() {
        try {
            // A thread that is always listening to the server
            serverListenerThread = new ServerListenerThread(this, in);
            serverListenerThread.start();

            while (connected) {
                String rawCommand = this.readRawCommand();
                sendRawCommand(rawCommand);
            }
            serverSocket.close();
            serverListenerThread.interrupt();

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
        } catch (Exception e) {
            System.err.println("Couldn't transfer data");
        }
    }

    private String readRawCommand() throws Exception {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
        System.out.print("> ");
        return stdIn.readLine();
    }


    private void sendRawCommand(String string) throws IOException {
        out.writeUTF(string);
        out.flush();
    }
}
