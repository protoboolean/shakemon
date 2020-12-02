package shakemon.api;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJson;
import shakemon.pokemon.PokemonName;

public class HttpHandlers {
    public void register(Javalin app) {
        app.get("/pokemon/:name", this::translatePokemon);
        app.exception(IllegalArgumentException.class, this::illegalArgumentException);
    }

    void translatePokemon(Context ctx) {
        var name = ctx.pathParam("name");
        var pokemon = new PokemonName(name);
        ctx.result("Hello " + pokemon.asString());
    }

    void illegalArgumentException(IllegalArgumentException iae, Context ctx) {
        ctx.status(400);
        var body = new ExceptionResponseBody(iae, ctx);
        var json = JavalinJson.toJson(body);
        ctx.result(json);
    }
}
