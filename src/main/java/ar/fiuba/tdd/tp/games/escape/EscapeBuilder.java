package ar.fiuba.tdd.tp.games.escape;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.behavior.BehaviorView;
import ar.fiuba.tdd.tp.games.exceptions.GameException;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;

import java.util.function.Predicate;

/**
 * Created by swandelow on 5/19/16.
 */

public class EscapeBuilder implements GameBuilder {

    ConcreteGame escape;

    @SuppressWarnings("CPD-START")
    @Override
    public Game build() {
        escape = new ConcreteGame();
        escape.setName("Escape");
        escape.setWinningCondition(this.buildWinningCondition());
        escape.setLoosingCondition(this.buildLoosingCondition());
        escape.setPlayer(this.buildPlayer());
        escape.addStage(this.buildHall());
        escape.addStage(this.buildRoom1());
        escape.addStage(this.buildRoom2());
        escape.addStage(this.buildRoom3());
        escape.addStage(this.buildLibraryHall());
        escape.addStage(this.buildLibrary());
        escape.addStage(this.buildSotano());
        escape.addStage(this.buildSotanoAbajo());
        escape.addStage(this.buildAfuera());
        registerKnownActions();
        return escape;
    }

    private Player buildPlayer() {
        Player player = new Player();
        player.addState("lifeStatus", "alive");
        player.setCurrentStage("Pasillo");
        player.addToInventory(new Item("Foto", "picture"));
        player.addToInventory(new Item("Lapicera", "pen"));
        return player;
    }

    private Predicate<ConcreteGame> buildWinningCondition() {
        return (game) -> game.getPlayer().getCurrentStage().equalsIgnoreCase("Afuera");
    }

    private Predicate<ConcreteGame> buildLoosingCondition() {
        return (game) -> buildLoosing(game);
    }

    private Boolean buildLoosing(ConcreteGame game) {
        Boolean isDead = game.getPlayer().getState("lifeStatus").equalsIgnoreCase("dead");
        Boolean hasHammer = game.getPlayer().getInventory().contains("Martillo");
        Boolean inSotanoAbajo = game.getPlayer().getCurrentStage().equalsIgnoreCase("SotanoAbajo");
        return isDead || (!hasHammer && inSotanoAbajo);
    }

    //----------------------------Handlers && Behaviors----------------------------------//
    private void registerKnownActions() {
        escape.registerKnownAction(Action.LOOK_AROUND, (itemName, args) -> this.lookAroundHandler());
        escape.registerKnownAction(Action.GOTO, (itemName, args) -> this.gotoHandler(itemName));
        escape.registerKnownAction(Action.PICK, (itemName, args) -> this.pickHandler(itemName));
        escape.registerKnownAction(Action.OPEN, (itemName, args) -> this.openHandler(itemName));
        escape.registerKnownAction(Action.MOVE, (itemName, args) -> this.moveHandler(itemName));
        escape.registerKnownAction(Action.USE, (itemName, arguments) -> this.useHandler(itemName));
        escape.registerKnownAction(Action.BREAK, (itemName, arguments) -> this.breakHandler(itemName));
        escape.registerKnownAction(Action.SHOW, (itemName, arguments) -> this.showHandler(itemName, arguments[0]));
        escape.registerKnownAction(Action.PUT, (itemName, arguments) -> this.putHandler(itemName, arguments[0]));
        escape.registerKnownAction(Action.DROP, (itemName, arguments) -> this.dropHandler(itemName));
    }

    private String dropHandler(String itemName) {
        escape.getPlayer().getInventory().dropItem(itemName);
        return String.format("You have dropped %s", itemName);
    }

    private String putHandler(String itemName, String argument) {
        Item item = escape.getPlayer().getInventory().getItem(argument);
        return item.execute(escape, Action.PUT);
    }

    private String useHandler(String itemName) {
        return escape.getCurrentStage().getItem(itemName).execute(escape, Action.USE);
    }

