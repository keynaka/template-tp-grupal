package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.exceptions.GameException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sebass on 07/06/16.
 */
public class PlayerManager {

    private static final String PREFIX_PLAYER_NAME = "player";

    private Map<Integer, Player> players = new HashMap<>();
    private PlayerCreator playerCreator;
    private Integer maxAmountOfPlayer;
    private Boolean allowMultiplayer;

    public PlayerManager(PlayerCreator playerCreator, Integer maxAmountOfPlayer, Boolean allowMultiplayer) {
        this.playerCreator = playerCreator;
        this.maxAmountOfPlayer = maxAmountOfPlayer;
        this.allowMultiplayer = allowMultiplayer;
    }

    public void addNewPlayer(Integer playerNumber) {
        if (this.isPossibleAddPlayer()) {
            String playerName = PREFIX_PLAYER_NAME.concat(String.valueOf(playerNumber));
            this.playerCreator.setPlayerName(playerName);
            Player player = this.playerCreator.create();
            this.players.put(playerNumber, player);
        }
        throw new GameException("It's not possible add new player.");
    }

    public Player getPlayer(Integer playerNumber) {
        return this.players.get(playerNumber);
    }


    private boolean isPossibleAddPlayer() {
        boolean isFirstPlayer = this.players.isEmpty();
        return isFirstPlayer || (this.allowMultiplayer && this.players.size() < this.maxAmountOfPlayer);
    }
}
