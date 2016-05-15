package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/25/16.
 */
public abstract class AbstractGameWithStage extends AbstractGame {

    protected Stage stage;

    protected AbstractGameWithStage(String gameName, String endGameMessage) {
        super(gameName, endGameMessage);
    }

    @Override
    protected void doStart() {
        this.stage = new Stage();
    }

    @Override
    protected void registerKnownActions() {
        this.knownActions.put(new Action("Look Around"), (itemName, args) -> this.stage.lookAround());
    }

}
