package shakemon.pokemon;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

class PokeAPIResponse {
    private final StringBuilder description = new StringBuilder();

    String name = "";
    Integer base_experience = 0;
    List<HeldItemElement> held_items = new ArrayList<>();

    String describe() {
        return description
                .append(StringUtils.capitalize(name))
                .append(" experience level is ").append(base_experience)
                .append(". ")
                .append(new HeldItems(held_items).describe())
                .toString();
    }

    static class HeldItems {
        private final StringBuilder description = new StringBuilder("This pokemon holds ");

        private final List<HeldItemElement> held_items;

        HeldItems(List<HeldItemElement> held_items) {
            this.held_items = held_items;
        }

        StringBuilder describe() {
            if (held_items.isEmpty()) {
                return description.append("no items.");
            } else {
                return description
                        .append(held_items.size())
                        .append(" items.");
            }
        }
    }

    static class HeldItemElement {
        HeldItem item = new HeldItem();
    }

    static class HeldItem {
        String name = "";
    }
}
