package shakemon.pokemon;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

class PokeAPIResponse {
    private final StringBuilder description = new StringBuilder();

    String name = "";
    Integer base_experience = 0;
    List<HeldItemElement> held_items = new ArrayList<>();
    List<MoveElement> moves = new ArrayList<>();

    String description() {
        return description
                .append(capitalizedName())
                .append(" experience level is ").append(base_experience).append(".")
                .append(heldItemsDescription())
                .append(movesDescription())
                .toString();
    }

    private String capitalizedName() {
        return StringUtils.capitalize(name);
    }


    StringBuilder heldItemsDescription() {
        var description = new StringBuilder("This pokemon holds");
        if (held_items.isEmpty()) {
            return description.append(" no items.");
        } else {
            return description
                    .append(held_items.size())
                    .append(" items.");
        }
    }

    StringBuilder movesDescription() {
        var description = new StringBuilder(capitalizedName())
                .append(" can make");
        if (moves.isEmpty()) {
            return description.append(" no moves.");
        } else {
            return description
                    .append(moves.size())
                    .append(" moves.");
        }
    }

    static class HeldItemElement {
        HeldItem item = new HeldItem();
    }

    static class HeldItem {
        String name = "";
        String url = "";
    }

    static class MoveElement {
        Move move = new Move();
    }

    static class Move {
        String name = "";
        String url = "";
    }
}
