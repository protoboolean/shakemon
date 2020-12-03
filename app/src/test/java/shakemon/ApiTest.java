package shakemon;

import kong.unirest.HttpStatus;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestInstance;
import org.json.JSONException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.assertj.core.api.Assertions.assertThat;
import static shakemon.Utils.resourceAsString;

public class ApiTest {
    static Config config = Config.load();
    static Main main = new Main(config);
    static UnirestInstance adminApi = Unirest.spawnInstance();
    static UnirestInstance appApi = Unirest.spawnInstance();

    @BeforeAll
    static void startService() {
        main.run();
        appApi.config().defaultBaseUrl("http://localhost:" + config.port());
        adminApi.config().defaultBaseUrl("http://localhost:" + config.adminPort());
    }

    @AfterAll
    static void cleanup() {
        adminApi.shutDown();
        appApi.shutDown();
    }

    @Test
    void metrics_panel() {
        appApi.get("/pokemon/any").asJson();

        var response = adminApi.get("/metrics").asJson();
        var json = response.getBody().getObject();
        var metrics = json.getJSONObject("metrics");
        var count = metrics.getJSONObject("HTTP GET /pokemon/:name");
        assertThat(count.getInt("count")).isGreaterThan(0);
    }

    @Test
    void valid_response_structure() {
        var response = appApi.get("/pokemon/any").asJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);

        var expectedJsonTemplate = resourceAsString("api_success_body.json", getClass());
        assertJsonSameStructure(expectedJsonTemplate, response.getBody());
    }

    @Test
    void failure_response_structure() {
        var response = appApi.get("/pokemon/💩")
                .asJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);

        var expectedJsonTemplate = resourceAsString("api_error_body.json", getClass());
        assertJsonSameStructure(expectedJsonTemplate, response.getBody());
    }

    private void assertJsonSameStructure(String expectedJsonTemplate, JsonNode json) {
        try {
            var strict = false;
            JSONAssert.assertEquals(expectedJsonTemplate, json.toString(), strict);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
