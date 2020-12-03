package shakemon.api;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.plugin.json.JavalinJson;
import org.jetbrains.annotations.NotNull;
import shakemon.TranslatePokemonDescription;
import shakemon.TranslatePokemonDescription.TranslatePokemonDescriptionException;
import shakemon.pokemon.PokemonName;

import static com.google.common.base.Preconditions.checkNotNull;

public class HttpHandlers {

    private final TranslatePokemonDescription translatePokemonDescription;
    private final MetricRegistry metricRegistry;

    public HttpHandlers(TranslatePokemonDescription translatePokemonDescription, MetricRegistry metricRegistry) {
        this.translatePokemonDescription = checkNotNull(translatePokemonDescription);
        this.metricRegistry = metricRegistry;
    }

    public void register(Javalin app) {
        getDescription(app);
        app.exception(IllegalArgumentException.class, this::illegalArgumentException);
    }

    private void getDescription(Javalin app) {
        var path = "/pokemon/:name";
        var timer = metricRegistry.timer("HTTP GET " + path);
        app.before(path, (ctx -> {
            var timerCtx = timer.time();
            ctx.attribute(ContextAttributesKeys.TIMER.name(), timerCtx);
        }));
        app.get(path, this::translatePokemonHandler);
        app.after(path, (ctx -> {
            var timerCtx = ctx.<Timer.Context>attribute(ContextAttributesKeys.TIMER.name());
            if (timerCtx != null) {
                timerCtx.stop();
            }
        }));
    }

    public void translatePokemonHandler(@NotNull Context ctx) throws TranslatePokemonDescriptionException {
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

    enum ContextAttributesKeys {
        TIMER
    }
}
