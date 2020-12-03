package shakemon.pokemon;

import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PokemonDescriptionTest {
    @Test
    void returns_own_trimmed_value() {
        var description = new PokemonDescription(" Best Pokemon ");
        assertThat(description.asString()).isEqualTo("Best Pokemon");
    }

    @Test
    public void rejects_invalid_value() {
        assertThatIllegalArgumentException().describedAs("null argument")
                .isThrownBy(() -> new PokemonDescription(null));
        assertThatIllegalArgumentException().describedAs("empty argument")
                .isThrownBy(() -> new PokemonDescription(EMPTY));
        assertThatIllegalArgumentException().describedAs("blank argument")
                .isThrownBy(() -> new PokemonDescription(SPACE));
    }
}
