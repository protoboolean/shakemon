package shakemon.pokemon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import shakemon.Config;

import static org.assertj.core.api.Assertions.assertThat;

@EnabledIf(value = "shakemon.TestEnablers#testPokeAPI", disabledReason = "Test Depends on External Service. Used for " +
        "development only ATM.")
class PokeAPITest {
    Config config = Config.load();
    PokeAPI pokeAPI = new PokeAPI(config.pokeApiUrl());

    @Test
    void fetch_pokemon() throws PokemonDescriptionsException {
        var name = new PokemonName("ditto");
        var description = pokeAPI.pokemonDescription(name).asString();
        assertThat(description).contains("Ditto");
    }
}
