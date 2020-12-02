package shakemon.pokemon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PokemonDescriptionsTest {
    @Test
    public void fake_api_returning_constant_description() {
        String someDescription = "the best pokemon";
        PokemonDescriptions api = PokemonDescriptions.Fakes.alwaysReturning(someDescription);
        PokemonDescription description = api.pokemonDescription("any");
        assertEquals(description.asString(), someDescription);
    }
}
