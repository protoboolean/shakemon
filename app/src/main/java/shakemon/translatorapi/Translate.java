package shakemon.translatorapi;

import shakemon.pokeapi.PokemonDescription;

public interface Translate {
    Shakesperean toShakesperean(PokemonDescription description);

    class Fakes {
        private Fakes() {
        }

        public static Translate prependingToDescription(String prefix) {
            return description -> new Shakesperean(prefix + description.asString());
        }
    }
}
