package shakemon.translation;

import org.junit.jupiter.api.Test;
import shakemon.pokemon.PokemonDescription;
import shakemon.translation.Translate.TranslateException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TranslateTest {
    @Test
    void fake_returning_prefixed_description() throws TranslateException {
        Translate translate = Translate.Fake.prependingToDescription("not really Shakespearean: ");
        Shakesperean shakesperean = translate.toShakesperean(new PokemonDescription("the best pokemon"));
        assertEquals(shakesperean.asString(), "not really Shakespearean: the best pokemon");
    }
}
