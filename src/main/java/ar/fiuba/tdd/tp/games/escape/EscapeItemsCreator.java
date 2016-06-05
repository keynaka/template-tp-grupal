package ar.fiuba.tdd.tp.games.escape;

import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;

import java.util.ArrayList;
import java.util.List;

import static ar.fiuba.tdd.tp.games.escape.EscapeProperties.*;

/**
 * Created by Patri on 05/06/2016.
 */
public class EscapeItemsCreator {

    List<Item> createdItems;

    public EscapeItemsCreator() {
        createdItems = new ArrayList<Item>();
    }

    @SuppressWarnings("CPD-START")

    public List<Item> create() {
        this.createPlayerItems();
        this.createRoom1Items();
        this.createRoom2Items();
        this.createRoom3Items();
        this.createLibraryAccessItems();
        this.createLibraryItems();
        this.createBasementItems();
        this.createBasementDownstairsItems();
        return this.createdItems;
    }

    private void createRoom1Items() {
        this.addItem(new Item(TABLE_NAME, TABLE_DESCRIPTION));
        this.addItem(new Item(LIQUOR_NAME, LIQUOR_DESCRIPTION));
        this.addItem(new Item(GLASS1_NAME, GLASS_DESCRIPTION));
        this.addItem(new Item(GLASS2_NAME, GLASS_DESCRIPTION));
        this.addItem(new Item(CHAIR1_NAME, CHAIR_DESCRIPTION));
        this.addItem(new Item(CHAIR2_NAME, CHAIR_DESCRIPTION));
        this.addItem(new Item(TRAIN_PICTURE_NAME, TRAIN_PICTURE_DESCRIPTION));
        this.addItem(new Item(BOAT_PICTURE_NAME, BOAT_PICTURE_DESCRIPTION));

        Item idCard = new Item(ID_CARD_NAME, ID_CARD_DESCRIPTION);
        idCard.addState(ID_CARD_PICTURE_STATE, STRANGER_PICTURE_NAME);
        this.addItem(idCard);

        ItemContainer safebox = new ItemContainer(SAFEBOX_NAME, SAFEBOX_DESCRIPTION, SAFEBOX_SIZE);
        safebox.addItem(idCard);
        this.addItem(safebox);
    }

    private void createRoom2Items() {
        this.addItem(new Item(HAMMER_NAME, HAMMER_DESCRIPTION));
        this.addItem(new Item(SCREWDRIVER1_NAME, SCREWDRIVER_DESCRIPTION));
        this.addItem(new Item(SCREWDRIVER2_NAME, SCREWDRIVER_DESCRIPTION));
    }

    private void createRoom3Items() {
        this.addItem(new Item(KEY_NAME, KEY_DESCRIPTION));
    }

    private void createLibraryAccessItems() {
        ItemContainer librarian = new ItemContainer(LIBRARIAN_NAME, LIBRIARIAN_DESCRIPTION, LIBRARIAN_SIZE);
        this.addItem(librarian);
    }

    private void createLibraryItems() {
        this.addItem(new Item(SHELF_NAME, SHELF_DESCRIPTION));
        this.addItem(new Item(BOOK1_NAME, BOOK_DESCRIPTION));
        this.addItem(new Item(BOOK2_NAME, BOOK_DESCRIPTION));
        this.addItem(new Item(BOOK3_NAME, BOOK_DESCRIPTION));
        this.addItem(new Item(BOOK4_NAME, BOOK_DESCRIPTION));
        this.addItem(new Item(BOOK5_NAME, BOOK_DESCRIPTION));
        this.addItem(new Item(BOOK6_NAME, BOOK_DESCRIPTION));
        this.addItem(new Item(BOOK7_NAME, BOOK_DESCRIPTION));
        this.addItem(new Item(BOOK8_NAME, BOOK_DESCRIPTION));
        this.addItem(new Item(BOOK9_NAME, BOOK_DESCRIPTION));
        this.addItem(new Item(OLD_BOOK_NAME, OLD_BOOK_DESCRIPTION));
    }

    private void createBasementItems() {
        this.addItem(new Item(STAIRS_NAME, STAIRS_DESCRIPTION));
        this.addItem(new Item(RAILING_NAME, RAILING_DESCRIPTION));
    }

    private void createBasementDownstairsItems() {
        this.addItem(new Item(WINDOW_NAME, WINDOW_DESCRIPTION));
    }

    private void createPlayerItems() {
        this.addItem(new Item(PLAYER_PICTURE_NAME, PLAYER_PICTURE_DESCRIPTION));
        this.addItem(new Item(PEN_NAME, PEN_DESCRIPTION));
    }

    @SuppressWarnings("CPD-END")

    private void addItem(Item item) {
        this.createdItems.add(item);
    }

}
