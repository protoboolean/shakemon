package shakemon.pokemon;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class PokemonDescription {
    private final String description;

    public PokemonDescription(String description) {
        checkArgument(!isBlank(description), "blank");
        this.description = description.trim();
    }

    public String asString() {
        return description;
    }

    @Override
    public String toString() {
        return asString();
    }
}
