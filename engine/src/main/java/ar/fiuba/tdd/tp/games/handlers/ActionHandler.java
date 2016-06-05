package ar.fiuba.tdd.tp.games.handlers;

import ar.fiuba.tdd.tp.red.server.Command;

/**
 * Created by swandelow on 4/23/16.
 */
@FunctionalInterface
public interface ActionHandler {

    String execute(Command command);
}