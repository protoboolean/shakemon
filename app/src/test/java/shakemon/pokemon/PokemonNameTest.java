package shakemon.pokemon;

import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PokemonNameTest {
    @Test
    void returns_own_trimmed_value() {
        var name = new PokemonName(" ditto ");
        assertThat(name.asString()).isEqualTo("ditto");
    }

    @Test
    void rejects_invalid_value() {
        assertThatIllegalArgumentException().describedAs("null argument")
                .isThrownBy(() -> new PokemonName(null));
        assertThatIllegalArgumentException().describedAs("empty argument")
                .isThrownBy(() -> new PokemonName(EMPTY));
        assertThatIllegalArgumentException().describedAs("blank argument")
                .isThrownBy(() -> new PokemonName(SPACE));
    }
}
