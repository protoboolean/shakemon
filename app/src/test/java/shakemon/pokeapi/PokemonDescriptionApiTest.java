package shakemon.pokeapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PokemonDescriptionApiTest {
    @Test
    public void fake_api_returning_constant_description() {
        String someDescription = "the best pokemon";
        PokemonDescriptionApi api = PokemonDescriptionApi.Fakes.alwaysReturning(someDescription);
        PokemonDescription description = api.pokemonDescription("any");
        assertEquals(description.asString(), someDescription);
    }
}
