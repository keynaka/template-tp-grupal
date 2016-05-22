package ar.fiuba.tdd.tp.engine.models.item;

import ar.fiuba.tdd.tp.engine.core.CommandExecution;
import ar.fiuba.tdd.tp.engine.models.item.classifications.IPairActionItem;
import ar.fiuba.tdd.tp.engine.models.item.classifications.ISingleActionItem;
import ar.fiuba.tdd.tp.engine.models.item.classifications.ItemClassificationAction;
import ar.fiuba.tdd.tp.engine.models.item.classifications.ItemClassificationTwiceActionException;

import java.util.Map;
import java.util.Set;

/**
 * Created by Nico on 20/05/2016.
 */
public class Item implements IIdentificable<Integer> {
    private int id;
    protected Map<String, ItemClassificationAction> classifications; // ItemClassificationType => ItemClassificationAction
    protected String name;
    private Set<CommandExecution> knownCommands;
    private static int idCount = 0;

    public Item(int idItem) {
        id = idItem;
    }

    public Item() {
        this(Item.generateNewId());
    }

    // Use this to generate an id for a new item
    public static int generateNewId() {
        return ++Item.idCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        // Overrides current id
        this.id = id;
    }

    public void setName(String itemName) {
        name = itemName;
    }

    // Used only to build the Item
    public void addItemClassification(String type, ItemClassificationAction classification) {
        classifications.put(type, classification);
    }

    public boolean is(String itemClassificationType) {
        return this.classifications.containsKey(itemClassificationType);
    }

    public boolean doAction(String itemClassificationType) throws ItemClassificationTwiceActionException,
                                                                  ItemClassificationTypeNotSupportedException {
        ItemClassificationAction itemAction = this.getItemAction(itemClassificationType);
        if (itemAction instanceof ISingleActionItem) {
            return ((ISingleActionItem) itemAction).doAction();
        }
        return false;
    }

    public boolean doActionWith(String itemClassificationType, int idItem) throws ItemClassificationTwiceActionException,
                                                                                  ItemClassificationTypeNotSupportedException {
        ItemClassificationAction itemAction = this.getItemAction(itemClassificationType);
        if (itemAction instanceof IPairActionItem) {
            return ((IPairActionItem) itemAction).doActionWith(idItem);
        }
        return false;
    }

    private ItemClassificationAction getItemAction(String itemClassificationType) throws ItemClassificationTypeNotSupportedException {
        if (!this.is(itemClassificationType)) {
            throw new ItemClassificationTypeNotSupportedException();
        }
        return this.classifications.get(itemClassificationType);
    }

    // Add supported commands by this item
    public void addCommand(CommandExecution command) {
        knownCommands.add(command);
    }

    public String runCommand(String commandName) {
        String result = "Unknown action";
        for (CommandExecution cmd : knownCommands) {
            if (cmd.getCommandName().equals(commandName)) {
                result = cmd.run();
                break;
            }
        }
        return result;
    }
}
