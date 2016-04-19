package ar.fiuba.tdd.tp;

public abstract class GameTemplate {
    protected int playersCount;

    public abstract void initializeGame();

    public abstract void makePlay(int player);

    public abstract boolean endOfGame();

    public abstract void printWinner();

    public final void playOneGame() {

        initializeGame();

        int player = 0;
        while (!endOfGame()) {
            makePlay(player);
            player = (player + 1) % playersCount;
        }
        printWinner();

    }

}
