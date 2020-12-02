package shakemon.pokemon;

public interface PokemonDescriptions {
    PokemonDescription pokemonDescription(PokemonName name);

    class Fake implements PokemonDescriptions {
        private final PokemonDescriptions delegate;

        private Fake(PokemonDescriptions delegate) {
            this.delegate = delegate;
        }

        public static Fake alwaysReturning(String description) {
            return new Fake((anyPokemonName) -> new PokemonDescription(description));
        }

        @Override
        public PokemonDescription pokemonDescription(PokemonName name) {
            return delegate.pokemonDescription(name);
        }
    }
}
