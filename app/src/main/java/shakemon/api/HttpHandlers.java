package shakemon.api;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJson;
import shakemon.TranslatePokemonDescription;
import shakemon.TranslatePokemonDescription.TranslatePokemonDescriptionException;
import shakemon.pokemon.PokemonName;

import static com.google.common.base.Preconditions.checkNotNull;

public class HttpHandlers {

    private final TranslatePokemonDescription translatePokemonDescription;

    public HttpHandlers(TranslatePokemonDescription translatePokemonDescription) {
        this.translatePokemonDescription = checkNotNull(translatePokemonDescription);
    }

    public void register(Javalin app) {
        app.get("/pokemon/:name", this::translatePokemon);
        app.exception(IllegalArgumentException.class, this::illegalArgumentException);
    }

    void translatePokemon(Context ctx) throws TranslatePokemonDescriptionException {
        var name = ctx.pathParam("name");
        var pokemon = new PokemonName(name);
        var description = translatePokemonDescription.shakespereanDescription(pokemon);
        var shakespereanPokemon = new ShakespereanPokemon(pokemon.asString(), description.asString());
        var json = JavalinJson.toJson(shakespereanPokemon);
        ctx.result(json);
    }

    void illegalArgumentException(IllegalArgumentException iae, Context ctx) {
        ctx.status(400);
        var body = new ExceptionResponseBody(iae, ctx);
        var json = JavalinJson.toJson(body);
        ctx.result(json);
    }

    static class ShakespereanPokemon {
        public final String name;
        public final String description;

        ShakespereanPokemon(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }
}
