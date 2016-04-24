package ar.fiuba.tdd.tp;

import java.io.*;
import java.net.*;

public class ServerClientThread extends Thread {

    private Socket clientSocket = null;
    private Server server;

    public ServerClientThread(Socket clientSocket, Server server) {
        super("ServerThread" + server.getClientAmount());
        this.clientSocket = clientSocket;
        this.server = server;
    }

    public void run() {
        try {

            DTO outputDto = new DTO();
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            welcomeToClient(outputDto, out);

            DTO inputDto;
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            while ((inputDto = (DTO)in.readObject()) != null) {
                outputDto = inputDto;
                outputDto.attr1 = "Response: " + outputDto.attr1;
                out.writeObject(outputDto);
                if (inputDto.attr1.equals("exit")) {
                    server.decrementedClientAmount();
                    System.out.println("Client amount: " + server.getClientAmount());
                    break;
                }
            }
            clientSocket.close();

        } catch (ClassNotFoundException e) {
            System.err.println("Unkown object recived from socket.");
        } catch (SocketException e) {
            server.decrementedClientAmount();
            System.out.println("Client has gone away. Client amount: " + server.getClientAmount());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void welcomeToClient(DTO outputDto, ObjectOutputStream out) throws Exception {
        outputDto.attr1 = "Bienvenido al puerto " + clientSocket.getLocalPort() + "! ";
        outputDto.attr1 += "Usted es el cliente numero " + server.getClientAmount();
        out.writeObject(outputDto);
    }

}
