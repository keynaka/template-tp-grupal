package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.exceptions.GameException;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;
import ar.fiuba.tdd.tp.games.objects.GameObject;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by swandelow on 4/21/16.
 */
public class Stage extends GameObject implements ItemKeeper {

    private static final int DEFAULT_STAGE_SIZE = 20;

    private String name;

    private ItemContainer itemContainer;
    private List<String> consecutiveStages = new ArrayList<>();
    private Predicate<Player> entranceCondition = (player) -> true;

    public Stage() {
        this.name = "room";
        this.itemContainer = new ItemContainer(this.name, "", DEFAULT_STAGE_SIZE);
    }

    public Stage(String name) {
        this();
        this.name = name;
        this.itemContainer = new ItemContainer(name, "", DEFAULT_STAGE_SIZE);
    }

    public String getName() {
        return this.name;
    }

    public void addItem(Item item) {
        this.itemContainer.addItem(item);
    }

    public void addItems(Item... items) {
        for (Item item : items) {
            this.addItem(item);
        }
    }

    public Item pickItem(String itemName) {
        return this.itemContainer.extract(itemName);
    }

    public String lookAround() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Items in the %s: ", this.getName()));
        Iterator<Item> it = itemContainer.getAllItems().iterator();
        while (it.hasNext()) {
            sb.append(it.next().getName());
            if (it.hasNext()) {
                sb.append(", ");
            } else {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    public Item getItem(String itemName) {
        return this.itemContainer.getItem(itemName);
    }

    public ItemContainer getItemContainer() {
        return itemContainer;
    }

    public void addConsecutiveStage(String stageName) {
        this.consecutiveStages.add(stageName);
    }

    public String getConsecutiveStage(String stageName) {
        Optional<String> consecutiveStageName = this.consecutiveStages.stream()
                .filter(cs -> cs.equalsIgnoreCase(stageName)).findFirst();
        if (consecutiveStageName.isPresent()) {
            return consecutiveStageName.get();
        }
        throw new GameException("Stage not found");
    }

    public void setEntranceCondition(Predicate<Player> entranceCondition) {
        this.entranceCondition = entranceCondition;
    }

    public void enter(Player player) {
        if (this.entranceCondition.test(player)) {
            player.setCurrentStage(this.getName());
        }
    }

    @Override
    public Collection<Item> getItems() {
        return this.itemContainer.getAllItems();
    }

    @Override
    public Item removeItem(String itemName) {
        return this.itemContainer.extract(itemName);
    }

    @Override
    public void insertItem(Item item) {
        this.itemContainer.addItem(item);
    }
}
