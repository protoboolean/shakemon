package shakemon.translation;

import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShakespereanTest {
    @Test
    void returns_own_trimmed_value() {
        assertEquals(new Shakesperean(" some translation ").asString(), "some translation");
    }

    @Test
    void rejects_invalid_value() {
        assertThrows(IllegalArgumentException.class, () -> new Shakesperean(null), "null");
        assertThrows(IllegalArgumentException.class, () -> new Shakesperean(EMPTY), "empty");
        assertThrows(IllegalArgumentException.class, () -> new Shakesperean(SPACE), "space");
    }
}
