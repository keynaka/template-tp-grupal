package ar.fiuba.tdd.tp.red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class MainClient {
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

            new Client(hostName, portNumber).run();
        } catch (IOException e) {
            System.err.println("IOException!");
        } catch (NumberFormatException e) {
            System.err.println("Invalid Format!");
        }
    }
}
