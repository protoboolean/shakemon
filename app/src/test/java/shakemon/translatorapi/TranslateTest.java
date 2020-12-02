package shakemon.translatorapi;

import org.junit.jupiter.api.Test;
import shakemon.pokeapi.PokemonDescription;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TranslateTest {
    @Test
    void fake_returning_prefixed_description() {
        Translate translate = Translate.Fakes.prependingToDescription("Some Marker: ");
        Shakesperean shakesperean = translate.toShakesperean(new PokemonDescription("the best pokemon"));
        assertEquals(shakesperean.asString(), "Some Marker: the best pokemon");
    }
}
