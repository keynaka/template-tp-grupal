package ar.fiuba.tdd.tp;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Port Number: ");
        try {
            int portNumber = Integer.parseInt(stdIn.readLine());
            new Client().run(portNumber);
        } catch (IOException e) {
            System.err.println("IOException!");
        } catch (NumberFormatException e) {
            System.err.println("Invalid Format!");
        }
    }

    public void run(int portNumber) {
        String hostName = "127.0.0.1";
        try (
                Socket serverSocket = new Socket(hostName, portNumber);
                ObjectOutputStream out = new ObjectOutputStream(serverSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());
        ) {
            // stdIn is the client keyboard buffer
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
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
