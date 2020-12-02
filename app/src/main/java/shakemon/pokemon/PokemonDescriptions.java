package shakemon.pokemon;

public interface PokemonDescriptions {
    PokemonDescription pokemonDescription(PokemonName name);

    class Fakes {
        private Fakes() {
        }

        public static PokemonDescriptions alwaysReturning(String description) {
            return (anyPokemonName) -> new PokemonDescription(description);
        }
    }
}
