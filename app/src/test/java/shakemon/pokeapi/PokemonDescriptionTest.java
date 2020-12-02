package shakemon.pokeapi;

import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PokemonDescriptionTest {
    @Test
    void returns_own_trimmed_value() {
        assertEquals(new PokemonDescription(" Best Pokemon ").asString(), "Best Pokemon");
    }

    @Test
    public void rejects_invalid_value() {
        assertThrows(IllegalArgumentException.class, () -> new PokemonDescription(null), "null");
        assertThrows(IllegalArgumentException.class, () -> new PokemonDescription(EMPTY), "empty");
        assertThrows(IllegalArgumentException.class, () -> new PokemonDescription(SPACE), "space");
    }
}
