package ar.fiuba.tdd.tp;

import java.io.Serializable;
/**
 * Data Transfer Object. Used as a package to be transfered through a socket.
 */
public class DTO implements Serializable {
    public String attr1 = "";
    public String attr2 = "";

    public DTO(String attr1, String attr2) {
        this.attr1 = attr1;
        this.attr2 = attr2;
    }

    public DTO() {}

}
