package shakemon.pokemon;

import kong.unirest.GetRequest;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.net.URL;

public class PokeAPI implements PokemonDescriptions {
    private final String pokemonByName;

    public PokeAPI(URL baseUrl) {
        var url = baseUrl.toString();
        if (!url.endsWith("/")) {
            url = url + "/";
        }
        pokemonByName = url + "pokemon/{name}";
    }

    @Override
    public PokemonDescription pokemonDescription(PokemonName name) throws PokemonDescriptionsException {
        var response = getPokemonByName().routeParam("name", name.asString())
                .asObject(PokeAPIResponse.class);
        if (!response.isSuccess()) {
            throw PokemonDescriptionsException.because(response);
        }
        var pokemon = response.getBody();
        var describe = pokemon.description();
        return new PokemonDescription(describe);
    }

    JsonNode pokemonJson(PokemonName name) throws PokemonDescriptionsException {
        var response = getPokemonByName().routeParam("name", name.asString()).asJson();
        if (!response.isSuccess()) {
            throw PokemonDescriptionsException.because(response);
        }
        return response.getBody();
    }

    private GetRequest getPokemonByName() {
        return Unirest.get(pokemonByName);
    }

}
