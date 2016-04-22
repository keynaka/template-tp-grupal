package ar.fiuba.tdd.tp.games;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by swandelow on 4/21/16.
 */
public class Stage {

    List<Item> items;

    public Stage() {
        this.items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public Item pickItem(String itemName) {
        Item result = null;
        for (Iterator<Item> it = this.items.iterator(); it.hasNext(); ) {
            Item item = it.next();
            if (item.getName().equalsIgnoreCase(itemName)) {
                result = item;
                it.remove();
            }
        }
        return  result;
    }

    public String lookArond() {
        StringBuilder sb = new StringBuilder();
        sb.append("There's ");
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            sb.append("a ".concat(it.next().getName()));
            if (it.hasNext()) {
                sb.append(" and ");
            }
        }
        sb.append(" in the room.");
        return sb.toString();
    }
}
