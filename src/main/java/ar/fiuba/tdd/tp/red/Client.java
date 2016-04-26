package ar.fiuba.tdd.tp.red;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

public class Client {

    private Socket serverSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String hostName;

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
            // stdIn is the client keyboard buffer
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));

            String fromUser;
            DTO dto;

            // Reads server messages as DTO objects
            while ((dto = (DTO)in.readObject()) != null) {
                // Shows Server's reply
                System.out.println("Server: " + dto.attr1);

                // Handle client exit
                if (dto.attr1.equals("exit")) {
                    break;
                }

                // Reads from command line and sends it to the server
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    dto = new DTO(fromUser, "");
                    out.writeObject(dto);
                }
            }

            serverSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
        } catch (ClassNotFoundException e) {
            System.err.println("Unkown object recived from socket.");
        }
    }

}
