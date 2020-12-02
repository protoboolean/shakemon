package shakemon.pokemon;

public interface PokemonDescriptions {
    PokemonDescription pokemonDescription(String name);

    class Fakes {
        private Fakes() {
        }

        public static PokemonDescriptions alwaysReturning(String description) {
            return (anyPokemonName) -> new PokemonDescription(description);
        }
    }
}
