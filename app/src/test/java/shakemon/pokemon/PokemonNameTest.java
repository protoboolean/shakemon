package shakemon.pokemon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PokemonNameTest {
    @Test
    void returns_own_normalized_value() {
        var name = new PokemonName(" diTTo ");
        assertThat(name.asString()).isEqualTo("ditto");
    }

    @ParameterizedTest(name = "name \"{0}\" ({1}) raises an illegal-argument-exception")
    @CsvFileSource(resources = "invalid_pokemon_names.csv", numLinesToSkip = 1)
    void invalid_name(String value, String description) {
        assertThatIllegalArgumentException().as(description)
                .isThrownBy(() -> new PokemonName(value));
    }

}
