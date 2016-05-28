package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.red.server.Command;

/**
 * Created by swandelow on 4/23/16.
 */
@FunctionalInterface
public interface ActionFunction {

    String execute(Command command);
}