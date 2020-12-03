package shakemon.api;

import com.codahale.metrics.MetricRegistry;
import com.google.common.net.MediaType;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminEndpoints {
    private static final Logger LOG = LoggerFactory.getLogger(AdminEndpoints.class);

    private final MetricRegistry metrics;

    public AdminEndpoints(MetricRegistry metrics) {
        this.metrics = metrics;
    }

    public void register(Javalin admin) {
        var path = "/metrics";
        admin.get(path, (ctx) -> {
            ctx.contentType(MediaType.JSON_UTF_8.type());
            ctx.result(JavalinJson.toJson(metrics));
        });
        LOG.info("GET {}", path);
    }
}
