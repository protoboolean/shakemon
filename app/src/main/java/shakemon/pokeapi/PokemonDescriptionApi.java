package shakemon.pokeapi;

public interface PokemonDescriptionApi {
    PokemonDescription pokemonDescription(String name);

    class Fakes {
        private Fakes() {
        }

        public static PokemonDescriptionApi alwaysReturning(String description) {
            return (anyPokemonName) -> new PokemonDescription(description);
        }
    }
}
