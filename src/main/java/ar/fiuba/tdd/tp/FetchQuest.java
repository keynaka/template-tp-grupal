package ar.fiuba.tdd.tp;

import java.util.LinkedList;
import java.util.Queue;

public class FetchQuest extends GameTemplate {
    private Queue<String> server;
    private Queue<String> client;

    public FetchQuest() {

    }

    @Override
    public void initializeGame() {
        playersCount = 1;

        server = new LinkedList<String>();
        server.add("There is a stick in the room.");
        server.add("You won the game!");

        client = new LinkedList<String>();
        client.add("look around [Enter]");
        client.add("pick stick [Enter]");

        System.out.println(client.poll());
        pressEnter();
    }

    @Override
    public void makePlay(int player) {

        if (! server.isEmpty()) {
            System.out.println(server.poll());
        }
        if (! client.isEmpty()) {
            System.out.println(client.poll());
        }

        pressEnter();
    }

    @Override
    public boolean endOfGame() {
        return (client.isEmpty());
    }

    @Override
    public void printWinner() {
        System.out.println(server.poll());
    }

    private void pressEnter() {
        int pressed;
        try {
            pressed = System.in.read();
            if (pressed < 0) {
                throw new ReceiveNegativeCountOfInformation("Excepcion no puede recibir una cantidad de informacion "
                        + "negativa");
            }
        } catch (Exception e) {
            System.out.println("Error al leer el Enter");
        }
    }
}