    private String breakHandler(String itemName) {
        return escape.getCurrentStage().getItem(itemName).execute(escape, Action.BREAK);
    }

    private String showHandler(String itemName, String receiver) {
        Item item = escape.getPlayer().getInventory().getItem(itemName);
        return item.execute(escape, Action.SHOW);
    }

    private String gotoHandler(String stageName) {
        String nextStageName;
        try {
            nextStageName = escape.getCurrentStage().getConsecutiveStage(stageName);
            Stage nextStage = escape.getStage(nextStageName);
            nextStage.enter(escape.getPlayer());
            return String.format("You have entered to %s.", nextStage.getName());
        } catch (GameException e) {
            return "Invalid stage.";
        }
    }

    private String pickHandler(String itemName) {
        return escape.getCurrentStage().getItem(itemName).execute(escape, Action.PICK);
    }

    private String moveHandler(String itemName) {
        return escape.getCurrentStage().getItem(itemName).execute(escape, Action.MOVE);
    }

    private String lookAroundHandler() {
        return escape.getCurrentStage().lookAround();
    }

    private String openHandler(String itemName) {
        Stage currentStage = escape.getCurrentStage();
        return currentStage.getItem(itemName).execute(escape, Action.OPEN);
    }

    //----------------------------Fin Handlers && Behaviors----------------------------------//

    //----------------------------Room1----------------------------------//
    private Stage buildRoom1() {
        Stage room1 = new Stage("Salon1");
        room1.addConsecutiveStage("Pasillo");
        room1.addItem(new Item("table", "it's a table."));
        room1.addItem(new Item("cup", "it's a cup2."));
        room1.addItem(new Item("cup2", "it's a cup2."));
        room1.addItem(new Item("chair1", "it's a chair."));
        room1.addItem(new Item("chair2", "it's a chair."));
        room1.addItem(this.buildLiquor());
        room1.addItem(this.buildTrainPicture());
        room1.addItem(this.buildBoatPicture());
        return room1;
    }

