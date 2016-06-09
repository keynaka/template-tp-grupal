package ar.fiuba.tdd.tp.red.client;

import java.io.DataInputStream;
import java.io.EOFException;
import java.net.SocketException;

/**
 * Created by Nico on 04/06/2016.
 */
public class ServerListenerThread extends Thread {
    private Client client;
    private DataInputStream in;

    public ServerListenerThread(Client client, DataInputStream in) {
        this.client = client;
        this.in = in;
    }

    public void run() {
        while (!this.isInterrupted()) {
            try {
                // Message from the server
                String msg = in.readUTF();
                System.out.println(msg);
            //} catch (EOFException e) {
                //client.connected = false; // Socket closed by server
            //} catch (SocketException  e) {
                //client.connected = false;
            } catch (Exception e) {
                //e.printStackTrace();
                client.connected = false;
                this.interrupt();
            }
        }
    }
}
