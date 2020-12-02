package shakemon.pokemon;

import org.junit.jupiter.api.Test;
import shakemon.pokemon.PokemonDescriptions.PokemonDescriptionsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PokemonDescriptionsTest {
    @Test
    public void fake_api_returning_constant_description() throws PokemonDescriptionsException {
        var someDescription = "the best pokemon";
        var api = PokemonDescriptions.Fake.alwaysReturning(someDescription);

        var name = new PokemonName("any");
        var description = api.pokemonDescription(name);
        assertEquals(description.asString(), someDescription);
    }
}
