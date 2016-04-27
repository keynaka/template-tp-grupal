package ar.fiuba.tdd.tp.red;

import java.io.Serializable;

/**
 * Created by Gonzalo on 26/04/2016.
 */
public class Response implements Serializable {

    private boolean gameFinalized;
    private String response;

    public Response(String response) {
        gameFinalized = false;
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public boolean isGameFinalized() {
        return gameFinalized;
    }

    public void setGameFinalized(boolean gameFinalized) {
        this.gameFinalized = gameFinalized;
    }
}
