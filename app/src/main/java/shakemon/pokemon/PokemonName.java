package shakemon.pokemon;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class PokemonName {
    private final String name;

    public PokemonName(String name) {
        checkArgument(!isBlank(name), "blank");
        this.name = name.trim();
    }

    public String asString() {
        return name;
    }
}
