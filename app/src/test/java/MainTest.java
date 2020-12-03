import kong.unirest.Unirest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import shakemon.Main;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

    static Main main = new Main();

    @BeforeAll
    static void startService() {
        main.run();
    }

    @Test
    void valid_response_structure() {
        var response = Unirest.get("http://localhost:7000/pokemon/any")
                .asJson();

        assertThat(response.isSuccess()).isTrue();

        var body = response.getBody().getObject();
        assertThat(body.has("name")).isTrue();
        assertThat(body.has("description")).isTrue();
    }

    @Test
    @Disabled("Realized that pokemon name should only be alphabetic. This would also make testing more obvious.")
    void failure_response_structure() {
        var response = Unirest.get("http://localhost:7000/pokemon/!!!")
                .asJson();

        assertThat(response.isSuccess()).isTrue();

        var body = response.getBody().getObject();
        assertThat(body.has("name")).isTrue();
        assertThat(body.has("description")).isTrue();
    }
}
