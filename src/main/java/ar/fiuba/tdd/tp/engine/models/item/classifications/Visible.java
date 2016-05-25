package ar.fiuba.tdd.tp.engine.models.item.classifications;

/**
 * Created by Nico on 20/05/2016.
 */
public class Visible extends ItemClassificationAction implements ISingleActionItem {
    public Visible(boolean isVisible) {
        itemType = ItemClassificationType.VISIBLE;
        state = isVisible;
    }

    public boolean doAction() {
        return this.state;
    }
}
