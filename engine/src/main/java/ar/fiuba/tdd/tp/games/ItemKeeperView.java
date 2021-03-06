package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.items.Item;

import java.util.Iterator;

/**
 * Created by Eze on 31/05/2016.
 */
public class ItemKeeperView {

    private ItemKeeper ik;
    private String name;

    public ItemKeeperView(ItemKeeper itemKeeper, String ikName) {
        this.ik = itemKeeper;
        this.name = ikName;
    }

    public String print() {
        if (this.ik.getItems().isEmpty()) {
            return String.format("No objects in %s.", this.name);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Items in %s: ", name));
        Iterator<Item> it = ik.getItems().iterator();
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
}
