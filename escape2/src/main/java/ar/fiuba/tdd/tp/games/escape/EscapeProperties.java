package ar.fiuba.tdd.tp.games.escape;

/**
 * Created by swandelow on 5/29/16.
 */
@SuppressWarnings("CPD-START")

public class EscapeProperties {

    public static final String GAME_NAME = "Escape";
    public static final String PLAYER_NAME = "player";
    public static final String HALL_NAME = "Pasillo";
    public static final String ROOM1_NAME = "Salon1";
    public static final String ROOM2_NAME = "Salon2";
    public static final String ROOM3_NAME = "Salon3";
    public static final String LIBRARY_ACCESS_NAME = "BibliotecaAcceso";
    public static final String LIBRARY_NAME = "Biblioteca";
    public static final String BASEMENT_NAME = "Sotano";
    public static final String BASEMENT_DOWNSTAIRS_NAME = "SotanoAbajo";
    public static final String OUTSIDE_NAME = "Afuera";
    public static final String KEY_NAME = "Llave";
    public static final String TABLE_NAME = "Mesa";
    public static final String PEN_NAME = "Lapicera";
    public static final String RAILING_NAME = "Baranda";
    public static final String RAILING_DESCRIPTION = "Es una baranda.";
    public static final String STAIRS_NAME = "Escalera";
    public static final String STAIRS_DESCRIPTION = "Es una escalera.";
    public static final String GLASS_DESCRIPTION = "Es un vaso.";
    public static final String HAMMER_NAME = "Martillo";
    public static final String HAMMER_DESCRIPTION = "Es un martillo.";
    public static final String LIQUOR_NAME = "BotellaLicor";
    public static final String GLASS1_NAME = "Vaso1";
    public static final String GLASS2_NAME = "Vaso2";
    public static final String LIQUOR_DESCRIPTION = "Es una botella de licor.";
    public static final String KEY_DESCRIPTION = "Es una llave.";
    public static final String PEN_DESCRIPTION = "Es una lapicera.";
    public static final String TABLE_DESCRIPTION = "Es una mesa.";
    public static final String BOAT_PICTURE_NAME = "CuadroBarco";
    public static final String TRAIN_PICTURE_NAME = "CuadroTren";
    public static final String BOAT_PICTURE_DESCRIPTION = "Es un cuadro de un barco.";
    public static final String TRAIN_PICTURE_DESCRIPTION = "Es un cuadro de un tren.";
    public static final String CHAIR1_NAME = "Silla1";
    public static final String CHAIR2_NAME = "Silla2";
    public static final String SCREWDRIVER1_NAME = "Destornillador1";
    public static final String SCREWDRIVER2_NAME = "Destornillador2";
    public static final String SHELF_NAME = "Estante";
    public static final String BOOK1_NAME = "Libro1";
    public static final String BOOK2_NAME = "Libro2";
    public static final String BOOK3_NAME = "Libro3";
    public static final String BOOK4_NAME = "Libro4";
    public static final String BOOK5_NAME = "Libro5";
    public static final String BOOK6_NAME = "Libro6";
    public static final String BOOK7_NAME = "Libro7";
    public static final String BOOK8_NAME = "Libro8";
    public static final String BOOK9_NAME = "Libro9";
    public static final String SHELF_DESCRIPTION = "Es un estante";
    public static final String BOOK_DESCRIPTION = "Es un libro";
    public static final String SCREWDRIVER_DESCRIPTION = "Es un destornillador.";
    public static final String CHAIR_DESCRIPTION = "Es una silla.";
    public static final String SAFEBOX_NAME = "CajaFuerte";
    public static final String SAFEBOX_DESCRIPTION = "Es una caja fuerte.";
    public static final int SAFEBOX_SIZE = 1;
    public static final String ID_CARD_NAME = "Credencial";
    public static final String ID_CARD_DESCRIPTION = "Es una credencial de la biblioteca.";
    public static final String ID_CARD_PICTURE_STATE = "idcard-picture";
    public static final String PLAYER_PICTURE_NAME = "Foto";
    public static final String PLAYER_PICTURE_DESCRIPTION = "Es una foto del jugador.";
    public static final String STRANGER_PICTURE_NAME = "Foto de un extraño";
    public static final String WINDOW_NAME = "Ventana";
    public static final String WINDOW_DESCRIPTION = "Es una ventana.";
    public static final String WINDOW_STATE = "window-state";
    public static final String BROKEN_WINDOW = "Rota";
    public static final String OLD_BOOK_STATE = "old-book-state";
    public static final String MOVED_BOOK = "Movido";
    public static final String LIFE_STATUS = "player-lifestatus";
    public static final String DEAD_PLAYER = "Muerto";
    public static final String ALIVE_PLAYER = "Vivo";
    public static final String ALLOWED_IN_LIBRARY_STATUS = "allowed-in-status";
    public static final String ALLOWED = "Puede ingresar";
    public static final String NOT_ALLOWED = "No puede ingresar";
    public static final String LOCK_STATUS = "lock-status";
    public static final String LOCKED = "Cerrado";
    public static final String UNLOCKED = "Abierto";
    public static final String LIBRARIAN_NAME = "Bibliotecario";
    public static final String LIBRIARIAN_DESCRIPTION = "Es un bibliotecario";
    public static final int LIBRARIAN_SIZE = 1;
    public static final String OLD_BOOK_NAME = "LibroViejo";
    public static final String OLD_BOOK_DESCRIPTION = "Es un libro viejo.";

    public static final String SLEEP_STATUS = "sleep-status";
    public static final String SLEEP_STATUS_AWAKE = "awake";
    public static final String SLEEP_STATUS_SLEPT = "slept";



    public static final String NOT_IN_INVENTORY_MSG = "%s is not in the inventory.";
    public static final String GOTO = "goto";
    public static final String GOTO_RESULT_MSG = "You have entered to %s.";
    public static final String GOTO_NOT_NEXT_ROOM_MSG = "You can't go to %s from here.";
    public static final String DROP_RESULT_MSG = "You have dropped %s.";
    public static final String PICK = "pick";
    public static final String DROP = "drop";
    public static final String PICK_RESULT_MSG = "There you go!";
    public static final String MOVE = "move";
    public static final String USE = "use";
    public static final String MOVE_RESULT_MSG = "There you go!";
    public static final String OPEN = "open";
    public static final String OPEN_RESULT_MSG = "%s opened.";
    public static final String PUT = "put";
    public static final String BREAK = "break";
    public static final String SHOW = "show";
    public static final String BREAK_WINDOW_RESULT_MSG = "Window is broken!";
    public static final String SHOW_RESULT_MSG = "There you go!";
    public static final String SHOW_LIQUOR_MSG = "Librarian fall sleep.";
    public static final String SHOW_ALT_RESULT_MSG = "You are banned from the library!";
    public static final String MOVE_OLD_BOOK_RESULT_MSG = "There you go!";
    public static final String USE_STAIRS_RESULT_MSG = "You are dead !! :(";
    public static final String USE_RAILING_RESULT_MSG = "You have entered to SotanoAbajo.";
    public static final String ERR_MSG = "You can't do that!";

    public static final Long RANDOM_WALKER_PERIOD = 1000 * 60L * 4L;
    public static final Long AWAKE_TIME = 1000 * 60L * 2L;


    @SuppressWarnings("CPD-END")
    public static final String PUT_RESULT_MSG = "You have put %s in %s";


}
