package ar.fiuba.tdd.tp.red;

import java.io.Serializable;

/**
 * Created by Gonzalo on 26/04/2016.
 */
public class Response implements Serializable {

    private String response;

    public Response(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

}
