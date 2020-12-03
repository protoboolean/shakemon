package shakemon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import shakemon.TranslatePokemonDescription.TranslatePokemonDescriptionException;
import shakemon.pokemon.PokemonDescriptions;
import shakemon.pokemon.PokemonDescriptions.PokemonDescriptionsException;
import shakemon.pokemon.PokemonName;
import shakemon.translation.Translate;
import shakemon.translation.Translate.TranslateException;

import static org.assertj.core.api.Assertions.*;

class TranslatePokemonDescriptionTest {
    PokemonDescriptions descriptions = PokemonDescriptions.Fake.alwaysReturning("best pokemon");
    Translate.Fake translate = Translate.Fake.prependingToDescription("not really Shakespearean: ");

    @AfterEach
    void cleanup() {
        translate.resetCounter();
    }

    @Test
    void translate_pokemon_description_to_Shakespearean() throws TranslatePokemonDescriptionException {
        var useCase = new TranslatePokemonDescription(descriptions, translate);
        var translation = useCase.shakespereanDescription(new PokemonName("any"));

        assertThat(translation.asString()).isEqualTo("not really Shakespearean: best pokemon");
    }

    @Test
    void pokemon_descriptions_throws_an_exception() {
        PokemonDescriptions descriptions = PokemonDescriptions.Fake.throwingException();

        var useCase = new TranslatePokemonDescription(descriptions, translate);

        assertThatThrownBy(() -> useCase.shakespereanDescription(new PokemonName("any")))
                .isInstanceOf(TranslatePokemonDescriptionException.class)
                .hasCauseInstanceOf(PokemonDescriptionsException.class);

        assertThat(translate.wasInvoked())
                .describedAs("translation was invoked")
                .isFalse();
    }

    @Test
    void translate_throws_an_exception() {
        var translate = Translate.Fake.throwingException();

        var useCase = new TranslatePokemonDescription(descriptions, translate);

        assertThatThrownBy(() -> useCase.shakespereanDescription(new PokemonName("any")))
                .isInstanceOf(TranslatePokemonDescriptionException.class)
                .hasCauseInstanceOf(TranslateException.class);

    }

    @Test
    void arguments_null_check() {
        assertThatNullPointerException()
                .describedAs("null descriptions")
                .isThrownBy(() -> new TranslatePokemonDescription(null, translate));

        assertThatNullPointerException()
                .describedAs("null translate")
                .isThrownBy(() -> new TranslatePokemonDescription(descriptions, null));
    }
}
