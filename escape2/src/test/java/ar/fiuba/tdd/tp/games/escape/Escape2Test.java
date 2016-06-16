package ar.fiuba.tdd.tp.games.escape;

import ar.fiuba.tdd.tp.games.ActionOld;
import ar.fiuba.tdd.tp.games.Command;
import ar.fiuba.tdd.tp.games.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sebass on 15/06/16.
 */
public class Escape2Test {

    private Game target;

    @Before
    public void setUp() {
        target = new EscapeBuilder2().build();
    }

    @Test
    public void moveBoatPictureAppearsSafeBox() {
        assertEquals("You have entered to Salon1.", target.play(new Command(ActionOld.GOTO, "Salon1")));
        assertEquals("Items in Salon1: BotellaLicor, CuadroBarco, CuadroTren, Mesa, Silla1, Silla2, Vaso1, Vaso2.", target.play(new Command(ActionOld.LOOK_AROUND)));
        assertEquals("There you go!", target.play(new Command(ActionOld.MOVE, "CuadroBarco")));
        assertEquals("Items in Salon1: BotellaLicor, CajaFuerte, CuadroBarco, CuadroTren, Mesa, Silla1, Silla2, Vaso1, Vaso2.", target.play(new Command(ActionOld.LOOK_AROUND)));
    }


}
