package ar.fiuba.tdd.tp.games.hanoitowers;

import ar.fiuba.tdd.tp.games.items.Item;

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Patri on 15/05/2016.
 */
public class Stacker extends Item {

    private Stack<Item> items;
    private String stackerId;

    public Stacker(String stackerId, Collection<Item> initialContent) {
        super(stackerId, "stacker");
        this.stackerId = stackerId;
        this.items = new Stack<Item>();
        Iterator iterator = initialContent.iterator();
        while (iterator.hasNext()) {
            this.items.push((Item) iterator.next());
        }
    }

    public void stack(Item item) {
        this.items.push(item);
    }

    public Item checkTop() {
        try {
            return this.items.peek();
        } catch (EmptyStackException e) {
            return null;
        }
    }

    public Item getTop() {
        try {
            return this.items.pop();
        } catch (EmptyStackException e) {
            return null;
        }
    }

    public int getSize() {

        return this.items.size();

    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public String getName() {

        return stackerId;

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


}
