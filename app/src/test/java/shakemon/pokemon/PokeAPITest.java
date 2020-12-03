package shakemon.pokemon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import shakemon.ShakemonConfig;

import static org.assertj.core.api.Assertions.assertThat;

@EnabledIfEnvironmentVariable(named = "SHAKEMON_ALLOW_NETWORK_IO_TEST", matches = "true",
        disabledReason = "Test depends on external services. Used for development only ATM.")
@EnabledIfEnvironmentVariable(named = "SHAKEMON_CONFIG", matches = "file://.*",
        disabledReason = "Test depends on external services. Used for development only ATM.")
class PokeAPITest {
    static ShakemonConfig config = ShakemonConfig.load();
    static PokeAPI pokeAPI = config.pokeApiUrl().map(PokeAPI::new).orElseThrow();

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
