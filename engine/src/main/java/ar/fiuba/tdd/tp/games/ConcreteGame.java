package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.driver.GameState;
import ar.fiuba.tdd.tp.games.handlers.ActionHandler;
import ar.fiuba.tdd.tp.games.rules.Rule;
import ar.fiuba.tdd.tp.games.timer.GameTimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Created by sebass on 10/05/16.
 */
@SuppressWarnings("CPD-START")
public class ConcreteGame extends AbstractGame {

    private static final String DEFAULT_WON_GAME_MSG = "You won the game!";
    private static final String DEFAULT_LOST_GAME_MSG = "You lost the game!";

    private String name;
    private String gameDescription;
    private String endGameMessage;
    private Player player;
    private Map<String, Stage> stages;
    private Predicate<ConcreteGame> winningCondition;
    private Rule winningConditionRule;
    private Rule losingConditionRule;
    private Predicate<ConcreteGame> losingCondition = (game) -> false;
    private GameState gameState;
    private List<GameObserver> gameObservers = new ArrayList<>();
    private PlayerManager playerManager;
    private String eventMessage = "soy un mensaje asíncrono.";
    private GameTimer timer;

    public ConcreteGame() {
        super("", "");
        this.knownActions = new HashMap<>();
        this.stages = new HashMap<>();
        this.gameState = GameState.Ready;
    }

    public ConcreteGame(String name, String endGameMessage) {
        super(name, endGameMessage);
        this.knownActions = new HashMap<>();
        this.stages = new HashMap<>();
    }

    @Override
    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return this.gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public String getEndGameMessage() {
        return this.endGameMessage != null ? this.endGameMessage : DEFAULT_WON_GAME_MSG;
    }

    public void setEndGameMessage(String endGameMessage) {
        this.endGameMessage = endGameMessage;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void registerObserver(GameObserver observer) {
        this.gameObservers.add(observer);
    }

    @Override
    public String getEventMessage() {
        return this.eventMessage;
    }

    public void setEventMessage(String eventMessage) {
        this.eventMessage = eventMessage;
    }

    public void notifyObservers() {
        for (GameObserver observer : this.gameObservers) {
            observer.update();
        }
    }

    public GameTimer getTimer() {
        return timer;
    }

    public void setTimer(GameTimer timer) {
        this.timer = timer;
    }

    @Deprecated
    public void setWinningCondition(Predicate<ConcreteGame> winningCondition) {
        this.winningCondition = winningCondition;
    }

    public void setWinningCondition(Rule winningCondition) {
        this.winningConditionRule = winningCondition;
    }

    @Deprecated
    public void setLosingCondition(Predicate<ConcreteGame> losingCondition) {
        this.losingCondition = losingCondition;
    }

    public void setLoosingCondition(Rule loosingCondition) {
        this.losingConditionRule = loosingCondition;
    }

    public void registerKnownAction(ActionOld action, ActionHandler actionFuntion) {
        this.knownActions.put(action, actionFuntion);
    }

    public void addStage(Stage stage) {
        this.stages.put(stage.getName(), stage);
    }

    public void setStages(List<Stage> stages) {
        for (Stage stage : stages) {
            addStage(stage);
        }
    }

    public Stage getCurrentStage() {
        String currentStageName = this.player.getCurrentStage();
        return this.stages.get(currentStageName);
    }

    public Stage getStage(String stageName) {
        return this.stages.get(stageName);
    }

    public Map<String, Stage> getStages() {
        return this.stages;
    }

    @Override
    public boolean isFinished() {
        return GameState.Won.equals(this.gameState) || GameState.Lost.equals(this.gameState);
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    public GameObjectRepository getGameObjectRepository() {
        return new GameObjectRepository(this);
    }

    @Override
    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Override
    protected void updateGameState() {
        if (winningCondition != null) {
            this.oldUpdateGameState();
        } else {
            this.newUpdateGameState();
        }
    }

    private void newUpdateGameState() {
        if (this.winningConditionRule.verify()) {
            this.gameState = GameState.Won;
            this.endGameMessage = DEFAULT_WON_GAME_MSG;

        } else if (this.losingConditionRule != null && this.losingConditionRule.verify()) {
            this.gameState = GameState.Lost;
            this.endGameMessage = DEFAULT_LOST_GAME_MSG;

        } else {
            this.gameState = GameState.InProgress;
        }
    }

    private void oldUpdateGameState() {
        if (this.winningCondition.test(this)) {
            this.gameState = GameState.Won;
            this.endGameMessage = DEFAULT_WON_GAME_MSG;
        } else if (this.losingCondition.test(this)) {
            this.gameState = GameState.Lost;
            this.endGameMessage = DEFAULT_LOST_GAME_MSG;
        } else {
            this.gameState = GameState.InProgress;
        }
    }

    // TODO Estos métodos probablemente ya no sean necesarios en el futuro.
    @Override
    public String start() {
        // no hagas nada por ahora
        return "Empezo el juego.";
    }

    @Override
    protected void doStart() {
        // no hagas nada por ahora
    }

    @SuppressWarnings("CPD-END")

    @Override
    protected void registerKnownActions() {
        // no hagas nada por ahora
    }
}
