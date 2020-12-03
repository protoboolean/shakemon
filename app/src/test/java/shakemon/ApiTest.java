package shakemon;

import kong.unirest.HttpStatus;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiTest {

    static Main main = new Main();

    @BeforeAll
    static void startService() {
        main.run();
    }

    @Test
    void valid_response_structure() {
        var response = Unirest.get("http://localhost:7000/pokemon/any")
                .asJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);

        var expectedJsonTemplate = load("api_success_body.json", getClass());
        assertJsonSameStructure(expectedJsonTemplate, response.getBody());
    }

    @Test
    void failure_response_structure() {
        var response = Unirest.get("http://localhost:7000/pokemon/💩")
                .asJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);

        var expectedJsonTemplate = load("api_error_body.json", getClass());
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

    static String load(String resourceName, Class<? extends ApiTest> interestedClass) {
        try {
            var resource = interestedClass.getResource(resourceName);
            var path = Paths.get(resource.toURI());
            return new String(Files.readAllBytes(path));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
