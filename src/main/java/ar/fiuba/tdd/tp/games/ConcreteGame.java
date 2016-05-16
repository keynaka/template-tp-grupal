package ar.fiuba.tdd.tp.games;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Created by sebass on 10/05/16.
 */
public class ConcreteGame extends AbstractGame {

    private static final String DEFAULT_END_GAME_MSG = "You won the game!";

    private String name;
    private String gameDescription;
    private String endGameMessage;
    private Player player;
    private Map<String, Stage> stages;
    private Predicate<ConcreteGame> winningCondition;

    public ConcreteGame() {
        super("", "");
        this.knownActions = new HashMap<>();
        this.stages = new HashMap<>();
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
        return this.endGameMessage != null ? this.endGameMessage : DEFAULT_END_GAME_MSG;
    }

    public void setEndGameMessage(String endGameMessage) {
        this.endGameMessage = endGameMessage;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setWinningCondition(Predicate<ConcreteGame> winningCondition) {
        this.winningCondition = winningCondition;
    }

    public void registerKnownAction(Action action, ActionFunction actionFuntion) {
        this.knownActions.put(action, actionFuntion);
    }

    public void addStage(Stage stage) {
        this.stages.put(stage.getName(), stage);
    }

    public Stage getCurrentStage() {
        String currentStageName = this.player.getCurrentStage();
        return this.stages.get(currentStageName);
    }

    // TODO Estos métodos probablemente ya no sean necesarios en el futuro.
    @Override
    public boolean isFinished() {
        return this.winningCondition.test(this);
    }

    @Override
    public String start() {
        // no hagas nada por ahora
        return "Empezo el juego.";
    }

    @Override
    protected void doStart() {
        // no hagas nada por ahora
    }

    @Override
    protected void registerKnownActions() {
        // no hagas nada por ahora
    }
}
