package shakemon.pokemon;

import kong.unirest.HttpResponse;

import static com.google.common.base.Preconditions.checkArgument;

public class PokemonDescriptionsException extends Exception {
    public PokemonDescriptionsException(String message) {
        super(message);
    }

    static PokemonDescriptionsException because(HttpResponse<?> response) {
        checkArgument(!response.isSuccess());
        var message = String.format(
                "Failed to resolve description. Response: %s %s",
                response.getStatus(),
                response.getStatusText());
        return new PokemonDescriptionsException(message);
    }
}
