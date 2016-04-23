package ar.fiuba.tdd.tp;

import java.io.*;
import java.net.*;

public class ServerClientThread extends Thread {

    private Socket clientSocket = null;
    private Server server;

    public ServerClientThread(Socket clientSocket, Server server) {
        super("ServerThread" + server.clientAmount);
        this.clientSocket = clientSocket;
        this.server = server;
    }

    public void run() {
        try (
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        ) {
            DTO inputDto;
            DTO outputDto = new DTO();

            outputDto.attr1 = "Bienvenido al puerto " + clientSocket.getLocalPort() + "! Usted es el cliente numero " + server.clientAmount;

            out.writeObject(outputDto);

            while ((inputDto = (DTO)in.readObject()) != null) {
                outputDto = inputDto;
                out.writeObject(outputDto);
                if (inputDto.attr1.equals("exit")) {
                    server.clientAmount--;
                    break;
                }
            }
            clientSocket.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Unkown object recived from socket.");
        } catch (SocketException e) {
            System.out.println("Client has gone away.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
