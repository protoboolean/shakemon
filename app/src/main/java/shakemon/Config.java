package shakemon;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

class Config {
    private final Map<String, String> properties;

    private Config(Map<String, String> properties) {
        this.properties = properties;
    }

    int port() {
        return Integer.parseInt(properties.get("shakemon.port"));
    }

    static Config load() {
        try {
            var properties = new Properties();
            var stream = Main.class.getResourceAsStream("config.properties");
            properties.load(stream);
            return new Config((Map) properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
