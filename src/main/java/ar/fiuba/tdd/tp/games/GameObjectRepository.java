package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.exceptions.GameException;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.objects.GameObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by swandelow on 5/31/16.
 */
public class GameObjectRepository {

    private ConcreteGame game;

    public GameObjectRepository(ConcreteGame game) {
        this.game = game;
    }

    // método agregado para evitar castear en todos lados
    public Item getItem(String itemName) {
        return (Item) this.getGameObject(itemName);
    }

    // método agregado para evitar castear en todos lados
    public ItemKeeper getItemKeeper(String objectName) {
        return (ItemKeeper) this.getGameObject(objectName);
    }

    public GameObject getGameObject(String objectName) {
        Optional<GameObject> object = this.getGameObjects().stream()
                .filter(gameObject -> gameObject.getName().equalsIgnoreCase(objectName))
                .findFirst();
        if(object.isPresent()) {
            return object.get();
        }
        throw new GameException("GameObject not found.");
    }

    public List<GameObject> getGameObjects() {
        List<GameObject> gameObjects = new ArrayList<>();
        // agrego el player
        gameObjects.add(this.game.getPlayer());
        // agrego los escenarios
        Collection<Stage> stages = this.game.getStages().values();
        gameObjects.addAll(stages);
        // agrego los items de cada escenario
        List<Item> items = stages.stream().map(stage -> stage.getItems()).flatMap(itemList -> itemList.stream()).collect(Collectors.toList());
        gameObjects.addAll(items);
        return gameObjects;
    }
}
