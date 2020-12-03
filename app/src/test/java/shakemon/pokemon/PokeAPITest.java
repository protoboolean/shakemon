package shakemon.pokemon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import shakemon.Config;

import static org.assertj.core.api.Assertions.assertThat;

@EnabledIfEnvironmentVariable(named = "SHAKEMON_ALLOW_NETWORK_IO_TEST", matches = "true",
        disabledReason = "Test Depends on External Service. Used for development only ATM.")
class PokeAPITest {
    Config config = Config.load();
    PokeAPI pokeAPI = new PokeAPI(config.pokeApiUrl());

    @Test
    void pokemon_description__dev_only() throws PokemonDescriptionsException {
        var name = new PokemonName("ditto");
        var description = pokeAPI.pokemonDescription(name).asString();
        assertThat(description).containsIgnoringCase(name.asString());
    }

    @Test
    void pokemon_json__dev_only() throws PokemonDescriptionsException {
        var name = new PokemonName("ditto");
        var json = pokeAPI.pokemonJson(name);
        assertThat(json).isNotNull();
    }
}
