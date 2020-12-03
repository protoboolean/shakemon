package shakemon.translation;

import org.junit.jupiter.api.Test;
import shakemon.pokemon.PokemonDescription;
import shakemon.translation.Translate.TranslateException;

import static org.assertj.core.api.Assertions.assertThat;

class TranslateTest {
    @Test
    void fake_returning_prefixed_description() throws TranslateException {
        var translate = Translate.Fake.prependingToDescription("not really Shakespearean: ");
        var shakesperean = translate.toShakesperean(new PokemonDescription("the best pokemon"));
        assertThat(shakesperean.asString()).isEqualTo("not really Shakespearean: the best pokemon");
    }
}
