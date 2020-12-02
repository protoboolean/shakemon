package shakemon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import shakemon.TranslatePokemonDescription.TranslatePokemonDescriptionException;
import shakemon.pokemon.PokemonDescriptions;
import shakemon.pokemon.PokemonDescriptions.PokemonDescriptionsException;
import shakemon.pokemon.PokemonName;
import shakemon.translation.Shakesperean;
import shakemon.translation.Translate;
import shakemon.translation.Translate.TranslateException;

import static org.junit.jupiter.api.Assertions.*;

class TranslatePokemonDescriptionTest {
    PokemonDescriptions descriptions = PokemonDescriptions.Fake.alwaysReturning("best pokemon");
    Translate.Fake translate = Translate.Fake.prependingToDescription("not really Shakespearean: ");

    @AfterEach
    void cleanup() {
        translate.resetCounter();
    }

    @Test
    void translate_pokemon_description_to_Shakespearean() throws TranslatePokemonDescriptionException {
        TranslatePokemonDescription useCase = new TranslatePokemonDescription(descriptions, translate);
        Shakesperean translation = useCase.shakespereanDescription(new PokemonName("any"));

        assertEquals(translation.asString(), "not really Shakespearean: best pokemon");
    }

    @Test
    void pokemon_descriptions_throws_an_exception() {
        PokemonDescriptions descriptions = PokemonDescriptions.Fake.throwingException();

        TranslatePokemonDescription useCase = new TranslatePokemonDescription(descriptions, translate);
        TranslatePokemonDescriptionException tpde = assertThrows(TranslatePokemonDescriptionException.class, () ->
                useCase.shakespereanDescription(new PokemonName("any")));
        assertEquals(tpde.getCause().getClass(), PokemonDescriptionsException.class);

        assertFalse(translate.wasInvoked(), "translation was invoked");
    }

    @Test
    void translate_throws_an_exception() {
        Translate.Fake translate = Translate.Fake.throwingException();

        TranslatePokemonDescription useCase = new TranslatePokemonDescription(descriptions, translate);
        TranslatePokemonDescriptionException tpde = assertThrows(TranslatePokemonDescriptionException.class, () ->
                useCase.shakespereanDescription(new PokemonName("any")));
        assertEquals(tpde.getCause().getClass(), TranslateException.class);
    }

    @Test
    void arguments_null_check() {
        assertThrows(NullPointerException.class, () -> new TranslatePokemonDescription(null, translate), "null descriptions");

        assertThrows(NullPointerException.class, () -> new TranslatePokemonDescription(descriptions, null), "null descriptions");
    }
}
