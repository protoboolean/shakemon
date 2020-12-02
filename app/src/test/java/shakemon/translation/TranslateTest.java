package shakemon.translation;

import org.junit.jupiter.api.Test;
import shakemon.pokemon.PokemonDescription;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TranslateTest {
    @Test
    void fake_returning_prefixed_description() {
        Translate translate = Translate.Fakes.prependingToDescription("not really shakespeare: ");
        Shakesperean shakesperean = translate.toShakesperean(new PokemonDescription("the best pokemon"));
        assertEquals(shakesperean.asString(), "not really shakespeare: the best pokemon");
    }
}
