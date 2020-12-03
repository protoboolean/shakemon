package shakemon;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static shakemon.Utils.propertiesAsMap;

public class ShakemonConfig {
    private static final String FAKE_FLAG = "fake";

    private final Map<String, String> properties;

    private ShakemonConfig(Map<String, String> properties) {
        this.properties = properties;
    }

    int port() {
        return Integer.parseInt(properties.get(Keys.PORT.key));
    }

    int adminPort() {
        var adminPort = properties.get(Keys.ADMIN_PORT.key);
        return Integer.parseInt(adminPort);
    }

    public Optional<URL> pokeApiUrl() {
        var url = properties.get(Keys.POKEAPI_BASE_URL.key);
        checkArgument(!StringUtils.isBlank(url), "blank url");
        url = url.trim();

        if (FAKE_FLAG.equalsIgnoreCase(url)) {
            return Optional.empty();
        } else {
            try {
                return Optional.of(new URL(url));
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    public static ShakemonConfig load() {
        return new ShakemonConfig(properties());
    }

    public static ShakemonConfig loadWithFakes() {
        var properties = properties();
        properties.put(Keys.POKEAPI_BASE_URL.key, FAKE_FLAG);
        return new ShakemonConfig(properties);
    }

    private static Map<String, String> properties() {
        return propertiesAsMap(configFileReader());
    }

    private static Reader configFileReader() {
        var configUri = StringUtils.trimToNull(System.getenv("SHAKEMON_CONFIG"));
        return Optional.ofNullable(configUri)
                .map(Utils::uriReader)
                .orElseGet(ShakemonConfig::defaultConfig);
    }

    @NotNull
    private static InputStreamReader defaultConfig() {
        var inputStream = Main.class.getResourceAsStream("config.properties");
        return new InputStreamReader(inputStream);
    }


    public enum Keys {
        PORT("shakemon.port"),
        ADMIN_PORT("shakemon.admin_port"),
        POKEAPI_BASE_URL("shakemon.pokeapi.base_url");

        public final String key;

        Keys(String key) {
            this.key = key;
        }
    }
}
