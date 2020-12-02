package shakemon;

import org.junit.jupiter.api.Test;
import shakemon.pokemon.PokemonDescriptions;
import shakemon.pokemon.PokemonName;
import shakemon.translation.Shakesperean;
import shakemon.translation.Translate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TranslatePokemonDescriptionTest {
    PokemonDescriptions descriptions = PokemonDescriptions.Fake.alwaysReturning("best pokemon");
    Translate translate = Translate.Fake.prependingToDescription("not really shakespeare: ");

    @Test
    void translate_pokemon_description_to_shakesperean() {
        TranslatePokemonDescription useCase = new TranslatePokemonDescription(descriptions, translate);
        Shakesperean translation = useCase.shakespereanDescription(new PokemonName("any"));

        assertEquals(translation.asString(), "not really shakespeare: best pokemon");
    }

    @Test
    void arguments_null_check() {
        assertThrows(NullPointerException.class, () -> new TranslatePokemonDescription(null, translate), "null descriptions");

        assertThrows(NullPointerException.class, () -> new TranslatePokemonDescription(descriptions, null), "null descriptions");
    }
}
