package shakemon.pokemon;

import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PokemonNameTest {
    @Test
    void returns_own_trimmed_value() {
        assertEquals(new PokemonName(" ditto ").asString(), "ditto");
    }

    @Test
    void rejects_invalid_value() {
        assertThrows(IllegalArgumentException.class, () -> new PokemonName(null), "null");
        assertThrows(IllegalArgumentException.class, () -> new PokemonName(EMPTY), "empty");
        assertThrows(IllegalArgumentException.class, () -> new PokemonName(SPACE), "space");
    }
}
