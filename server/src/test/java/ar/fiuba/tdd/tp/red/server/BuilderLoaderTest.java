package ar.fiuba.tdd.tp.red.server;

import ar.fiuba.tdd.tp.games.GameBuilder;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by sebass on 05/06/16.
 */
public class BuilderLoaderTest {

    @Ignore
    @Test
    public void test() {
        try {
            GameBuilder builder = BuilderLoader.load("/home/sebass/juegos-tdd/fetch-quest.jar");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
