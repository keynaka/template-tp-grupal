package ar.fiuba.tdd.tp.driver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Fede on 19/05/2016.
 */
public class EscapeDriverTest {

    @Test
    public void lostUsingStairs() {
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("escape");
        String response = driver.sendCommand("goto BibliotecaAcceso");
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
