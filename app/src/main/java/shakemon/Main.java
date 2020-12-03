/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package shakemon;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.json.MetricsModule;
import com.google.common.net.MediaType;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;
import io.javalin.plugin.json.JavalinJson;
import org.jetbrains.annotations.NotNull;
import shakemon.api.HttpHandlers;
import shakemon.pokemon.PokemonDescriptions;
import shakemon.translation.Translate;

import java.util.concurrent.TimeUnit;

public class Main {
    final MetricRegistry metrics = new MetricRegistry();

    private final Config config;

    public Main(Config config) {
        this.config = config;
    }

    public static void main(String[] args) {
        new Main(Config.load()).run();
    }

    public void run() {
        JavalinJackson.defaultObjectMapper().registerModule(metricsToJsonSerializationConfig());
        var admin = Javalin.create().start(config.adminPort());

        admin.get("/metrics", (ctx) -> {
            ctx.contentType(MediaType.JSON_UTF_8.type());
            ctx.result(JavalinJson.toJson(metrics));
        });

        var app = Javalin.create().start(config.port());
        new HttpHandlers(dependencies(), metrics).register(app);
    }

    @NotNull
    private MetricsModule metricsToJsonSerializationConfig() {
        var showSamples = true;
        return new MetricsModule(TimeUnit.MILLISECONDS, TimeUnit.MILLISECONDS, showSamples);
    }

    private TranslatePokemonDescription dependencies() {
        return new TranslatePokemonDescription(
                PokemonDescriptions.Fake.alwaysReturning("best pokemon"),
                Translate.Fake.prependingToDescription("Pretend it is Shakesperean: "));
    }

}
