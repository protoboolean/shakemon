package shakemon.pokemon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PokemonDescriptionsTest {
    @Test
    public void fake_api_returning_constant_description() {
        String someDescription = "the best pokemon";
        PokemonDescriptions api = PokemonDescriptions.Fakes.alwaysReturning(someDescription);

        PokemonName name = new PokemonName("any");
        PokemonDescription description = api.pokemonDescription(name);
        assertEquals(description.asString(), someDescription);
    }
}
