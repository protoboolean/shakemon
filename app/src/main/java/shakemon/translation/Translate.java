package shakemon.translation;

import shakemon.pokemon.PokemonDescription;

public interface Translate {
    Shakesperean toShakesperean(PokemonDescription description);

    class Fake implements Translate {
        private int invocationCounts = 0;
        private final Translate delegate;

        private Fake(Translate delegate) {
            this.delegate = delegate;
        }

        public static Fake prependingToDescription(String prefix) {
            return new Fake(description -> new Shakesperean(prefix + description.asString()));
        }

        @Override
        public Shakesperean toShakesperean(PokemonDescription description) {
            invocationCounts += 1;
            return delegate.toShakesperean(description);
        }

        public boolean wasInvoked() {
            return invocationCounts > 0;
        }

        public void resetCounter() {
            invocationCounts = 0;
        }
    }
}
