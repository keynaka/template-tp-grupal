package ar.fiuba.tdd.tp;

public class MainServer {
    public static void main(String[] args) {
        /*
        Server oneServer = new Server();
        oneServer.createSocket();
        oneServer.waitClient();
        oneServer.createInputOutputThreads();

        String answer = "Message Received Correctly --> ";
        String msg = "";
        while (true) {
            try {
                msg = oneServer.getInThread().readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if ("FIN".equals(msg)) {
                break;
            } else {
                System.out.println("Mensaje del cliente [" + oneServer.getSocket().getInetAddress().getHostName() + "] : "
                        + msg);

                oneServer.getOutThread().println(answer + msg);
            }
        }
        oneServer.close();
        */
    }
}

