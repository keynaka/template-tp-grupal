package ar.fiuba.tdd.tp.red;

import java.io.*;
import java.net.*;

public class ServerClientThread extends Thread {

    private Socket clientSocket = null;
    private Server server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private DTO inputDto;
    private DTO outputDto;

    public ServerClientThread(Socket clientSocket, Server server) {
        super("ServerThread" + server.getClientAmount());
        try {
            this.clientSocket = clientSocket;
            this.server = server;
            this.in = new ObjectInputStream(clientSocket.getInputStream());
            this.out = new ObjectOutputStream(clientSocket.getOutputStream());
            this.inputDto = new DTO();
            this.outputDto = new DTO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            welcomeToClient(outputDto, out);

            while ((inputDto = (DTO)in.readObject()) != null) {
                outputDto = inputDto;
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
