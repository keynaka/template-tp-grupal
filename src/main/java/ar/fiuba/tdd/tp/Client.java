package ar.fiuba.tdd.tp;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

public class Client {

    public static void main(String[] args) {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
        try {
            System.out.print("Host [127.0.0.1]: ");
            String hostName = stdIn.readLine();
            if (hostName == null) {
                hostName = "localhost";
            }

            System.out.print("Port Number [8080]: ");
            String bufferAux = stdIn.readLine();
            int portNumber;
            if (bufferAux == null || bufferAux.isEmpty()) {
                portNumber = 8080;
            } else {
                portNumber = Integer.parseInt(bufferAux);
            }

            new Client().run(hostName, portNumber);
        } catch (IOException e) {
            System.err.println("IOException!");
        } catch (NumberFormatException e) {
            System.err.println("Invalid Format!");
        }
    }

    public void run(String hostName, int portNumber) {
        try {
            Socket serverSocket = new Socket(hostName, portNumber);
            ObjectOutputStream out = new ObjectOutputStream(serverSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());

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
