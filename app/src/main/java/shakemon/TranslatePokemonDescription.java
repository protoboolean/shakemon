package shakemon;

import shakemon.pokemon.PokemonDescription;
import shakemon.pokemon.PokemonDescriptions;
import shakemon.pokemon.PokemonName;
import shakemon.translation.Shakesperean;
import shakemon.translation.Translate;

import static com.google.common.base.Preconditions.checkNotNull;

public class TranslatePokemonDescription {
    private final PokemonDescriptions pokemonDescriptions;
    private final Translate translate;

    public TranslatePokemonDescription(PokemonDescriptions pokemonDescriptions, Translate translate) {
        this.pokemonDescriptions = checkNotNull(pokemonDescriptions, "pokemon descriptions");
        this.translate = checkNotNull(translate, "translate");
    }

    public Shakesperean shakespereanDescription(PokemonName name) throws TranslatePokemonDescriptionException {
        try {
            PokemonDescription pokemonDescription = pokemonDescriptions.pokemonDescription(name);
            return translate.toShakesperean(pokemonDescription);
        } catch (PokemonDescriptions.PokemonDescriptionsException e) {
            throw new TranslatePokemonDescriptionException(e);
        }
    }

    class TranslatePokemonDescriptionException extends Exception {
        public TranslatePokemonDescriptionException(Throwable cause) {
            super(cause);
        }
    }
}