    private Item buildLiquor() {
        Item liquor = new Item("liquor", "it's a liquor.");

        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            Item pickedItem = game.getCurrentStage().pickItem(liquor.getName());
            game.getPlayer().addToInventory(pickedItem);
        });
        liquor.addBehavior(behavior);
        return liquor;
    }

    private Item buildTrainPicture() {
        Behavior behavior = new Behavior();
        behavior.setActionName("move");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
        });
        Item trainPicture = new Item("trainPicture", "it's a picture of a train.");
        trainPicture.addBehavior(behavior);
        return trainPicture;
    }

    private Item buildBoatPicture() {
        Item boatPicture = new Item("CuadroBarco", "it's a picture of a boat.");
        Behavior behavior = new Behavior();
        behavior.setActionName("move");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            this.movePicture();
        });
        boatPicture.addBehavior(behavior);
        return boatPicture;
    }

    private void movePicture() {
        Stage currentStage = escape.getCurrentStage();
        currentStage.addItem(this.buildSecurityBox());
    }

    private Item buildSecurityBox() {
        ItemContainer securityBox = new ItemContainer("CajaFuerte", "'it's a security box.", 1);
        Item idCard = this.buildIdCard();
        securityBox.addItem(idCard);

        Behavior behavior = new Behavior();
        behavior.setActionName("open");
        BehaviorView behaviorView = new BehaviorView();
        behaviorView.setAction((game) -> "Security box opened.");
        behavior.setView(behaviorView);
        behavior.setFailMessage("you can't open the box without key.");
        behavior.setExecutionCondition((game) -> game.getPlayer().hasItem("Llave"));
        behavior.setBehaviorAction((game) -> {
            Item extractedItem = securityBox.extract(idCard.getName());
            game.getCurrentStage().addItem(extractedItem);
        });
        securityBox.addBehavior(behavior);
        return securityBox;
    }

    private Item buildIdCard() {
        Item idCard = new Item("Credencial", "it's an id card.");
        idCard.addState("picture", "stranger");

        Behavior pickBehavior = new Behavior();
        pickBehavior.setActionName("pick");
        BehaviorView idCardView = new BehaviorView();
        idCardView.setAction((game) -> "There you go!");
        pickBehavior.setView(idCardView);
        pickBehavior.setExecutionCondition((game) -> true);
        pickBehavior.setBehaviorAction((game) -> {
            Item pickedItem = game.getCurrentStage().pickItem(idCard.getName());
            game.getPlayer().addToInventory(pickedItem);
        });
        idCard.addBehavior(pickBehavior);

        Behavior showBehavior = new Behavior();
        showBehavior.setActionName("show");
        showBehavior.setView(idCardView);
        showBehavior.setExecutionCondition((game) -> true);
        showBehavior.setBehaviorAction((game) -> {
            Item credencial = game.getPlayer().getInventory().getItem("Credencial");
            if (credencial.getState("picture").equalsIgnoreCase("player")) {
                escape.getStage("Biblioteca").addState("entranceStatus", "opened");
            }
        });
        idCard.addBehavior(showBehavior);

        Behavior putBehavior = new Behavior();
        putBehavior.setActionName("put");
        putBehavior.setView(idCardView);
        putBehavior.setExecutionCondition((game) -> true);
        putBehavior.setBehaviorAction((game) -> {
            Item credencial = game.getPlayer().getInventory().getItem("Credencial");
            credencial.addState("picture", "player");
        });
        idCard.addBehavior(putBehavior);
        return idCard;
    }
    //----------------------------FinRoom1----------------------------------//

    //----------------------------Room2----------------------------------//
    private Stage buildRoom2() {
        Stage room2 = new Stage("Salon2");
        room2.addItem(this.buildHammer());
        room2.addItem(this.buildScrewdriver());
        room2.addConsecutiveStage("Pasillo");
        return room2;
    }

    private Item buildHammer() {
        Item hammer = new Item("Martillo", "it's a hammer.");

        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            Item pickedItem = game.getCurrentStage().pickItem(hammer.getName());
            game.getPlayer().addToInventory(pickedItem);
        });
        hammer.addBehavior(behavior);
        return hammer;
    }

    private Item buildScrewdriver() {
        Item screwdriver = new Item("screwdriver", "it's a screwdriver.");

        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            Item pickedItem = game.getCurrentStage().pickItem(screwdriver.getName());
            game.getPlayer().addToInventory(pickedItem);
        });
        screwdriver.addBehavior(behavior);
        return screwdriver;
    }
    //----------------------------FinRoom2----------------------------------//

    //----------------------------Room3----------------------------------//
    private Stage buildRoom3() {
        Stage room3 = new Stage("Salon3");
        room3.addItem(this.buildKey());
        room3.addConsecutiveStage("Pasillo");
        return room3;
    }

    private Item buildKey() {
        Item key = new Item("Llave", "it's a Llave.");

        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            Item pickedItem = game.getCurrentStage().pickItem(key.getName());
            game.getPlayer().addToInventory(pickedItem);
        });
        key.addBehavior(behavior);
        return key;
    }
    //----------------------------FinRoom3----------------------------------//

    //----------------------------Library----------------------------------//
    private Stage buildLibraryHall() {
        Stage libraryHall = new Stage("BibliotecaAcceso");
        libraryHall.addItem(this.buildLibrarian());
        libraryHall.addConsecutiveStage("Pasillo");
        libraryHall.addConsecutiveStage("Biblioteca");
        return libraryHall;
    }

    private Stage buildLibrary() {
        Stage stage = new Stage("Biblioteca");
        stage.addState("entranceStatus", "closed");
        stage.setEntranceCondition((player) -> stage.getState("entranceStatus").equalsIgnoreCase("opened"));
        stage.addItem(buildOldBook());
        stage.addConsecutiveStage("Sotano");
        stage.addConsecutiveStage("BibliotecaAcceso");
        return stage;
    }

    private Item buildLibrarian() {
        Item librarian = new Item("Bibliotecario", "bla");
        return librarian;
    }

    //TODO: para que despues hagan addItem(this.buildBook("book1")), etc.
    private Item buildBook(String book) {
        Item bookItem = new Item(book, "it's a " + book + ".");

        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            Item pickedItem = game.getCurrentStage().pickItem(bookItem.getName());
            game.getPlayer().addToInventory(pickedItem);
        });
        bookItem.addBehavior(behavior);
        return bookItem;
    }

    //TODO: para el oldBook que tiene un behavior diferente al de los demas libros
    private Item buildOldBook() {

        Behavior behavior = new Behavior();
        behavior.setActionName("move");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            escape.getStage("Sotano").addState("entranceStatus", "opened");
        });
        Item book = new Item("LibroViejo", "it's an old book.");
        book.addBehavior(behavior);
        return book;
    }


    //----------------------------FinLibrary----------------------------------//

    //----------------------------Sotano----------------------------------//

    private Stage buildSotano() {
        Stage sotano = new Stage("Sotano");
        sotano.addConsecutiveStage("Biblioteca");
        sotano.addItem(this.buildEscalera());
        sotano.addItem(this.buildBaranda());
        return sotano;
    }

    private Item buildEscalera() {
        Behavior use = new Behavior();
        use.setActionName("use");
        use.setExecutionCondition((Game) -> true);
        BehaviorView view = new BehaviorView();
        view.setAction((Game) -> "You are dead!");
        use.setView(view);
        use.setBehaviorAction(game -> game.getPlayer().addState("lifeStatus", "dead"));
        Item escalera = new Item("Escalera", "It's an escalera");
        escalera.addBehavior(use);
        return escalera;
    }

    private Item buildBaranda() {
        Behavior use = new Behavior();
        use.setActionName("use");
        use.setExecutionCondition((Game) -> true);
        BehaviorView view = new BehaviorView();
        view.setAction((Game) -> "There you go!");
        use.setView(view);
        use.setBehaviorAction(game -> game.getPlayer().setCurrentStage("SotanoAbajo"));
        Item baranda = new Item("Baranda", "It's a baranda");
        baranda.addBehavior(use);
        return baranda;
    }

    //----------------------------Fin Sotano----------------------------------//

    //----------------------------Sotano Abajo----------------------------------//

    private Stage buildSotanoAbajo() {
        Stage sotanoAbajo = new Stage("SotanoAbajo");
        sotanoAbajo.addConsecutiveStage("Afuera");
        sotanoAbajo.addItem(buildVentana());

        return sotanoAbajo;
    }

    private Item buildVentana() {
        Behavior breakVentana = new Behavior();
        breakVentana.setActionName("break");
        breakVentana.setExecutionCondition(game -> game.getPlayer().getInventory().contains("Martillo"));
        BehaviorView view = new BehaviorView();
        view.setAction((Game) -> "You broke the window!");
        breakVentana.setView(view);
        breakVentana.setBehaviorAction(game -> game.getPlayer().setCurrentStage("Afuera"));
        Item ventana = new Item("Ventana", "It's a ventana");
        ventana.addBehavior(breakVentana);
        return ventana;
    }


    //----------------------------Fin Sotano Abajo----------------------------------//

    //----------------------------Afuera----------------------------------//

    private Stage buildAfuera() {
        return new Stage("Afuera");
    }

    @SuppressWarnings("CPD-END")
    private Stage buildHall() {
        Stage hall = new Stage("Pasillo");
        hall.addConsecutiveStage("Salon1");
        hall.addConsecutiveStage("Salon2");
        hall.addConsecutiveStage("Salon3");
        hall.addConsecutiveStage("BibliotecaAcceso");
        return hall;
    }
}
