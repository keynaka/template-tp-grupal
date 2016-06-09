package ar.fiuba.tdd.tp.red.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ServerClientThread extends Thread {

    private Socket clientSocket;
    private ServerPortListenerThread portThread;
    private int playerNumber;
    private DataInputStream in;
    private DataOutputStream out;

    public ServerClientThread(Socket clientSocket, ServerPortListenerThread portThread, int playerNumber) {
        try {
            this.clientSocket = clientSocket;
            this.portThread = portThread;
            this.playerNumber = playerNumber;
            this.in = new DataInputStream(clientSocket.getInputStream());
            this.out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void interrupt() {
        disconnectClient();
    }

    public void run() {
        try {
            portThread.newClientEvent(this);

            while (!this.isInterrupted()) {
                String msg = in.readUTF();
                portThread.processMessageByClientEvent(this, msg);
            }
        } catch (EOFException e) {
            System.out.println("Player " + playerNumber + " has exit.");
        } catch (SocketException e) {
            System.out.println("Player " + playerNumber + " has gone away.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        disconnectClient();
    }

    public void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
            out.flush(); // Force send
        } catch (SocketException e) {
            System.out.print("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void disconnectClient() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
