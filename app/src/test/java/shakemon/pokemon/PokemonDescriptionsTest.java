package shakemon.pokemon;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PokemonDescriptionsTest {
    @Test
    public void fake_api_returning_constant_description() throws PokemonDescriptionsException {
        var someDescription = "the best pokemon";
        var api = PokemonDescriptions.Fake.alwaysReturning(someDescription);

        var name = new PokemonName("any");
        var description = api.pokemonDescription(name);
        assertThat(description.asString()).isEqualTo(someDescription);
    }
}
