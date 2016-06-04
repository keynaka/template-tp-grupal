package ar.fiuba.tdd.tp.driver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Fede on 19/05/2016.
 */
//@Ignore
public class EscapeDriverTest2 {

    @Test
    public void testGotoNextRoomCommand() {
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("escape2");
        assertEquals("You have entered to Salon3.", driver.sendCommand("goto Salon3"));
        assertEquals("You have entered to Pasillo.", driver.sendCommand("goto Pasillo"));
        assertEquals("You have entered to Salon1.", driver.sendCommand("goto Salon1"));
        assertEquals("You have entered to Pasillo.", driver.sendCommand("goto Pasillo"));
    }

    @Test
    public void moveBoatPicture() {
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("escape2");
        assertEquals("You have entered to Salon1.", driver.sendCommand("goto Salon1"));
        assertEquals("There you go!", driver.sendCommand("move CuadroBarco"));
    }

    private void getToLibraryWithIdCard(GameDriver driver) {
        driver.sendCommand("goto Salon3");
        driver.sendCommand("pick Llave");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon1");
        driver.sendCommand("move CuadroBarco");
        driver.sendCommand("open CajaFuerte Llave");
        driver.sendCommand("pick Credencial");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("put Foto Credencial");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("show Credencial");
        driver.sendCommand("goto Biblioteca");
    }

    @Test
    public void moveOldBook() {
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("escape2");
        getToLibraryWithIdCard(driver);
        assertEquals("There you go!", driver.sendCommand("move LibroViejo"));
    }

    private void pickMartilloAndPutFotoInIdCardAndEnterBibliotecaAcceso(GameDriver driver) {
        driver.sendCommand("drop Lapicera");
        driver.sendCommand("goto Salon3");
        driver.sendCommand("pick Llave");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon1");
        driver.sendCommand("move CuadroBarco");
        driver.sendCommand("open CajaFuerte Llave");
        driver.sendCommand("pick Credencial");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon2");
        driver.sendCommand("pick Martillo");
        driver.sendCommand("put Foto Credencial");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto BibliotecaAcceso");
    }

    @Test
    public void breakWindow() {
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("escape2");

        this.pickMartilloAndPutFotoInIdCardAndEnterBibliotecaAcceso(driver);

        assertEquals("There you go!", driver.sendCommand("show Credencial"));
        assertEquals("You have entered to Biblioteca.", driver.sendCommand("goto Biblioteca"));
        assertEquals("There you go!", driver.sendCommand("move LibroViejo"));
        assertEquals("You have entered to Sotano.", driver.sendCommand("goto Sotano"));
        assertEquals("You have entered to SotanoAbajo.", driver.sendCommand("use Baranda"));
        assertEquals("Window is broken!", driver.sendCommand("break Ventana"));

    }

    @Test
    public void showIdCard() {
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("escape2");

        assertEquals("You have entered to Salon3.", driver.sendCommand("goto Salon3"));
        assertEquals("There you go!", driver.sendCommand("pick Llave"));
        assertEquals("You have entered to Pasillo.", driver.sendCommand("goto Pasillo"));
        assertEquals("You have entered to Salon1.", driver.sendCommand("goto Salon1"));
        assertEquals("There you go!", driver.sendCommand("move CuadroBarco"));
        assertEquals("CajaFuerte opened.", driver.sendCommand("open CajaFuerte Llave"));
        assertEquals("There you go!", driver.sendCommand("pick Credencial"));
        assertEquals("You have entered to Pasillo.", driver.sendCommand("goto Pasillo"));
        assertEquals("You have put Foto in Credencial", driver.sendCommand("put Foto Credencial"));
        assertEquals("You have entered to BibliotecaAcceso.", driver.sendCommand("goto BibliotecaAcceso"));
        assertEquals("There you go!", driver.sendCommand("show Credencial"));
        assertEquals("You have entered to Biblioteca.", driver.sendCommand("goto Biblioteca"));

    }

    @Test
    public void lostUsingStairs() {
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("escape2");
        assertEquals("You have entered to BibliotecaAcceso.", driver.sendCommand("goto BibliotecaAcceso"));
        assertEquals("You have entered to Pasillo.", driver.sendCommand("goto Pasillo"));
        assertEquals("You have entered to Salon3.", driver.sendCommand("goto Salon3"));
        assertEquals("There you go!", driver.sendCommand("pick Llave"));
        assertEquals("You have entered to Pasillo.", driver.sendCommand("goto Pasillo"));
        assertEquals("You have entered to Salon1.", driver.sendCommand("goto Salon1"));
        assertEquals("There you go!", driver.sendCommand("move CuadroBarco"));
        assertEquals("Items in the Salon1: CajaFuerte, CuadroBarco.", driver.sendCommand("look around"));
        assertEquals("CajaFuerte opened.", driver.sendCommand("open CajaFuerte Llave"));
        assertEquals("There you go!", driver.sendCommand("pick Credencial"));
        assertEquals("You have put Foto in Credencial", driver.sendCommand("put Foto Credencial"));
        assertEquals("You have entered to Pasillo.", driver.sendCommand("goto Pasillo"));
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("show Credencial Bibliotecario");
        driver.sendCommand("goto Biblioteca");
        assertEquals("There you go!", driver.sendCommand("move LibroViejo"));
        assertEquals("You have entered to Sotano.", driver.sendCommand("goto Sotano"));
        assertEquals("You lost the game!", driver.sendCommand("use Escalera"));
        assertTrue(GameState.Lost == driver.getCurrentState());
    }

    @Test
    public void lostWithoutHammer() {
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("escape");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon3");
        driver.sendCommand("pick Llave");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon1");
        driver.sendCommand("move CuadroBarco");
        driver.sendCommand("open CajaFuerte Llave");
        driver.sendCommand("pick Credencial");
        driver.sendCommand("put Foto Credencial");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("show Credencial Bibliotecario");
        driver.sendCommand("goto Biblioteca");
        driver.sendCommand("move LibroViejo");
        driver.sendCommand("goto Sotano");
        driver.sendCommand("use Baranda");
        assertTrue(GameState.Lost == driver.getCurrentState());
    }

    @Test
    public void winWithHammer() {
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("escape");
        driver.sendCommand("drop Lapicera");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon3");
        driver.sendCommand("pick Llave");
        driver.sendCommand("goto Pasillo");
        assertEquals("You have entered to Salon2.", driver.sendCommand("goto Salon2"));
        assertEquals("There you go!", driver.sendCommand("pick Martillo"));
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon1");
        driver.sendCommand("move CuadroBarco");
        driver.sendCommand("open CajaFuerte Llave");
        driver.sendCommand("pick Credencial");
        driver.sendCommand("put Foto Credencial");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("show Credencial Bibliotecario");
        driver.sendCommand("goto Biblioteca");
        driver.sendCommand("move LibroViejo");
        driver.sendCommand("goto Sotano");
        driver.sendCommand("use Baranda");
        driver.sendCommand("break Ventana Martillo");
        driver.sendCommand("goto Afuera");
        assertTrue(GameState.Won == driver.getCurrentState());
    }
}
