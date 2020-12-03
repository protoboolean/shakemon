package shakemon;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkArgument;

public class ShakemonConfig {
    private final Map<String, String> properties;

    private ShakemonConfig(Map<String, String> properties) {
        this.properties = properties;
    }

    int port() {
        return Integer.parseInt(properties.get("shakemon.port"));
    }

    int adminPort() {
        var adminPort = properties.get("shakemon.admin_port");
        return Integer.parseInt(adminPort);
    }

    public URL pokeApiUrl() {
        var url = properties.get("shakemon.pokeapi.base_url");
        checkArgument(!StringUtils.isBlank(url), "blank url");
        try {
            return new URL(url.trim());
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static ShakemonConfig load() {
        try {
            var properties = new Properties();
            var stream = Main.class.getResourceAsStream("config.properties");
            properties.load(stream);
            //noinspection unchecked,rawtypes
            return new ShakemonConfig((Map) properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
