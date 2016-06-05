package ar.fiuba.tdd.tp.red.server;

import java.io.*;
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
        } catch (SocketException e) {
            System.out.println("Client has gone away.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        disconnectClient();
    }

    public void sendMessage(String msg) {
        try {
            out.writeBytes(msg);
            out.flush(); // Force send
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
