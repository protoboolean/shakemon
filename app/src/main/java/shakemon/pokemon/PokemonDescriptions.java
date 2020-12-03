package shakemon.pokemon;

public interface PokemonDescriptions {
    PokemonDescription pokemonDescription(PokemonName name) throws PokemonDescriptionsException;

    class Fake implements PokemonDescriptions {
        private final PokemonDescriptions delegate;

        private Fake(PokemonDescriptions delegate) {
            this.delegate = delegate;
        }

        public static Fake alwaysReturning(String description) {
            return new Fake((anyPokemonName) -> new PokemonDescription(description));
        }

        public static Fake throwingException() {
            return new Fake((anyPokemonName) -> {
                throw new PokemonDescriptionsException("some error");
            });
        }

        @Override
        public PokemonDescription pokemonDescription(PokemonName name) throws PokemonDescriptionsException {
            return delegate.pokemonDescription(name);
        }
    }
}
